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

package edu.wright.hendrix11.familyTree.entity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class PersonTest
{

    private static final Logger LOG = Logger.getLogger(PersonTest.class.getName());

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
    public void countGenders()
    {
        TypedQuery<Long> countQuery = em.createNamedQuery(Person.COUNT_GENDERS, Long.class);
        countQuery.setParameter("gender",Gender.MALE);
        long count = countQuery.getSingleResult();

        LOG.log(Level.INFO, String.format("There are %d males.", count));

    }

    @Test
    @Ignore
    public void testFindFirst()
    {
        TypedQuery<Person> personQuery = em.createNamedQuery(Person.FIND_FIRST, Person.class);
        Person person = personQuery.getSingleResult();

        outputPerson(person);
    }

    @Test
    @Ignore
    public void test()
    {
        Person person = em.find(Person.class, 9512);
    }

    public void outputPerson(Person person)
    {
        StringBuilder sb = new StringBuilder();

        sb.append("\n\tID:\t").append(person.getId());
        sb.append("\n\tNAME:\t").append(person.getName());
        sb.append("\n\tBIRTH:\t").append(person.getBirth().getDate());
        sb.append("\n\tGENDER:\t").append(person.getGender());
        sb.append("\n\tFATHER:\t").append(person.getFather().getName());
        sb.append("\n\tMOTHER:\t").append(person.getMother().getName());

        sb.append("\n\tSPOUSES:\t");

        for ( Person spouse : person.getSpouses() )
        {
            sb.append(spouse.getName()).append(", ");
        }

        sb.append("\n\tCHILDREN:\t");

        for ( Person child : person.getChildren() )
        {
            sb.append(child.getName()).append(", ");
        }

        // NullPointerException if it don't exist!
        //        sb.append("\n\tDEATH:\t").append(person.getDeath().getDate());
        LOG.log(Level.INFO, sb.toString());
    }

}
