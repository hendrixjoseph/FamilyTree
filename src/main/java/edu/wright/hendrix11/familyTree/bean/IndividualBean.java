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
import edu.wright.hendrix11.familyTree.entity.manager.EntityManagerInjector;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 *
 * @author Joe Hendrix
 */
@Named
@ViewScoped
public class IndividualBean implements Serializable
{

    private Person person;

    @Inject @EntityManagerInjector
    private transient EntityManager em;

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        // This is ugly - we'll use injection later
        //em = Persistence.createEntityManagerFactory("edu.wright.hendrix11.familyTree").createEntityManager();
        person = em.find(Person.class, 9510);
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
}
