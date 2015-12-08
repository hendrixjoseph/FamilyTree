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

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import edu.wright.hendrix11.familyTree.entity.place.Cemetery;
import edu.wright.hendrix11.familyTree.entity.place.City;
import edu.wright.hendrix11.familyTree.entity.place.Country;
import edu.wright.hendrix11.familyTree.entity.place.County;
import edu.wright.hendrix11.familyTree.entity.place.Place;
import edu.wright.hendrix11.familyTree.entity.place.State;

import javax.persistence.EntityManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
public abstract class Importer
{
    private static final Logger LOG = Logger.getLogger(Importer.class.getName());
    /**
     *
     */
    protected FileReader file;
    /**
     *
     */
    protected EntityManager em;
    /**
     *
     */
    protected String nextLine = "";
    /**
     *
     */
    protected int lineNumber;
    private Multimap<String, Cemetery> cemeteries = ArrayListMultimap.create();
    private Multimap<String, City> cities = ArrayListMultimap.create();
    private Multimap<String, County> counties = ArrayListMultimap.create();
    private HashMap<String, Country> countries = new HashMap<>();
    private Multimap<String, State> states = ArrayListMultimap.create();

    /**
     * @param fileName
     *
     * @throws FileNotFoundException
     */
    public Importer(String fileName) throws FileNotFoundException
    {
        this(new FileReader(fileName));
    }

    /**
     * @param file
     */
    public Importer(FileReader file)
    {
        this.file = file;
    }

    /**
     * @param entityManager
     */
    public void processData(EntityManager entityManager)
    {
        this.em = entityManager;
        entityManager.getTransaction().begin();

        try
        {
            processData();
        }
        catch ( Exception e )
        {
            LOG.log(Level.SEVERE, String.format("Failed on line %d: %s", lineNumber, nextLine));
            throw e;
        }

        entityManager.getTransaction().commit();
    }

    /**
     *
     */
    protected abstract void processData();

    /**
     * @param name
     *
     * @return
     */
    protected Country getCountry(String name)
    {
        Country country = countries.get(name);

        if ( country == null )
        {
            country = new Country();
            country.setName(name);
            countries.put(name, country);
            em.persist(country);
        }

        return country;
    }

    /**
     * @param name
     * @param region
     *
     * @return
     */
    protected County getCounty(String name, State region)
    {
        Collection<County> countyList = counties.get(name);

        for ( County county : countyList )
        {
            if ( county.getRegion().equals(region) )
            {
                return county;
            }
        }

        County county = new County(name);
        county.setRegion(region);
        counties.put(name, county);
        return county;
    }

    /**
     * @param name
     * @param region
     *
     * @return
     */
    protected City getCity(String name, Place region)
    {
        Collection<City> cityList = cities.get(name);

        for ( City city : cityList )
        {
            if ( city.getRegion().equals(region) )
            {
                return city;
            }
        }

        City city = new City(name);
        city.setRegion(region);
        cities.put(name, city);
        return city;
    }

    /**
     * @param name
     * @param region
     *
     * @return
     */
    protected Cemetery getCemetery(String name, Place region)
    {
        Collection<Cemetery> cemeteryList = cemeteries.get(name);

        for ( Cemetery cemetery : cemeteryList )
        {
            if ( cemetery.getRegion().equals(region) )
            {
                return cemetery;
            }
        }

        Cemetery cemetery = new Cemetery(name);
        cemetery.setRegion(region);
        cemeteries.put(name, cemetery);
        return cemetery;
    }

    /**
     * @param name
     *
     * @return
     */
    protected State getState(String name)
    {
        Collection<State> stateList = states.get(name);

        State state;

        if ( stateList.isEmpty() )
        {
            state = new State();
            state.setName(name);
            state.setRegion(getCountry("USA"));
            states.put(name, state);
            em.persist(state);
        }
        else
        {
            state = stateList.iterator().next();
        }

        return state;
    }

    protected Place getState(String name, Country region)
    {
        Collection<State> stateList = states.get(name);

        for ( State state : stateList )
        {
            if ( state.getRegion().equals(region) )
            {
                return state;
            }
        }

        State state = new State(name);
        state.setRegion(region);
        states.put(name, state);
        return state;
    }

    /**
     *
     */
    protected enum KnownCountry
    {

        /**
         *
         */
        Mexico,

        /**
         *
         */
        Germany,

        /**
         *
         */
        USA,

        /**
         *
         */
        Ireland,

        /**
         *
         */
        England,

        /**
         *
         */
        Spain,

        South_Wales;

        @Override
        public String toString()
        {
            return name().replaceAll("_", " ");
        }
    }

}
