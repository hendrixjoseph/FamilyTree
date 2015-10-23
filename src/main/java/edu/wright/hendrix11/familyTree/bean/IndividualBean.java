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

import edu.wright.hendrix11.familyTree.dataBean.PersonDataBean;
import edu.wright.hendrix11.familyTree.entity.Birth;
import edu.wright.hendrix11.familyTree.entity.Death;
import edu.wright.hendrix11.familyTree.entity.Person;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
@Named
@ViewScoped
public class IndividualBean implements Serializable
{

    private static final Logger LOG = Logger.getLogger(IndividualBean.class.getName());
    @EJB
    private PersonDataBean personDataBean;
    private Person person;
    private Person tempPerson;

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        person = personDataBean.findFirst();

        tempPerson = new Person();
        tempPerson.setName(person.getName());
        tempPerson.setGender(person.getGender());

        if ( person.getBirth() != null )
            tempPerson.setBirth(person.getBirth());
        else
            tempPerson.setBirth(new Birth());

        if ( person.getDeath() != null )
            tempPerson.setDeath(person.getDeath());
        else
            tempPerson.setDeath(new Death());
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
        person = personDataBean.find(personId);
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

    public Person getTempPerson()
    {
        return tempPerson;
    }

    public void setTempPerson(Person tempPerson)
    {
        this.tempPerson = tempPerson;
    }

    public void updatePerson()
    {
        LOG.log(Level.SEVERE, "Still developing!! " + tempPerson.getName());

        person.setName(tempPerson.getName());
        person.setGender(tempPerson.getGender());

        personDataBean.update(person);
    }
}
