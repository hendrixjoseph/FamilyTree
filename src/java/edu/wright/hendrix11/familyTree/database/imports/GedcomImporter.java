
package edu.wright.hendrix11.familyTree.database.imports;

import edu.wright.hendrix11.familyTree.database.Database;
import edu.wright.hendrix11.familyTree.database.table.MarriageTable;
import edu.wright.hendrix11.familyTree.database.table.PersonTable;
import edu.wright.hendrix11.familyTree.database.table.SpouseChildTable;
import edu.wright.hendrix11.familyTree.entity.Marriage;
import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.entity.PersonInfo;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class GedcomImporter extends Importer
{
    private static final DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
    
    private static PrintStream out;
    
    private HashMap<String, Person> entry = new HashMap<String, Person>();
    
    private PersonTable personTable;
    private MarriageTable marriageTable;
    private SpouseChildTable spouseChildTable;

    public GedcomImporter(String fileName) throws FileNotFoundException
    {
        super(fileName);
    }

    public GedcomImporter(Scanner in)
    {
        super(in);
    }
    
    @Override
    protected void initializeTables()
    {
        personTable = new PersonTable();
        marriageTable = new MarriageTable();
        spouseChildTable = new SpouseChildTable();
    }
    
    /**
     *
     * 
     */
    @Override
    protected void processData()
    {        
        String nextLine = "";
        nextLine = getNextLine(nextLine);
        
        // Get to the first person
        while(hasNext(!nextLine.startsWith("0 @I")))
        {
            nextLine = getNextLine(nextLine);
        }

        while(in.hasNext())
        {               
            if(nextLine.startsWith("0 @I"))
            {                
                nextLine = gatherPeople(nextLine);
            }
            else if(!nextLine.startsWith("0 @I"))
            {
                
                
                nextLine = getNextLine(nextLine);
            } 
        }
    }
    
    private boolean hasNext(boolean condition)
    {
        return in.hasNext() && condition;
    }
    
    private String getNextLine(String nextLine)
    {
        if(in.hasNext())
            nextLine = in.nextLine();
        
        return nextLine;
    }
    
    private String gatherPeople(String nextLine)
    {
        String gedcomid = getId(nextLine);

        Person person = new Person();

        nextLine = getNextLine(nextLine);

        while(hasNext(!nextLine.startsWith("0 @I") && !nextLine.endsWith("@ FAM")))
        {  
            String change = nextLine;

            PersonInfo personInfo = new PersonInfo();

            nextLine = processPerson(person, nextLine);
            nextLine = processPersonInfo(personInfo, nextLine);

            if(personInfo.isSet())
                person.addInfo(personInfo);

            if(change.equals(nextLine))
            {
                nextLine = getNextLine(nextLine);                        
            }
        }
        
        person = personTable.insert(person);
        
        out.println(person.getName() + " inserted with gedcom id " + gedcomid + " and database id " + person.getId());
        
        entry.put(gedcomid, person);
        
        return nextLine;
    }
    
    private String gatherFamilies(String nextLine)
    {
        if(nextLine.endsWith("FAM"))
        {
            nextLine = getNextLine(nextLine);

            Marriage marriage = new Marriage();

            nextLine = processFamily(nextLine, marriage);
            
            marriageTable.insert(marriage);
        }
        
        return nextLine;
    }
    
    private String processFamily(String nextLine, Marriage marriage)
    {
        while(hasNext(!nextLine.endsWith("FAM")))
        {                        
            if(nextLine.startsWith("1 HUSB"))
            {
                String id = getId(nextLine);
                Person husband = entry.get(id);
                marriage.setHusband(husband);
            }
            else if(nextLine.startsWith("1 WIFE"))
            {
                String id = getId(nextLine);
                Person wife = entry.get(id);
                marriage.setWife(wife);
            }
            else if(nextLine.startsWith("1 CHIL"))
            {
                String id = getId(nextLine);
                Person child = entry.get(id);
                Person husband = marriage.getHusband();
                Person wife = marriage.getWife();

                husband.addChild(child, wife);
                wife.addChild(child, husband);
                
                child.setFather(husband);
                child.setMother(wife);
                
                personTable.update(child);
            }
            else if(nextLine.startsWith("1 MARR"))
            {
                nextLine = getNextLine(nextLine);
                String change = nextLine;

                if(nextLine.startsWith("2 DATE"))
                {
                    Date date = parseDate(nextLine);

                    if(date != null)
                        marriage.setAnniversary(date);

                    nextLine = getNextLine(nextLine);
                }

                if(nextLine.startsWith("2 PLAC"))
                {
                    nextLine = parsePlac(nextLine);
                    marriage.setPlace(nextLine);
                    nextLine = getNextLine(nextLine);
                }
                
                if(change.equals(nextLine))
                    nextLine = getNextLine(nextLine);
            }
        }
        
        return nextLine;
    }
    
    private String processPersonInfo(PersonInfo personInfo, String nextLine)
    {
        int start;
        int end;
        
        if(nextLine.startsWith("1 BURI"))
        {
            nextLine = getNextLine(nextLine);
            
            while(hasNext(nextLine.startsWith("2 PLAC")))
            {
                nextLine = parsePlac(nextLine);                

                personInfo.setType("Place of Burial");
                personInfo.setDescription(nextLine);
                
                nextLine = getNextLine(nextLine);
            }
        }
        else if(nextLine.startsWith("1 SSN"))
        {
            nextLine = getNextLine(nextLine);
            
            while(hasNext(nextLine.startsWith("2 PLAC")))
            {
                nextLine = parsePlac(nextLine);                

                personInfo.setType("SSN");
                personInfo.setDescription(nextLine);
                
                nextLine = getNextLine(nextLine);
            }
        }
        else if(nextLine.startsWith("1 NOTE"))
        {
            nextLine = getNextLine(nextLine);
            
            if(nextLine.startsWith("0 @N"))
                nextLine = getNextLine(nextLine);
            
            StringBuilder sb = new StringBuilder();
            
            while(hasNext(nextLine.startsWith("1 CON")))
            {
                start = "1 CONC ".length();
                
                if(nextLine.length() >= start)
                {
                    nextLine = nextLine.substring(start);
                    sb.append(nextLine);
                }
                else
                {
                    sb.append("\n");
                }
                
                nextLine = getNextLine(nextLine);
            }
            
            personInfo.setType("Note");
            personInfo.setDescription(sb.toString());
        }
        else if(nextLine.startsWith("1 ALIA"))
        {
            start = nextLine.indexOf("/") + 1;
            end = nextLine.lastIndexOf("/");
            
            nextLine = nextLine.substring(start, end);
            
            personInfo.setType("Alias");
            personInfo.setDescription(nextLine);
            
            nextLine = getNextLine(nextLine);
        }
        
        return nextLine;
    }
    
    private String processPerson(Person person, String nextLine)
    {        
        int start;
        
        if(nextLine.contains("NAME"))
        {
            start = "1 NAME ".length();
            String name = nextLine.substring(start);
            person.setName(name.replace("/", ""));
            nextLine = getNextLine(nextLine);
        }
        else if(nextLine.contains("SEX"))
        {
            if(nextLine.endsWith("M"))
                person.setGender("Male");
            else if(nextLine.endsWith("F"))
                person.setGender("Female");
            
            nextLine = getNextLine(nextLine);
        }
        else if(nextLine.contains("BIRT"))
        {
            nextLine = getNextLine(nextLine);

            while(hasNext(nextLine.contains("DATE") || nextLine.contains("PLAC")))
            {
                if(nextLine.contains("DATE"))
                {
                    Date date = parseDate(nextLine);
                    
                    if(date != null)
                        person.setDateOfBirth(date);
                }

                if(nextLine.contains("PLAC"))
                {
                    nextLine = parsePlac(nextLine);
                    person.setPlaceOfBirth(nextLine); 
                }
                
                nextLine = getNextLine(nextLine);
            }
        }
        else if(nextLine.contains("DEAT"))
        {
            nextLine = getNextLine(nextLine);

            while(hasNext(nextLine.contains("DATE") || nextLine.contains("PLAC")))
            {
                if(nextLine.contains("DATE"))
                {
                    Date date = parseDate(nextLine);
                    
                    if(date != null)
                        person.setDateOfDeath(date);
                }

                if(nextLine.contains("PLAC"))
                {
                    nextLine = parsePlac(nextLine);   
                    person.setPlaceOfDeath(nextLine); 
                }
                
                nextLine = getNextLine(nextLine);
            }
        }
        
        return nextLine;
    }
    
    private String getId(String line)
    {
        int start = line.indexOf("@") + 1;
        int end = line.indexOf("@", start + 1);
        return line.substring(start, end);
    }
    
    private String parsePlac(String nextLine)
    {
        int start = "2 PLAC ".length();
        nextLine = nextLine.substring(start);
        
        return nextLine;
    }
    
    private Date parseDate(String line)
    {
        int start = "2 DATE ".length();
        line = line.substring(start);
        
        Date date = null;

        try
        {
            date = dateFormat.parse(line);
        }
        catch(ParseException e)
        {
            //System.out.println("Unparsable date: " + nextLine);
        }
        
        return date;
    }

    /**
     *
     * @return
     */
    public HashMap<String, Person> getEntry()
    {
        return entry;
    }
    
    /**
     *
     * @param args
     */
    private static final String propertyFile = "C:\\Program Files\\Apache Software Foundation\\Apache Tomcat 8.0.15\\bin\\database.properties";
    
    public static void main(String[] args)
    {
        Database.setProperties(propertyFile);
        
        String path = "C:\\Users\\Joe\\Documents\\";
        String file = "hendrixfamily.fte.GED";
        
        String outFile = "outFile.txt";
        
        try
        {
            out = new PrintStream(path + outFile);
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
            out = System.out;
        }
            
        try
        {
            GedcomImporter importer = new GedcomImporter(path + file);
            importer.importData();
            
            HashMap<String, Person> entry = importer.getEntry();
        
            System.out.println(entry.size() + " people loaded.");
        }
        catch (FileNotFoundException | SQLException ex)
        {
            ex.printStackTrace();
        }
        
        out.close();
    }
}
