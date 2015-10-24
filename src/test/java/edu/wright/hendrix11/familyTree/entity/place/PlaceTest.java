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
import java.util.logging.Logger;

import static org.junit.Assert.*;

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
        emf = Persistence.createEntityManagerFactory("edu.wright.hendrix11.familyTree");
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
    }
}