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

import edu.wright.hendrix11.familyTree.entity.Birth;
import edu.wright.hendrix11.familyTree.entity.Death;
import edu.wright.hendrix11.familyTree.entity.Gender;
import edu.wright.hendrix11.familyTree.entity.Marriage;
import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.entity.Place;
import edu.wright.hendrix11.familyTree.entity.event.Event;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class GedcomImporter extends Importer
{

    private static final Logger LOG = Logger.getLogger(GedcomImporter.class.getName());

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy");
    private Mode datePlace = Mode.NONE;
    private Mode familyInfo = Mode.NONE;
    private Mode inserting = Mode.NONE;
    private List<Marriage> marriages = new ArrayList<>();
    private String nextLine = "";
    private HashMap<String, Person> people = new HashMap<>();
    private Mode personInfo = Mode.NONE;

    /**
     * @param fileName
     *
     * @throws FileNotFoundException
     */
    public GedcomImporter(String fileName) throws FileNotFoundException
    {
        super(fileName);
    }

    /**
     *
     *
     */
    @Override
    public void processData()
    {
        try ( LineNumberReader in = new LineNumberReader(file) )
        {
            while ( !in.readLine().startsWith(Mode.PERSON.toString()) )
            {
            }

            Person person = null;
            Person husband = null;
            Person wife = null;
            Person child = null;
            Marriage marriage = null;

            while ( ( nextLine = in.readLine() ) != null )
            {
                inserting = inserting.getInserting(nextLine);
                personInfo = personInfo.getPersonInfoType(nextLine);
                familyInfo = familyInfo.getFamilyInfoType(nextLine);
                datePlace = datePlace.getDatePlace(nextLine);

                switch ( inserting )
                {
                    case NEW_PERSON:
                        person = new Person();

                        String[] split = nextLine.split("@");
                        String id = split[1];

                        people.put(id, person);
                        break;
                    case PERSON:
                        processPerson(person);
                        break;
                    case NEW_FAMILY:
                        husband = null;
                        wife = null;
                        child = null;
                        marriage = null;
                        break;
                    case FAMILY:
                        switch ( familyInfo )
                        {
                            case HUSB:
                                split = nextLine.split("@");
                                id = split[1];
                                husband = people.get(id);
                                break;
                            case WIFE:
                                split = nextLine.split("@");
                                id = split[1];
                                wife = people.get(id);
                                break;
                            case CHILD:
                                split = nextLine.split("@");
                                id = split[1];
                                child = people.get(id);

                                child.setFather(husband);
                                child.setMother(wife);
                                break;
                            case MARRIAGE:
                                if ( marriage == null )
                                {
                                    marriage = new Marriage();
                                    marriage.setHusband(husband);
                                    marriage.setWife(wife);
                                }

                                if ( !marriages.contains(marriage) )
                                {
                                    marriages.add(marriage);
                                }

                                processEvent(marriage);
                                break;
                        }
                        break;
                    default:
                        // Do nothing
                }
            }
        }
        catch ( IOException e )
        {
            LOG.log(Level.SEVERE, e.getClass().getName(), e);
        }

        LOG.log(Level.INFO, "Done! {0} people read in!", people.size());
        LOG.log(Level.INFO, "Done! {0} marriages read in!", marriages.size());
    }

    /**
     * @param em
     */
    @Override
    public void processData(EntityManager em)
    {
        this.em = em;
        em.getTransaction().begin();
        processData();
        em.getTransaction().commit();

        //        for (Person person : people.values())
        //        {
        //            em.getTransaction().begin();
        //            em.persist(person);
        //            em.getTransaction().commit();
        //        }
    }

    private Date processDate(String string)
    {
        try
        {
            return DATE_FORMAT.parse(string);
        }
        catch ( ParseException ex )
        {
            return null;
        }
    }

    private void processEvent(Event event)
    {
        String info = datePlace.restOf(nextLine);

        switch ( datePlace )
        {
            case DATE:
                event.setDate(processDate(info));
                break;
            case PLACE:
                Place place;

                if ( em != null )
                {
                    TypedQuery<Place> placeQuery = em.createNamedQuery(Place.FIND_BY_NAME, Place.class);
                    placeQuery.setParameter("name", info);
                    List<Place> placeList = placeQuery.getResultList();

                    if ( placeList.isEmpty() )
                    {
                        place = new Place();
                        place.setName(info);
                    }
                    else
                    {
                        place = placeList.get(0);
                    }
                }
                else
                {
                    place = new Place();
                    place.setName(info);
                }

                event.setPlace(place);
                break;
            case SOURCE:

                break;
        }
    }

    private void processPerson(Person person)
    {
        String info = personInfo.restOf(nextLine);

        switch ( personInfo )
        {
            case NAME:
                info = info.replaceAll("/", "");
                person.setName(info);

                personInfo = Mode.NONE;
                break;
            case GENDER:
                Gender gender;

                if ( em != null )
                    gender = em.find(Gender.class, info.charAt(0));
                else
                    gender = new Gender(info.charAt(0));

                person.setGender(gender);

                if ( em != null )
                    em.persist(person);

                personInfo = Mode.NONE;

                break;
            case BIRTH:
                if ( person.getBirth() == null )
                {
                    person.setBirth(new Birth());
                }

                processEvent(person.getBirth());
                break;
            case DEATH:
                if ( person.getDeath() == null )
                {
                    person.setDeath(new Death());
                }

                processEvent(person.getDeath());
                break;
            default:
                // Do nothing
        }

    }

    private enum Mode
    {

        NONE("NONE"),
        NEW_PERSON("0 @I"),
        PERSON(""),
        NEW_FAMILY("0 @F"),
        FAMILY(""),
        MARRIAGE("1 MARR"),
        NAME("1 NAME "),
        GENDER("1 SEX "),
        BIRTH("1 BIRT"),
        DEATH("1 DEAT"),
        HUSB("1 HUSB "),
        WIFE("1 WIFE "),
        CHILD("1 CHIL "),
        DATE("2 DATE "),
        PLACE("2 PLAC "),
        SOURCE("2 SOUR ");

        private final String string;

        /**
         * @param string
         */
        Mode(final String string)
        {
            this.string = string;
        }

        public Mode getDatePlace(String string)
        {
            Mode[] modes = {DATE, PLACE, SOURCE};

            return getMode(modes, string);
        }

        public Mode getFamilyInfoType(String string)
        {
            Mode[] modes = {HUSB, WIFE, CHILD, MARRIAGE};

            Mode mode = getMode(modes, string);

            if ( this == MARRIAGE && mode == NONE )
            {
                return MARRIAGE;
            }
            else
            {
                return mode;
            }
        }

        public Mode getInserting(String string)
        {
            Mode[] modes = {NEW_PERSON, NEW_FAMILY};

            Mode mode = getMode(modes, string);

            if ( this == NEW_PERSON && mode == NONE )
            {
                return PERSON;
            }
            else if ( this == NEW_FAMILY && mode == NONE )
            {
                return FAMILY;
            }
            else if ( mode == NONE )
            {
                return this;
            }
            else
            {
                return mode;
            }
        }

        public Mode getPersonInfoType(String string)
        {
            Mode[] modes = {NAME, GENDER, BIRTH, DEATH};

            if ( this == BIRTH || this == DEATH )
            {
                if ( string.startsWith("1") )
                {
                    return NONE;
                }
            }

            return getNotNoneMode(modes, string);
        }

        public boolean isStartOf(String string)
        {
            return string.startsWith(this.string);
        }

        public String restOf(String string)
        {
            if ( this.string.length() < string.length() )
            {
                return string.substring(this.string.length());
            }
            else
            {
                return "";
            }
        }

        private Mode getMode(Mode[] modes, String string)
        {
            for ( Mode mode : modes )
            {
                if ( mode.isStartOf(string) )
                {
                    return mode;
                }
            }

            return NONE;
        }

        private Mode getNotNoneMode(Mode[] modes, String string)
        {
            Mode mode = getMode(modes, string);

            return mode != NONE ? mode : this;
        }

        @Override
        public String toString()
        {
            return string;
        }
    }
}
