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

import edu.wright.hendrix11.familyTree.entity.Gender;
import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.entity.event.Birth;
import edu.wright.hendrix11.familyTree.entity.event.Burial;
import edu.wright.hendrix11.familyTree.entity.event.Death;
import edu.wright.hendrix11.familyTree.entity.event.Event;
import edu.wright.hendrix11.familyTree.entity.event.Marriage;
import edu.wright.hendrix11.familyTree.entity.place.Cemetery;
import edu.wright.hendrix11.familyTree.entity.place.City;
import edu.wright.hendrix11.familyTree.entity.place.Country;
import edu.wright.hendrix11.familyTree.entity.place.County;
import edu.wright.hendrix11.familyTree.entity.place.Place;
import edu.wright.hendrix11.familyTree.entity.place.State;

import javax.persistence.TypedQuery;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.LineNumberReader;
import java.time.Month;
import java.util.ArrayList;
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

    private Mode datePlace = Mode.NONE;
    private Mode familyInfo = Mode.NONE;
    private Mode inserting = Mode.NONE;
    private List<Marriage> marriages = new ArrayList<>();
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
     */
    @Override
    protected void processData()
    {
        try ( LineNumberReader in = new LineNumberReader(file) )
        {
            Person person = null;
            Person husband = null;
            Person wife = null;
            Person child = null;
            Marriage marriage = null;

            while ( ( nextLine = in.readLine() ) != null )
            {
                lineNumber = in.getLineNumber();

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
                                    em.persist(marriage);
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
                        outputUnusedLine(inserting);
                }
            }
        }
        catch ( IOException e )
        {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }

        LOG.log(Level.INFO, "Done! {0} people read in!", people.size());
        LOG.log(Level.INFO, "Done! {0} marriages read in!", marriages.size());
    }

    private Place processPlace()
    {
        String[] info = datePlace.restOf(nextLine).split(",");
        Place[] places = new Place[info.length];

        Cemetery cemetery;
        City city;
        County county;
        State state;
        Country country;

        boolean first = true;

        for ( int i = info.length - 1; i >= 0; i-- )
        {
            info[i] = info[i].trim();

            if ( first )
            {
                for ( KnownCountry knownCountry : KnownCountry.values() )
                {
                    if ( knownCountry.name().equals(info[i]) )
                    {
                        places[i] = getCountry(info[i]);
                    }
                }

                if ( places[i] == null )
                {
                    places[i] = getState(info[i]);
                }

                first = false;
            }
            else
            {
                if ( info[i].contains("Cemetery") )
                {
                    places[i] = getCemetery(info[i], places[i + 1]);
                }
                else if ( info[i].contains("County") || ( i == 1 && places.length == 3 ) || ( i == 2 && places.length == 4 ) )
                {
                    places[i] = getCounty(info[i], (State) places[i + 1]);
                }
                else
                {
                    places[i] = getCity(info[i], places[i + 1]);
                }
            }
        }

        return places[0];
    }

    private void processEventDate(Event event)
    {
        String dateString = datePlace.restOf(nextLine);

        event.setAbout(dateString.contains("ABT"));

        dateString = dateString.replace("ABT ", "");

        String[] tokens = dateString.split(" ");

        for ( String token : tokens )
        {
            if ( token.matches("[0-9]{1,2}") )
            {
                int number = Integer.parseInt(token);
                event.setDay(number);
            }
            else if ( token.matches("[0-9]{4}") )
            {
                int number = Integer.parseInt(token);
                event.setYear(number);
            }
            else
            {
                for ( Month month : Month.values() )
                {
                    if ( month.name().startsWith(token) )
                    {
                        event.setMonth(month);
                        break;
                    }
                }
            }
        }
    }

    private void processEvent(Event event)
    {
        switch ( datePlace )
        {
            case DATE:
                processEventDate(event);
                break;
            case PLACE:
                event.setPlace(processPlace());
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
                Gender gender = Gender.getEnum(info);
                person.setGender(gender);
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
            case BURIAL:
                if ( person.getBurial() == null )
                {
                    person.setBurial(new Burial());
                }

                processEvent(person.getBurial());
                break;
            default:
                if ( !nextLine.startsWith("1 FAM") )
                    outputUnusedLine(personInfo);
        }

    }

    private void outputUnusedLine(Mode mode)
    {
        LOG.log(Level.INFO, String.format("Mode %s: Line %d not read: %s", mode.name(), lineNumber, nextLine));
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
        BURIAL("1 BURI"),
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
            Mode[] modes = {NAME, GENDER, BIRTH, DEATH, BURIAL};

            if ( this == BIRTH || this == DEATH || this == BURIAL )
            {
                if ( string.startsWith("1") )
                {
                    Mode[] birthDeath = {BIRTH, DEATH, BURIAL};

                    return getMode(birthDeath, string);
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
