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

import edu.wright.hendrix11.familyTree.entity.place.Place;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

import static javax.persistence.CascadeType.ALL;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@MappedSuperclass
public abstract class Event
{
    private boolean about;
    @Temporal(TemporalType.DATE)
    @Column(name = "ANNIVERSARY")
    private Date date;
    @Column(name = "DAY_KNOWN")
    private boolean dayKnown;
    @Column(name = "MONTH_KNOWN")
    private boolean monthKnown;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "PLACE_ID")
    private Place place;
    @Column(name = "YEAR_KNOWN")
    private boolean yearKnown;

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Place getPlace()
    {
        return place;
    }

    public void setPlace(Place place)
    {
        this.place = place;
    }

    public boolean isYearKnown()
    {
        return yearKnown;
    }

    public void setYearKnown(boolean yearKnown)
    {
        this.yearKnown = yearKnown;
    }

    public boolean isAbout()
    {
        return about;
    }

    public void setAbout(boolean about)
    {
        this.about = about;
    }

    public boolean isDayKnown()
    {
        return dayKnown;
    }

    public void setDayKnown(boolean dayKnown)
    {
        this.dayKnown = dayKnown;
    }

    public boolean isMonthKnown()
    {
        return monthKnown;
    }

    public void setMonthKnown(boolean monthKnown)
    {
        this.monthKnown = monthKnown;
    }

    public String getDateString()
    {
        return "";
    }
}
