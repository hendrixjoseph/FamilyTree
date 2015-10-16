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

import edu.wright.hendrix11.familyTree.entity.Birth;
import edu.wright.hendrix11.familyTree.entity.Death;
import edu.wright.hendrix11.familyTree.entity.Person;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
@Named
@ViewScoped
public class PersistPersonBean implements Serializable
{

    private static final Logger LOG = Logger.getLogger(PersistPersonBean.class.getName());

    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    private Person person;
    private Person tempPerson;

    @PostConstruct
    public void initialize()
    {
        tempPerson = new Person();
        tempPerson.setBirth(new Birth());
        tempPerson.setDeath(new Death());
    }

    public void insertPerson()
    {
    }

    public void updatePerson()
    {
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;

        tempPerson.setName(person.getName());
        tempPerson.setGender(person.getGender());

        if ( person.getBirth() != null )
        {
            tempPerson.setBirth(person.getBirth());
        }
        else
        {
            tempPerson.setBirth(new Birth());
        }

        if ( person.getDeath() != null )
        {
            tempPerson.setDeath(person.getDeath());
        }
        else
        {
            tempPerson.setDeath(new Death());
        }
    }

    public Person getTempPerson()
    {
        return tempPerson;
    }

    public void setTempPerson(Person tempPerson)
    {
        this.tempPerson = tempPerson;
    }

    public String getGender()
    {
        if ( tempPerson.getGender() != null )
            return tempPerson.getGender().getFullWord();
        else
            return "notSelected";
    }

    public void setGender(String gender)
    {

    }
}
