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
package edu.wright.hendrix11.familyTree.importer;

import edu.wright.hendrix11.familyTree.entity.Marriage;
import edu.wright.hendrix11.familyTree.entity.Person;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class GedcomImporter extends Importer
{

    private static final Logger LOG = Logger.getLogger(GedcomImporter.class.getName());

    private static final DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

    // Just here to allow compilation til I'm done.
    Scanner in;

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

    private enum Mode 
    { 
        PERSON("0 @I"), FAMILY("0 @F"), MARRIAGE("1 MARR"), NONE("NONE");
        
        private final String string;

        /**
         * @param string
         */
        private Mode(final String string)
        {
            this.string = string;
        }
        
        public static Mode getMode(String string)
        {
            for(Mode mode : Mode.values())
            {
                if(mode.contains(string))
                    return mode;
            }
            
            return this;
        }
        
        private boolean contains(String string)
        {
            return string.contains(this.string);
        }
    }
    
    private enum LineType 
    { 
		NAME("1 NAME "),
		GENDER("1 SEX "),
		BIRTH("1 BIRT"),
		DEATH("1 DEAT"),
		DATE("2 DATE "),
		PLACE("2 PLAC "),	
		HUSB("1 HUSB"),
		WIFE("1 WIFE"),
		CHILD("1 CHIL"),
		MARR("1 MARR");
        
        private final String string;

        /**
         * @param string
         */
        private LineType(final String string)
        {
            this.string = string;
        }
        
        public LineType getLineType(String string)
        {
            for(LineType lineType : LineType.values())
            {
                if(lineType.contains(string))
                    return lineType;
            }
            
            return null;
        }
        
        private boolean contains(String string)
        {
            return string.contains(this.string);
        }
    }

    private boolean insertPersonMode;
    private boolean insertFamilyMode;
    private boolean insertMarriageMode;
    private boolean insertBirthMode;
    private boolean insertDeathMode;

    private HashMap<String, Person> entry = new HashMap<>();
    private HashMap<Person, String> reverseEntry = new HashMap<>();

    public GedcomImporter(String fileName) throws FileNotFoundException
    {
        super(fileName);
    }

    {
        processData();
    }

    /**
     *
     *
     */
    @Override
    protected void processData()
    {
        try(LineNumberReader in = new LineNumberReader(file))
        {
            while(!in.readLine().contains(INDI_LINE))
            {
            }

            String nextLine;
            Mode mode = Mode.NONE;
            
            while((nextLine = in.readLine()) != null)
            {
                mode = mode.getMode(nextLine);
    
                switch(mode)
                {
                    case PERSON:
    
                        break;
                    case FAMILY:
    
                        break;
                    case MARRIAGE:
    
                        break;
                    default:
                        // Do nothing
                }
            }
        }
        catch (IOException e)
        {
            LOG.log(Level.SEVERE, e.getClass().getName(), e);
        }

        LOG.log(Level.INFO, "Done!");
    }

    private void temp()
    {
        setAllModes(false);

        String nextLine = "";

        // Get to the first person
        while (hasNext(!nextLine.startsWith(INDI_LINE)))
        {
            nextLine = getNextLine(nextLine);
        }

        Person person = new Person();

        List<Person> children = new ArrayList<Person>();
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

                person = new Person();
                gedcomid = getId(nextLine);
            }
            else if (nextLine.startsWith(FAM_LINE))
            {
                if (insertFamilyMode)
                {
                    insertFamily(marriage, children);
                }

                this.setInsertFamilyMode(true);

                children = new ArrayList<Person>();
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

    private void insertFamily(Marriage marriage, List<Person> children)
    {
        for (Person child : children)
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
//            child = personTable.update(child);
            setEntry(child, gedcomid);

            //System.out.println(gedcomid + " " +  child.getName() + " updated.");
            if (child.hasParents())
            {
//                marriageTable.insert(marriage);
            }
        }
    }

    private void processFamily(Marriage marriage, List<Person> children, String nextLine)
    {
        if (nextLine.startsWith(HUSB_LINE))
        {
            String husbandId = getId(nextLine);
            Person husband = entry.get(husbandId);
            marriage.setHusband(husband);
        }
        else if (nextLine.startsWith(WIFE_LINE))
        {
            String wifeId = getId(nextLine);
            Person wife = entry.get(wifeId);
            marriage.setWife(wife);
        }
        else if (nextLine.startsWith(CHIL_LINE))
        {
            String childId = getId(nextLine);
            Person child = entry.get(childId);
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
//                marriage.setPlace(plac);
            }

            setSubModes(false);
        }
        else
        {
            // Discard pile
//            out.println(nextLine);
        }
    }

    private void insertPerson(String gedcomid, Person person)
    {
        System.out.println("Attempting to insert " + person.getName());
//        person = personTable.insert(person);
        setEntry(person, gedcomid);
        System.out.println(gedcomid + " " + person.getName() + " inserted.");
    }

    private void processPerson(Person person, String nextLine)
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
//                person.setGender("Male");
            }
            else if (gender.equals("F"))
            {
//                person.setGender("Female");
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
//                    person.setDateOfBirth(date);
                }
                else if (insertDeathMode)
                {
//                    person.setDateOfDeath(date);
                }
            }
        }
        else if (nextLine.startsWith(PLAC_LINE))
        {
            String plac = parsePlac(nextLine);

            if (insertBirthMode)
            {
//                person.setPlaceOfBirth(plac);
            }
            else if (insertDeathMode)
            {
//                person.setPlaceOfDeath(plac);
            }

            setSubModes(false);
        }
        else
        {
            setSubModes(false);
            // Discard pile...
//            out.println(nextLine);
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

    private void setEntry(Person person, String gedcomid)
    {
        entry.put(gedcomid, person);
        reverseEntry.put(person, gedcomid);
    }

    /**
     *
     * @return
     */
    public HashMap<String, Person> getEntry()
    {
        return entry;
    }

}
