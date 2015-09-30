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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class PersonTest
{

    private static final Logger LOG = Logger.getLogger(PersonTest.class.getName());

    private static EntityManagerFactory emf;

    @BeforeClass
    public static void setUpClass()
    {
        emf = Persistence.createEntityManagerFactory("edu.wright.hendrix11.familyTree");
    }

    @AfterClass
    public static void tearDownClass()
    {
        if (emf != null)
        {
            emf.close();
        }
    }

    private EntityManager em;

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
//    @Ignore
    public void test()
    {
        Person person = em.find(Person.class, 9512);

        StringBuilder sb = new StringBuilder();

        sb.append("\n\tID:\t").append(person.getId());
        sb.append("\n\tNAME:\t").append(person.getName());
        sb.append("\n\tBIRTH:\t").append(person.getBirth().getDate());
        sb.append("\n\tGENDER:\t").append(person.getGender().getFullWord());
        sb.append("\n\tFATHER:\t").append(person.getFather().getName());
        sb.append("\n\tMOTHER:\t").append(person.getMother().getName());

        sb.append("\n\tSPOUSES:\t");

        for (Person spouse : person.getSpouses())
        {
            sb.append(spouse.getName()).append(", ");
        }

        sb.append("\n\tCHILDREN:\t");

        for (Person child : person.getChildren())
        {
            sb.append(child.getName()).append(", ");
        }

        // NullPointerException if it don't exist!
//        sb.append("\n\tDEATH:\t").append(person.getDeath().getDate());
        LOG.log(Level.INFO, sb.toString());
    }

}
