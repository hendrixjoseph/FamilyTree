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

package edu.wright.hendrix11.familyTree.entity.event;

import org.junit.AfterClass;
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
public class EventTest
{
    private static final Logger LOG = Logger.getLogger(EventTest.class.getName());

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

    @Test
    public void birthTest()
    {
        TypedQuery<Birth> birthQuery = em.createNamedQuery(Birth.FIND_ALL, Birth.class);
        List<Birth> births = birthQuery.getResultList();
    }
}