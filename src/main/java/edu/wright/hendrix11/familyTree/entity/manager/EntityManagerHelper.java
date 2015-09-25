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

import edu.wright.hendrix11.familyTree.entity.manager.EntityManagerInjector;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Joe Hendrix
 */
@Stateful
public class EntityManagerHelper implements Serializable
{
    private EntityManagerFactory emf;
    private EntityManager em;

    @PostConstruct
    public void initialize()
    {
        emf = Persistence.createEntityManagerFactory("edu.wright.hendrix11.familyTree");
        em = emf.createEntityManager();
    }

    @PreDestroy
    public void destroy()
    {
        emf.close();
    }

    @Produces
    @EntityManagerInjector
    public EntityManager getEntityManager()
    {
//        emf = Persistence.createEntityManagerFactory("edu.wright.hendrix11.familyTree");
//        em = emf.createEntityManager();
        return em;
    }
}
