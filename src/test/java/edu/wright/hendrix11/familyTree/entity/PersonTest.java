/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class PersonTest
{
    private static EntityManagerFactory emf;

    private EntityManager em;

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
        Person person = em.find(Person.class, 9510);

        System.out.println(person.getId());
        System.out.println(person.getName());
        System.out.println(person.getGender());
        System.out.println(person.getFather().getName());
        System.out.println(person.getMother().getName());
    }

}
