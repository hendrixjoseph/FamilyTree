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

import edu.wright.hendrix11.familyTree.entity.Gender;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class GenderBeanTest
{
    private static final Logger LOG = Logger.getLogger(GenderBeanTest.class.getName());
    public static GenderBean bean;

    @BeforeClass
    public static void setUpClass()
    {
        bean = new GenderBean();
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
    public void testGetGenders()
    {
        List<Gender> genders = bean.getGenders();

        StringBuilder sb = new StringBuilder();

        for(Gender gender : genders)
            sb.append("\n\t").append(gender.getAbbr()).append(":\t").append(gender);

        LOG.log(Level.INFO, sb.toString());
    }

}
