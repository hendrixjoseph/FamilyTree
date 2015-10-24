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

import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.entity.place.Place;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

import static javax.persistence.CascadeType.ALL;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Entity
public class Death implements Event
{

    @Temporal(TemporalType.DATE)
    @Column(name = "ANNIVERSARY")
    private Date date;
    @Id
    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "PLACE_ID")
    private Place place;

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
     * @return
     */
    @Override
    public Place getPlace()
    {
        return place;
    }

    /**
     * @param place
     */
    @Override
    public void setPlace(Place place)
    {
        this.place = place;
    }

    /**
     * @return
     */
    @Override
    public Date getDate()
    {
        return date;
    }

    /**
     * @param date
     */
    @Override
    public void setDate(Date date)
    {
        this.date = date;
    }

}