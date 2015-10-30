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
     * Returns the date the event happened. Keep in mind this might be and approximate date, or not all fields may
     * actually be known. Also, it is recommended to get the formatted date string from {@link #getDateString()} rather
     * than just formatting the date this method returns, since {@link #getDateString()} takes into account the boolean
     * attributes.
     *
     * @return the date the event happened
     *
     * @see #getDateString()
     * @see #isYearKnown()
     * @see #isMonthKnown()
     * @see #isDayKnown()
     * @see #isAbout
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * Set the date the event happened. Keep in mind this might be and approximate date, or not all fields may actually
     * be known, so set the other fields accordingly.
     *
     * @param date the date the event happened
     *
     * @see #setYearKnown()
     * @see #setMonthKnown()
     * @see #setDayKnown()
     * @see #setAbout
     */
    public void setDate(Date date)
    {
        this.date = date;
    }

    /**
     * Returns the place this event happened. This event also happened in all regions of this place.
     *
     * @return the place this event happened
     *
     * @see edu.wright.hendrix11.familyTree.entity.place.Place#getRegion()
     * @see edu.wright.hendrix11.familyTree.entity.place.Place#getPlaces()
     */
    public Place getPlace()
    {
        return place;
    }

    /**
     * Sets the place this event happened. This event also happened in all regions of this place.
     *
     * @param place the place this event happened
     */
    public void setPlace(Place place)
    {
        this.place = place;
    }

    /**
     * Returns {@code true} if the year is known. Sometimes not all information about the date of an event is known.
     *
     * @return {@code true} if the year is known
     */
    public boolean isYearKnown()
    {
        return yearKnown;
    }

    /**
     * Sets the year is known or not. Sometimes not all information about the date of an event is known.
     *
     * @param yearKnown whether the year is known or not
     */
    public void setYearKnown(boolean yearKnown)
    {
        this.yearKnown = yearKnown;
    }

    /**
     * Returns {@code true} if the date is just an approximate.
     *
     * @return {@code true} if the date is just an approximate
     */
    public boolean isAbout()
    {
        return about;
    }

    /**
     * Set if the date is an approximate date.
     *
     * @param about if the date is an approximate date
     */
    public void setAbout(boolean about)
    {
        this.about = about;
    }

    /**
     * Returns {@code true} if the day is known. Sometimes not all information about the date of an event is known.
     *
     * @return {@code true} if the day is known
     */
    public boolean isDayKnown()
    {
        return dayKnown;
    }

    /**
     * Sets the day is known or not. Sometimes not all information about the date of an event is known.
     *
     * @param dayKnown whether the day is known or not
     */
    public void setDayKnown(boolean dayKnown)
    {
        this.dayKnown = dayKnown;
    }

    /**
     * Returns {@code true} if the month is known. Sometimes not all information about the date of an event is known.
     *
     * @return {@code true} if the month is known
     */
    public boolean isMonthKnown()
    {
        return monthKnown;
    }

    /**
     * Sets the month is known or not. Sometimes not all information about the date of an event is known.
     *
     * @param monthKnown whether the month is known or not
     */
    public void setMonthKnown(boolean monthKnown)
    {
        this.monthKnown = monthKnown;
    }

    /**
     * Constructs and returns the date string. This method is better than just formatting the value from {@link
     * #getDate()} since it takes into account the values from {@link #isMonthKnown()}, {@link #isDayKnown()}, {@link
     * #isYearKnown()}, and {@link #isAbout()}.
     *
     * @return the formatted date string
     */
    public String getDateString()
    {
        StringBuilder sb = new StringBuilder();

        if ( date != null )
        {
            if ( about )
                sb.append("About ");

            if ( monthKnown )
                sb.append(MONTH_FORMAT.format(date));

            if ( monthKnown && dayKnown )
                sb.append(" ");

            if ( monthKnown && !dayKnown && yearKnown )
                sb.append(", ");

            if ( dayKnown )
                sb.append(DAY_FORMAT.format(date));

            if ( dayKnown && yearKnown )
                sb.append(", ");

            if ( yearKnown )
                sb.append(YEAR_FORMAT.format(date));
        }

        return sb.toString();
    }
}
