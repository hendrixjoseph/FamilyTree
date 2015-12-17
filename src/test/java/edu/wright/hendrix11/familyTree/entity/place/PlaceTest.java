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

package edu.wright.hendrix11.familyTree.entity.place;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
public class PlaceTest
{

    private static final Logger LOG = Logger.getLogger(PlaceTest.class.getName());

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeClass
    public static void setUpClass()
    {
        emf = Persistence.createEntityManagerFactory("edu.wright.hendrix11.familyTree.test");
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void tearDownClass()
    {
        if ( emf != null )
        {
            emf.close();
        }
    }

    @Before
    public void setUp()
    {

    }

    @After
    public void tearDown()
    {

    }

    @Test
    public void cityTest()
    {
        TypedQuery<City> cityQuery = em.createNamedQuery(City.FIND_ALL, City.class);
        List<City> cities = cityQuery.getResultList();

        outputPlaces(City.FIND_ALL, cities);
    }

    @Test
    public void countyTest()
    {
        TypedQuery<County> countyQuery = em.createNamedQuery(County.FIND_ALL, County.class);
        List<County> counties = countyQuery.getResultList();

        outputPlaces(County.FIND_ALL, counties);
    }

    @Test
    public void countryTest()
    {
        TypedQuery<Country> countryQuery = em.createNamedQuery(Country.FIND_ALL, Country.class);
        List<Country> countries = countryQuery.getResultList();

        outputPlaces("countryTest", countries);
    }

    @Test
    public void stateTest()
    {
        TypedQuery<State> stateQuery = em.createNamedQuery(State.FIND_ALL, State.class);
        List<State> states = stateQuery.getResultList();

        outputPlaces(State.FIND_ALL, states);
    }

    @Test
    public void placeTest()
    {
        TypedQuery<Place> placeQuery = em.createNamedQuery(Place.FIND_ALL, Place.class);
        List<Place> places = placeQuery.getResultList();

        outputPlaces(Place.FIND_ALL, places);
    }

    private void outputPlaces(String testName, List<? extends Place> places)
    {
        StringBuilder sb = new StringBuilder(testName);

        for ( Place place : places )
        {
            sb.append("\n").append(place);
        }

        LOG.log(Level.INFO, sb.toString());
    }
}