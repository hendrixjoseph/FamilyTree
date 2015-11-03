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
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    @Ignore
    public void birthTest()
    {
        TypedQuery<Birth> birthQuery = em.createNamedQuery(Birth.FIND_ALL, Birth.class);
        List<Birth> births = birthQuery.getResultList();

        outputEvents(Birth.FIND_ALL, births);
    }

    @Test
    @Ignore
    public void deathTest()
    {
        TypedQuery<Death> deathQuery = em.createNamedQuery(Death.FIND_ALL, Death.class);
        List<Death> deaths = deathQuery.getResultList();

        outputEvents(Death.FIND_ALL, deaths);
    }

    @Test
    public void burialTest()
    {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Burial> q = criteriaBuilder.createQuery(Burial.class);
        Root<Burial> c = q.from(Burial.class);
        q.select(c).orderBy(criteriaBuilder.asc(c.get("place").get("name")));

        TypedQuery<Burial> query = em.createQuery(q);
        List<Burial> results = query.getResultList();

        outputEvents("burial test", results);
    }

    private void outputEvents(String testName, List<? extends Event> events)
    {
        StringBuilder sb = new StringBuilder(testName);

        for ( Event event : events )
        {
            sb.append("\n").append(event.getClass().getSimpleName());
            sb.append("\t").append(event.getDateString());
            sb.append("\t").append(event.getPlace());
        }

        LOG.log(Level.INFO, sb.toString());
    }
}