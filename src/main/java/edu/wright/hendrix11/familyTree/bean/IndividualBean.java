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

import edu.wright.hendrix11.familyTree.entity.*;

import javax.annotation.*;
import javax.faces.view.*;
import javax.inject.*;
import javax.persistence.*;
import java.io.*;

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
     */
    @PostConstruct
    public void initialize()
    {
        person = em.find(Person.class, 9510);
        person.setName("JOeeee");
    }

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
     * @return
     */
    public int getPersonId()
    {
        return 1;//person.getId();
    }

    /**
     *
     * @param id
     */
    public void setPersonId(int id)
    {
        person = em.find(Person.class, id);
    }

    public void updatePerson()
    {
        em.getTransaction().commit();
    }

    public void insertPerson(Person person)
    {
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
    }
}
