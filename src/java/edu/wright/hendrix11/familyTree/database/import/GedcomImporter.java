
package edu.wright.hendrix11.familyTree.database.imports;

import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.entity.PersonInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class GedcomImporter extends Importer
{
    private static final DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
    
    private static final String propertyFile = "C:\\Program Files\\Apache Software Foundation\\Apache Tomcat 8.0.15\\bin\\database.properties";
    
    private HashMap<String, Person> entry = new HashMap<String, Person>();
    
    private PersonData personData;
    
    /**
     *
     * @param filename
     */
    public void import()
    {
        personData = new PersonData();

        while(in.hasNext())
        {
            //read single line, put in string
            String nextLine = in.nextLine();
            
            int start;
                
            if(nextLine.startsWith("0 @I"))//nextLine.contains("INDI") && nextLine.contains("@"))
            {                
                String gedcomid = getId(nextLine);

                Person person = new Person();
                PersonInfo personInfo = new PersonInfo();

                nextLine = in.nextLine();
                out.println(nextLine);

                while(in.hasNext() && !nextLine.startsWith("0 @I"))//!nextLine.contains("INDI") && !nextLine.endsWith("FAM"))
                {  
                    String change = nextLine;
                    
                    nextLine = processPerson(in, person, nextLine);
                    nextLine = processPersonInfo(in, personInfo, nextLine);
                    
                    if(change.equals(nextLine))
                    {
                        out.println(nextLine);
                        nextLine = in.nextLine();                        
                    }
                }
                
                entry.put(gedcomid, person);
                //personData.insert(person);
            }
        }
            
               
    }
    
    private String processPersonInfo(Scanner in, PersonInfo personInfo, String nextLine)
    {
        int start;
        
        if(nextLine.contains("BURI"))
        {
            nextLine = in.nextLine();
            
            while(nextLine.contains("PLAC"))
            {
                if(nextLine.contains("PLAC"))
                {
                    start = "2 PLAC ".length();
                    nextLine = nextLine.substring(start);

                    personInfo.setPlaceOfBurial(nextLine);
                }
                
                nextLine = in.nextLine();
            }
        }
        else if(nextLine.contains("SSN"))
        {
            nextLine = in.nextLine();
            
            while(nextLine.contains("PLAC"))
            {
                if(nextLine.contains("PLAC"))
                {
                    start = "2 PLAC ".length();
                    nextLine = nextLine.substring(start);

                    personInfo.setSsn(nextLine);
                }
                
                nextLine = in.nextLine();
            }
        }
        
        return nextLine;
    }
    
    private String processPerson(Scanner in, Person person, String nextLine)
    {        
        int start;
        
        if(nextLine.contains("NAME"))
        {
            start = "1 NAME ".length();
            String name = nextLine.substring(start);
            person.setName(name.replace("/", ""));
            nextLine = in.nextLine();
        }
        else if(nextLine.contains("SEX"))
        {
            if(nextLine.endsWith("M"))
                person.setGender("Male");
            else if(nextLine.endsWith("F"))
                person.setGender("Female");
            
            nextLine = in.nextLine();
        }
        else if(nextLine.contains("BIRT"))
        {
            nextLine = in.nextLine();

            while(nextLine.contains("DATE") || nextLine.contains("PLAC"))
            {
                if(nextLine.contains("DATE"))
                {
                    start = "2 DATE ".length();
                    nextLine = nextLine.substring(start);

                    try
                    {
                        person.setDateOfBirth(dateFormat.parse(nextLine));
                    }
                    catch(ParseException e)
                    {
                        System.out.println("Unparsable date: " + nextLine);
                    }
                }

                if(nextLine.contains("PLAC"))
                {
                    start = "2 PLAC ".length();
                    nextLine = nextLine.substring(start);

                    person.setPlaceOfBirth(nextLine); 
                }
                
                nextLine = in.nextLine();
            }
        }
        else if(nextLine.contains("DEAT"))
        {
            nextLine = in.nextLine();

            while(nextLine.contains("DATE") || nextLine.contains("PLAC"))
            {
                if(nextLine.contains("DATE"))
                {
                    start = "2 DATE ".length();
                    nextLine = nextLine.substring(start);

                    try
                    {
                        person.setDateOfDeath(dateFormat.parse(nextLine));
                    }
                    catch(ParseException e)
                    {
                        System.out.println("Unparsable date: " + nextLine);
                    }
                }

                if(nextLine.contains("PLAC"))
                {
                    start = "2 PLAC ".length();
                    nextLine = nextLine.substring(start);

                    person.setPlaceOfDeath(nextLine); 
                }
                
                nextLine = in.nextLine();
            }
        }
        else
        {
            //nextLine = in.nextLine();
        }
        
        return nextLine;
    }
    
    private String getId(String line)
    {
        int start = line.indexOf("@") + 1;
        int end = line.indexOf("@", start + 1);
        return line.substring(start, end);
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
            
        GedcomImporter importer = new GedcomImporter(path + file);
        
        HashMap<String, Person> entry = importer.getEntry();
        
        System.out.println(entry.size() + " people loaded.");
        
        out.close();
    }
}
