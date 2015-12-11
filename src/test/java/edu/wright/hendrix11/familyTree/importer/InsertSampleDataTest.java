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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.wright.hendrix11.familyTree.entity.Gender;
import edu.wright.hendrix11.familyTree.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
public class InsertSampleDataTest
{    private static final Logger LOG = Logger.getLogger(InsertSampleDataTest.class.getName());

    private static EntityManagerFactory emf;
    private EntityManager em;

    @BeforeClass
    public static void setUpClass()
    {
        emf = Persistence.createEntityManagerFactory("edu.wright.hendrix11.familyTree.test");
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
        em = emf.createEntityManager();
    }

    @After
    public void tearDown()
    {
        em.close();
    }

    @Test
    public void insertPerson()
    {
        em.getTransaction().begin();

        Person person = new Person();
        person.setName("Joe Hendrix");
        person.setGender(Gender.MALE);
        em.persist(person);
        person = new Person();
        person.setName("Beth Wirick");
        person.setGender(Gender.FEMALE);
        em.persist(person);

        em.getTransaction().commit();
    }
}
