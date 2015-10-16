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

import edu.wright.hendrix11.familyTree.entity.compositeKey.MarriagePK;
import edu.wright.hendrix11.familyTree.entity.event.Event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

import static javax.persistence.CascadeType.ALL;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Entity
@IdClass(MarriagePK.class)
public class Marriage implements Event
{

    @Temporal(TemporalType.DATE)
    @Column(name = "ANNIVERSARY")
    private Date date;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HUSBAND")
    private Person husband;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "PLACE_ID")
    private Place place;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WIFE")
    private Person wife;

    /**
     * @return
     */
    public Person getHusband()
    {
        return husband;
    }

    /**
     * @param husband
     */
    public void setHusband(Person husband)
    {
        this.husband = husband;
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

    /**
     * @return
     */
    public Person getWife()
    {
        return wife;
    }

    /**
     * @param wife
     */
    public void setWife(Person wife)
    {
        this.wife = wife;
    }

    /**
     * @return
     */
    public boolean hasHusband()
    {
        return hasSpouse(husband);
    }

    /**
     * @return
     */
    public boolean hasWife()
    {
        return hasSpouse(wife);
    }

    private boolean hasSpouse(Person spouse)
    {
        return spouse != null;
    }

}