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
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Joe Hendrix
 */
@Named
@SessionScoped
public class IndividualBean implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Person person;

    EntityManager em;

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        // This is ugly - we'll use injection later
        em = Persistence.createEntityManagerFactory("edu.wright.hendrix11.familyTree").createEntityManager();
    }

    /**
     *
     * @return
     */
    public Person getPerson()
    {
        return person;
    }

    /**
     *
     * @return
     */
    public int getPersonId()
    {
        return person.getId();
    }

    /**
     *
     * @param id
     */
    public void setPersonId(int id)
    {
        person = em.find(Person.class, id);
    }
}
