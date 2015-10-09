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

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
@Named
@ViewScoped
public class IndividualBean implements Serializable
{

    private static final Logger LOG = Logger.getLogger(IndividualBean.class.getName());

    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;
    private Person person;

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        TypedQuery<Person> personQuery = em.createNamedQuery(Person.FIND_FIRST, Person.class);
        person = personQuery.getSingleResult();
    }

    /**
     * @return
     */
    public int getPersonId()
    {
        return person.getId();
    }

    /**
     * @param personId
     */
    public void setPersonId(int personId)
    {
        person = em.find(Person.class, personId);
    }

    /**
     * @return
     */
    public Person getPerson()
    {
        return person;
    }

    /**
     * @param person
     */
    public void setPerson(Person person)
    {
        this.person = person;
    }

    /**
     * @param person
     */
    public void insertPerson(Person person)
    {
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
    }

    /**
     * 
     */
    public void updatePerson()
    {
        em.getTransaction().commit();
    }
}
