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

import javax.persistence.PersistenceException;
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

    private Country getCountry(String name)
    {
        TypedQuery<Country> countryQuery = em.createNamedQuery(Country.FIND_BY_NAME, Country.class);
        List<Country> countryList = countryQuery.setParameter("name", name).getResultList();
        Country country;

        if ( countryList.isEmpty() )
        {
            country = new Country();
            country.setName(name);
            em.persist(country);
        }
        else
        {
            country = countryList.get(0);
        }

        return country;
    }

    private List<County> getCounty(String name)
    {
        List<County> countyList = new ArrayList<>();

        TypedQuery<County> countyQuery = em.createNamedQuery(County.FIND_BY_NAME, County.class);

        try
        {
            countyList = countyQuery.setParameter("name", name).getResultList();
        }
        catch(PersistenceException e)
        {
            if(!(e.getCause() instanceof NullPointerException))
            {
                throw e;
            }
        }

        if ( countyList.isEmpty() )
        {
            County county = new County();
            county.setName(name);
            em.persist(county);
            countyList.add(county);
        }

        return countyList;
    }

    private County getCounty(String name, String stateName)
    {
        List<County> countyList = getCounty(name);

        for ( County c : countyList )
        {
            State s = c.getState();

            if ( s != null )
            {
                if ( s.getName().equals(stateName) )
                    return c;
            }
            else
            {
                s = getState(stateName);
                c.setRegion(s);
                return c;
            }
        }

        return countyList.get(0);
    }

    private City getCity(String name)
    {
        TypedQuery<City> cityQuery = em.createNamedQuery(City.FIND_BY_NAME, City.class);
        List<City> cityList = cityQuery.setParameter("name", name).getResultList();
        City city;

        if ( cityList.isEmpty() )
        {
            city = new City();
            city.setName(name);
            em.persist(city);
        }
        else
        {
            city = cityList.get(0);
        }

        return city;
    }

    private State getState(String name)
    {
        TypedQuery<State> stateQuery = em.createNamedQuery(State.FIND_BY_NAME, State.class);
        stateQuery.setParameter("name", name);
        List<State> stateList = stateQuery.getResultList();
        State state;

        if ( stateList.isEmpty() )
        {
            state = new State();
            state.setName(name);
            state.setRegion(getCountry("USA"));
            em.persist(state);
        }
        else
        {
            state = stateList.get(0);
        }

        return state;
    }

    private Place makeNewPlace(String[] names)
    {
        Place[] places = new Place[names.length];

        for(int i = names.length - 1; i >= 0; i--)
        {
            if(i + 1 == names.length) // Last one, probably a state
            {
                places[i] = getState(names[i]);
            }
            else
            {
                List<? extends Place> list;
                Place newPlace;

                if(names[i].contains("Cemetery"))
                {
                    list = em.createNamedQuery(Cemetery.FIND_BY_NAME, Cemetery.class).setParameter("name", names[i]).getResultList();
                    newPlace = new Cemetery(names[i]);
                }
                else if(names[i].contains("County"))
                {
                    list = getCounty(names[i]);
                    newPlace = new County(names[i]);
                }
                else
                {
                    list = em.createNamedQuery(City.FIND_BY_NAME, City.class).setParameter("name", names[i]).getResultList();
                    newPlace = new City(names[i]);
                }

                if(!list.isEmpty())
                {
                    for(Place place : list)
                    {
                        if(place.getRegion() != null && place.getRegion().equals(places[i+1]))
                        {
                            newPlace = place;
                            break;
                        }
                    }
                }
                else
                {
                    newPlace.setRegion(places[i+1]);
                }

                places[i] = newPlace;
            }
        }

        return places[0];
    }

    private Place processPlace()
    {
        String[] info = datePlace.restOf(nextLine).split(",");

        Cemetery cemetery;
        City city;
        County county;
        State state;
        Country country;

        for ( int i = 0; i < info.length; i++ )
        {
            info[i] = info[i].trim();
        }

        if ( info.length == 1 )
        {
            if ( info[0].contains("County") )
            {
                return getCounty(info[0]).get(0);
            }
            else
            {
                for ( String knownCountry : KNOWN_COUNTRIES )
                {
                    if ( knownCountry.equals(info[0]) )
                    {
                        return getCountry(info[0]);
                    }
                }

                return getState(info[0]);
            }
        }
        else if ( info.length == 2 )
        {

            for ( String knownCountry : KNOWN_COUNTRIES )
            {
                if ( knownCountry.equals(info[1]) )
                {
                    city = getCity(info[0]);

                    if ( city.getCountry() == null )
                    {
                        country = getCountry(info[1]);
                        city.setRegion(country);
                    }

                    return city;
                }
            }

            if ( info[0].contains("County") )
            {
                return getCounty(info[0], info[1]);
            }
            else
            {
                city = getCity(info[0]);

                if ( city.getState() == null )
                {
                    state = getState(info[1]);
                    city.setRegion(state);
                }

                return city;
            }
        }
        else // if length >= 3
        {
            return makeNewPlace(info);
        }
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
