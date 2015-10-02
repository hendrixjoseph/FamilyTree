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

import java.io.FileNotFoundException;
import java.util.logging.Logger;
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
public class GedcomImporterTest
{
    private static final Logger LOG = Logger.getLogger(GedcomImporterTest.class.getName());

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
    public void test() throws FileNotFoundException
    {
        GedcomImporter importer = new GedcomImporter("hendrixfamily.fte.GED");
        importer.processData(em);
    }
}
