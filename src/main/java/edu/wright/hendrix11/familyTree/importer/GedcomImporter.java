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
import edu.wright.hendrix11.familyTree.entity.event.Death;
import edu.wright.hendrix11.familyTree.entity.event.Event;
import edu.wright.hendrix11.familyTree.entity.event.Marriage;
import edu.wright.hendrix11.familyTree.entity.place.City;
import edu.wright.hendrix11.familyTree.entity.place.Country;
import edu.wright.hendrix11.familyTree.entity.place.County;
import edu.wright.hendrix11.familyTree.entity.place.Place;
import edu.wright.hendrix11.familyTree.entity.place.State;

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

    private static final DateFormat FULL_DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy");
    private static final String FULL_DATE_REGEX = "[0-9]{1,2}[ A-z]+[0-9]{4}";
    private static final DateFormat MONTH_YEAR_ONLY_FORMAT = new SimpleDateFormat("MMM yyyy");
    private static final String MONTH_YEAR_ONLY_REGEX = "[A-z]+ [0-9]{4}";
    private static final DateFormat MONTH_DAY_ONLY_FORMAT = new SimpleDateFormat("dd MMM");
    private static final String MONTH_DAY_ONLY_REGEX = "[0-9]{1,2} [A-z]+";
    private static final DateFormat YEAR_ONLY_FORMAT = new SimpleDateFormat("yyyy");
    private static final String YEAR_ONLY_REGEX = "[0-9]{4}";

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
     * @param entityManager
     */
    @Override
    public void processData(EntityManager entityManager)
    {
        this.em = entityManager;
        entityManager.getTransaction().begin();
        processData();
        entityManager.getTransaction().commit();
    }

    @Override
    protected void processData()
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
        TypedQuery<County> countyQuery = em.createNamedQuery(County.FIND_BY_NAME, County.class);
        List<County> countyList = countyQuery.setParameter("name", name).getResultList();

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

        for(County c : countyList)
        {
            State s = c.getState();

            if(s != null)
            {
                if(s.getName().equals(stateName))
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

    private Place processPlace()
    {
        String[] info = datePlace.restOf(nextLine).split(",");

        City city;
        County county;
        State state;
        Country country;

        for(int i = 0; i < info.length; i++)
        {
            info[i] = info[i].trim();
        }

        if ( info.length == 1 )
        {
            if ( info[0].contains("County") )
            {
                return getCounty(info[0]).get(0);
            }
            else if( "USA".equals(info[0]))
            {
                return getCountry(info[0]);
            }
            else
            {
                return getState(info[0]);
            }
        }
        else if ( info.length == 2 )
        {
            if ( "Mexico".equals(info[1]) || "Germany".equals(info[1]) )
            {
                city = getCity(info[0]);

                if ( city.getCountry() == null )
                {
                    country = getCountry(info[1]);
                    city.setRegion(country);
                }

                return city;
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
        else if ( info.length == 3 )
        {
            city = getCity(info[0]);
            county = getCounty(info[1],info[2]);
            city.setRegion(county);

            return city;
        }
        else // length == 4
        {
            LOG.log(Level.SEVERE, "Places of length > 3 not implemented yet: {0)", nextLine);
            return null;
        }
    }

    private void processEventDate(Event event)
    {
        String dateString = datePlace.restOf(nextLine);

        event.setAbout(dateString.contains("ABT"));

        dateString = dateString.replace("ABT ", "");

        Date date = null;

        try
        {
            if ( dateString.matches(FULL_DATE_REGEX) )
            {
                date = FULL_DATE_FORMAT.parse(dateString);
                event.setDayKnown(true);
                event.setMonthKnown(true);
                event.setYearKnown(true);
            }
            else if ( dateString.matches(MONTH_YEAR_ONLY_REGEX) )
            {
                date = MONTH_YEAR_ONLY_FORMAT.parse(dateString);
                event.setDayKnown(false);
                event.setMonthKnown(true);
                event.setYearKnown(true);
            }
            else if ( dateString.matches(MONTH_DAY_ONLY_REGEX) )
            {
                date = MONTH_DAY_ONLY_FORMAT.parse(dateString);
                event.setDayKnown(true);
                event.setMonthKnown(true);
                event.setYearKnown(false);
            }
            else if ( dateString.matches(YEAR_ONLY_REGEX) )
            {
                date = YEAR_ONLY_FORMAT.parse(dateString);
                event.setDayKnown(false);
                event.setMonthKnown(false);
                event.setYearKnown(true);
            }
            else
            {
                throw new ParseException(dateString, 0);
            }
        }
        catch ( ParseException e )
        {
            StringBuilder sb = new StringBuilder(( e.getClass().getName() ));
            sb.append(e.getMessage());

            LOG.log(Level.SEVERE, sb.toString());
        }

        event.setDate(date);
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
                Gender gender;

                gender = em.find(Gender.class, info.charAt(0));

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
