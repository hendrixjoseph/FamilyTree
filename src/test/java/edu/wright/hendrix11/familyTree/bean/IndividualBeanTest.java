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
package edu.wright.hendrix11.familyTree.bean;

import edu.wright.hendrix11.familyTree.entity.Person;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class IndividualBeanTest
{

    private static final Logger LOG = Logger.getLogger(IndividualBeanTest.class.getName());
    public static IndividualBean bean;

    @BeforeClass
    public static void setUpClass()
    {
        bean = new IndividualBean();
        bean.initialize();
    }

    @AfterClass
    public static void tearDownClass()
    {
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
    public void testGetPerson()
    {
        Person person = bean.getPerson();

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

        LOG.log(Level.INFO, sb.toString());
    }
}
