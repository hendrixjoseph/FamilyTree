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

/**
 *
 * @author Joseph Hendrix
 */
public class CustomDate extends Date
{
    private boolean about;
    private boolean yearKnown;
    private boolean monthKnown;
    private boolean dayKnown;

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
    public boolean isMonthKnown()
    {
        return monthKnown;
    }

    /**
     *
     * @param knowMonth
     */
    public void setMonthKnown(boolean knowMonth)
    {
        this.monthKnown = knowMonth;
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
}
