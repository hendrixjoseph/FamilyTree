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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class PersonViewTest
{

    private static EntityManagerFactory emf;

    @BeforeClass
    public static void setUpClass()
    {
        emf = Persistence.createEntityManagerFactory("edu.wright.hendrix11.familyTree");
    }

    @AfterClass
    public static void tearDownClass()
    {
        emf.close();
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
    public void test()
    {
        PersonView person = em.find(PersonView.class, 9512);

        System.out.println(person.getId());
        System.out.println(person.getName());
        System.out.println(person.getGender());
        System.out.println(person.getFather().getName());
        System.out.println(person.getMother().getName());

        for (PersonView spouse : person.getSpouses())
        {
            System.out.println(spouse.getName());
        }

        for (PersonView child : person.getChildren())
        {
            System.out.println(child.getName());
        }
    }

}
