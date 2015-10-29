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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.persistence.CascadeType.ALL;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@MappedSuperclass
public abstract class Event
{
    private static final DateFormat MONTH_FORMAT = new SimpleDateFormat("MMM");
    private static final DateFormat DAY_FORMAT = new SimpleDateFormat("d");
    private static final DateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");
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

    /**
     *
     * @return
     */
    public Date getDate()
    {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(Date date)
    {
        this.date = date;
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
    public boolean isYearKnown()
    {
        return yearKnown;
    }

    /**
     *
     * @param yearKnown
     */
    public void setYearKnown(boolean yearKnown)
    {
        this.yearKnown = yearKnown;
    }

    /**
     *
     * @return
     */
    public boolean isAbout()
    {
        return about;
    }

    /**
     *
     * @param about
     */
    public void setAbout(boolean about)
    {
        this.about = about;
    }

    /**
     *
     * @return
     */
    public boolean isDayKnown()
    {
        return dayKnown;
    }

    /**
     *
     * @param dayKnown
     */
    public void setDayKnown(boolean dayKnown)
    {
        this.dayKnown = dayKnown;
    }

    /**
     *
     * @return
     */
    public boolean isMonthKnown()
    {
        return monthKnown;
    }

    /**
     *
     * @param monthKnown
     */
    public void setMonthKnown(boolean monthKnown)
    {
        this.monthKnown = monthKnown;
    }

    /**
     *
     * @return
     */
    public String getDateString()
    {
        StringBuilder sb = new StringBuilder();

        if(date != null)
        {
            if(about)
                sb.append("About ");

            if ( monthKnown )
                sb.append(MONTH_FORMAT.format(date));

            if(monthKnown && dayKnown)
                sb.append(" ");

            if(monthKnown && !dayKnown && yearKnown)
                sb.append(", ");

            if(dayKnown)
                sb.append(DAY_FORMAT.format(date));

            if(dayKnown && yearKnown)
                sb.append(", ");

            if(yearKnown)
                sb.append(YEAR_FORMAT.format(date));
        }

        return sb.toString();
    }
}
