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

import edu.wright.hendrix11.familyTree.entity.place.Cemetery;
import edu.wright.hendrix11.familyTree.entity.place.City;
import edu.wright.hendrix11.familyTree.entity.place.Country;
import edu.wright.hendrix11.familyTree.entity.place.County;
import edu.wright.hendrix11.familyTree.entity.place.Place;
import edu.wright.hendrix11.familyTree.entity.place.State;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
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

    /**
     * @param name
     * @param region
     *
     * @return
     */
    protected County getCounty(String name, State region)
    {
        TypedQuery<County> countyQuery = em.createNamedQuery(County.FIND_BY_NAME, County.class);
        List<County> countyList = countyQuery.setParameter("name", name).getResultList();

        for ( County county : countyList )
        {
            if ( county.getRegion().equals(region) )
            {
                return county;
            }
        }

        County county = new County(name);
        county.setRegion(region);
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
        TypedQuery<City> cityQuery = em.createNamedQuery(City.FIND_BY_NAME, City.class);
        List<City> cityList = cityQuery.setParameter("name", name).getResultList();

        for ( City city : cityList )
        {
            if ( city.getRegion().equals(region) )
            {
                return city;
            }
        }

        City city = new City(name);
        city.setRegion(region);
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
        TypedQuery<Cemetery> cemeteryQuery = em.createNamedQuery(Cemetery.FIND_BY_NAME, Cemetery.class);
        List<Cemetery> cemeteryList = cemeteryQuery.setParameter("name", name).getResultList();

        for ( Cemetery cemetery : cemeteryList )
        {
            if ( cemetery.getRegion().equals(region) )
            {
                return cemetery;
            }
        }

        Cemetery cemetery = new Cemetery(name);
        cemetery.setRegion(region);
        return cemetery;
    }

    /**
     * @param name
     *
     * @return
     */
    protected State getState(String name)
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
        Spain
    }

}
