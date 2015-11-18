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

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OrderBy;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

import static javax.persistence.CascadeType.ALL;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@MappedSuperclass
public abstract class Event
{
    private boolean about = false;
    private Integer day;
    @Enumerated
    private Month month;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "PLACE_ID")
    @OrderBy("name")
    private Place place;
    private Integer year;

    /**
     * Returns the date the event happened. Keep in mind this might be and approximate date, and not all fields may
     * actually be known. Also, it is recommended to get the formatted date string from {@link #getDateString()} rather
     * than just formatting the date this method returns.
     *
     * @return the date the event happened
     *
     * @see #getDateString
     * @see #isAbout
     */
    public Date getDate()
    {
        return null;
    }

    /**
     * Returns the place this event happened. This event also happened in all regions of this place.
     *
     * @return the place this event happened
     *
     * @see Place#getRegion()
     * @see Place#iterator()
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
     * Returns the day of the month. If the day is not known, it will return {@code null}.
     *
     * @return the day of the month
     */
    public Integer getDay()
    {
        return day;
    }

    /**
     * Sets the day of the month.
     *
     * @param day the day of the month
     */
    public void setDay(Integer day)
    {
        this.day = day;
    }

    /**
     * Returns the month of the year. If the month is not known, it will return {@code null}.
     *
     * @return the month of the year
     */
    public Month getMonth()
    {
        return month;
    }

    /**
     * Sets the month of the year.
     *
     * @param month the month of the year
     */
    public void setMonth(Month month)
    {
        this.month = month;
    }

    /**
     * Returns the year. If the year is not known, it will return {@code null}.
     *
     * @return the year
     */
    public Integer getYear()
    {
        return year;
    }

    /**
     * Sets the year.
     *
     * @param year the year
     */
    public void setYear(Integer year)
    {
        this.year = year;
    }

    /**
     * Constructs and returns the date string.
     *
     * @return the formatted date string
     */
    public String getDateString()
    {
        StringBuilder sb = new StringBuilder();

        if ( month != null )
            sb.append(month.getDisplayName(TextStyle.SHORT, Locale.US));

        if ( month != null && ( day != null || year != null ) )
            sb.append(" ");

        if ( day != null )
            sb.append(day);

        if ( day != null && year != null )
            sb.append(", ");

        if ( year != null )
            sb.append(year);

        return sb.toString();
    }
}
