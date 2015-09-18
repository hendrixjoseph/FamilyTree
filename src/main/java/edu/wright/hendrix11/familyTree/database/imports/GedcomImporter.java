/*
 *  The MIT License (MIT)
 *
 *  View the full license at:
 *  https://github.com/hendrixjoseph/FamilyTree/blob/master/LICENSE.md
 *
 *  Copyright (c) 2015 Joseph Hendrix
 *
 *  Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
 *
 */
package edu.wright.hendrix11.familyTree.database.imports;

import edu.wright.hendrix11.familyTree.database.Database;
import edu.wright.hendrix11.familyTree.entity.Marriage;
import edu.wright.hendrix11.familyTree.entity.PersonView;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class GedcomImporter extends Importer
{

    private static final DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

    private static final String INDI_LINE = "0 @I";

    private static final String NAME_LINE = "1 NAME ";
    private static final String GENDER_LINE = "1 SEX ";
    private static final String BIRTH_LINE = "1 BIRT";
    private static final String DEATH_LINE = "1 DEAT";
    private static final String DATE_LINE = "2 DATE ";
    private static final String PLAC_LINE = "2 PLAC ";

    private static final String FAM_LINE = "0 @F";

    private static final String HUSB_LINE = "1 HUSB";
    private static final String WIFE_LINE = "1 WIFE";
    private static final String CHIL_LINE = "1 CHIL";
    private static final String MARR_LINE = "1 MARR";

    private static PrintStream out;
    /**
     *
     * @param args
     */
    private static final String propertyFile = "C:\\Program Files\\Apache Software Foundation\\Apache Tomcat 8.0.15\\bin\\database.properties";

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
        
        try
        {
            GedcomImporter importer = new GedcomImporter(path + file);
            importer.importData();
            
            HashMap<String, PersonView> entry = importer.getEntry();
            
            System.out.println(entry.size() + " people loaded.");
        }
        catch (FileNotFoundException | SQLException ex)
        {
            ex.printStackTrace();
        }
        
        out.close();
    }

    private boolean insertPersonMode;
    private boolean insertFamilyMode;
    private boolean insertMarriageMode;
    private boolean insertBirthMode;
    private boolean insertDeathMode;

    private HashMap<String, PersonView> entry;
    private HashMap<PersonView, String> reverseEntry;

    /**
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    public GedcomImporter(String fileName) throws FileNotFoundException
    {
        super(fileName);
        initializeEntry();
    }

    /**
     *
     * @param in
     */
    public GedcomImporter(Scanner in)
    {
        super(in);
        initializeEntry();
    }

    private void initializeEntry()
    {
        entry = new HashMap<String, PersonView>();
        reverseEntry = new HashMap<PersonView, String>();
    }

    /**
     *
     *
     */
    @Override
    protected void processData()
    {
        setAllModes(false);

        String nextLine = "";

        // Get to the first person
        while (hasNext(!nextLine.startsWith(INDI_LINE)))
        {
            nextLine = getNextLine(nextLine);
        }

        PersonView person = new PersonView();

        List<PersonView> children = new ArrayList<PersonView>();
        Marriage marriage = new Marriage();

        String gedcomid = "";

        while (in.hasNext())
        {
            if (nextLine.startsWith(INDI_LINE))
            {
                if (insertPersonMode)
                {
                    insertPerson(gedcomid, person);
                }

                this.setInsertPersonMode(true);

                person = new PersonView();
                gedcomid = getId(nextLine);
            }
            else if (nextLine.startsWith(FAM_LINE))
            {
                if (insertFamilyMode)
                {
                    insertFamily(marriage, children);
                }

                this.setInsertFamilyMode(true);

                children = new ArrayList<PersonView>();
            }

            if (insertPersonMode)
            {
                processPerson(person, nextLine);
            }
            else if (insertFamilyMode)
            {
                processFamily(marriage, children, nextLine);
            }

            nextLine = in.nextLine();
        }

        if (insertPersonMode)
        {
            insertPerson(gedcomid, person);
        }
        else if (insertFamilyMode)
        {
            insertFamily(marriage, children);
        }
    }

    private void insertFamily(Marriage marriage, List<PersonView> children)
    {
        for (PersonView child : children)
        {
            if (marriage.hasHusband())
            {
                child.setFather(marriage.getHusband());
            }

            if (marriage.hasWife())
            {
                child.setMother(marriage.getWife());
            }

            String gedcomid = reverseEntry.get(child);
            child = personTable.update(child);
            setEntry(child, gedcomid);

            //System.out.println(gedcomid + " " +  child.getName() + " updated.");
            if (child.hasParents())
            {
                marriageTable.insert(marriage);
            }
        }
    }

    private void processFamily(Marriage marriage, List<PersonView> children, String nextLine)
    {
        if (nextLine.startsWith(HUSB_LINE))
        {
            String husbandId = getId(nextLine);
            PersonView husband = entry.get(husbandId);
            marriage.setHusband(husband);
        }
        else if (nextLine.startsWith(WIFE_LINE))
        {
            String wifeId = getId(nextLine);
            PersonView wife = entry.get(wifeId);
            marriage.setWife(wife);
        }
        else if (nextLine.startsWith(CHIL_LINE))
        {
            String childId = getId(nextLine);
            PersonView child = entry.get(childId);
            children.add(child);
        }
        else if (nextLine.startsWith(MARR_LINE))
        {
            this.setInsertMarriageMode(true);
        }
        else if (nextLine.startsWith(DATE_LINE))
        {
            Date date = parseDate(nextLine);

            if (date != null)
            {
                if (insertMarriageMode)
                {
                    marriage.setAnniversary(date);
                }
            }
        }
        else if (nextLine.startsWith(PLAC_LINE))
        {
            String plac = parsePlac(nextLine);

            if (insertMarriageMode)
            {
                marriage.setPlace(plac);
            }

            setSubModes(false);
        }
        else
        {
            // Discard pile
            out.println(nextLine);
        }
    }

    private void insertPerson(String gedcomid, PersonView person)
    {
        System.out.println("Attempting to insert " + person.getName());
        person = personTable.insert(person);
        setEntry(person, gedcomid);
        System.out.println(gedcomid + " " + person.getName() + " inserted.");
    }

    private void processPerson(PersonView person, String nextLine)
    {
        if (nextLine.startsWith(NAME_LINE))
        {
            String name = nextLine.substring(NAME_LINE.length()).replaceAll("/", "");

            person.setName(name);
        }
        else if (nextLine.startsWith(GENDER_LINE))
        {
            String gender = nextLine.substring(GENDER_LINE.length());

            if (gender.equals("M"))
            {
                person.setGender("Male");
            }
            else if (gender.equals("F"))
            {
                person.setGender("Female");
            }
        }
        else if (nextLine.startsWith(BIRTH_LINE))
        {
            this.setInsertBirthMode(true);
        }
        else if (nextLine.startsWith(DEATH_LINE))
        {
            this.setInsertDeathMode(true);
        }
        else if (nextLine.startsWith(DATE_LINE))
        {
            Date date = parseDate(nextLine);

            if (date != null)
            {
                if (insertBirthMode)
                {
                    person.setDateOfBirth(date);
                }
                else if (insertDeathMode)
                {
                    person.setDateOfDeath(date);
                }
            }
        }
        else if (nextLine.startsWith(PLAC_LINE))
        {
            String plac = parsePlac(nextLine);

            if (insertBirthMode)
            {
                person.setPlaceOfBirth(plac);
            }
            else if (insertDeathMode)
            {
                person.setPlaceOfDeath(plac);
            }

            setSubModes(false);
        }
        else
        {
            setSubModes(false);
            // Discard pile...
            out.println(nextLine);
        }
    }

    private void setAllModes(boolean mode)
    {
        insertPersonMode = mode;
        insertFamilyMode = mode;
        setSubModes(mode);
    }

    private void setSubModes(boolean mode)
    {
        insertBirthMode = mode;
        insertDeathMode = mode;
        insertMarriageMode = mode;
    }

    private boolean hasNext(boolean condition)
    {
        return in.hasNext() && condition;
    }

    private String getNextLine(String nextLine)
    {
        if (in.hasNext())
        {
            nextLine = in.nextLine();
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
        return nextLine.substring(PLAC_LINE.length());
    }

    private Date parseDate(String line)
    {
        line = line.substring(DATE_LINE.length());

        Date date = null;

        try
        {
            date = dateFormat.parse(line);
        }
        catch (ParseException e)
        {
            //System.out.println("Unparsable date: " + nextLine);
        }

        return date;
    }

    private void setInsertPersonMode(boolean insertPersonMode)
    {
        setAllModes(false);
        this.insertPersonMode = insertPersonMode;
    }

    private void setInsertFamilyMode(boolean insertFamilyMode)
    {
        setAllModes(false);
        this.insertFamilyMode = insertFamilyMode;
    }

    private void setInsertBirthMode(boolean insertBirthMode)
    {
        setSubModes(false);
        this.insertBirthMode = insertBirthMode;
    }

    private void setInsertDeathMode(boolean insertDeathMode)
    {
        setSubModes(false);
        this.insertDeathMode = insertDeathMode;
    }

    private void setInsertMarriageMode(boolean insertMarriageMode)
    {
        setSubModes(false);
        this.insertMarriageMode = insertMarriageMode;
    }

    private void setEntry(PersonView person, String gedcomid)
    {
        entry.put(gedcomid, person);
        reverseEntry.put(person, gedcomid);
    }

    /**
     *
     * @return
     */
    public HashMap<String, PersonView> getEntry()
    {
        return entry;
    }

}
