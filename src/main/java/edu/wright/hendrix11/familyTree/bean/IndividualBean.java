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

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import edu.wright.hendrix11.familyTree.entity.Person;

/**
 * @author Joe Hendrix
 */
@Named
@ViewScoped
public class IndividualBean implements Serializable
{

    private Person person;

    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    /**
     *
     * @return
     */
    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    /**
     *
     * @param id
     */
    public void setPersonId(int id)
    {
        person = em.find(Person.class, id);
    }

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        TypedQuery<Person> personQuery = em.createNamedQuery(Person.FIND_FIRST, Person.class);
        person = personQuery.getSingleResult();
    }

    public void insertPerson(Person person)
    {
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
    }

    public void updatePerson()
    {
        em.getTransaction().commit();
    }
}
