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

import java.util.Date;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Entity
@IdClass(MarriagePK.class)
public class Marriage
{

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HUSBAND")
    private Person husband;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WIFE")
    private Person wife;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "PLACE")
    private Place place;

    @Temporal(TemporalType.DATE)
    private Date anniversary;

    /**
     *
     * @return
     */
    public Person getHusband()
    {
        return husband;
    }

    /**
     *
     * @param husband
     */
    public void setHusband(Person husband)
    {
        this.husband = husband;
    }

    /**
     *
     * @return
     */
    public Person getWife()
    {
        return wife;
    }

    /**
     *
     * @param wife
     */
    public void setWife(Person wife)
    {
        this.wife = wife;
    }

    /**
     *
     * @return
     */
    public Place getPlace()
    {
        return place;
    }

    /**
     *
     * @param place
     */
    public void setPlace(Place place)
    {
        this.place = place;
    }

    /**
     *
     * @return
     */
    public Date getAnniversary()
    {
        return anniversary;
    }

    /**
     *
     * @param anniversary
     */
    public void setAnniversary(Date anniversary)
    {
        this.anniversary = anniversary;
    }

}
