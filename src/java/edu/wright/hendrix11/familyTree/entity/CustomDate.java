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

    public boolean isAbout()
    {
        return about;
    }

    public void setAbout(boolean about)
    {
        this.about = about;
    }

    public boolean isYearKnown()
    {
        return yearKnown;
    }

    public void setYearKnown(boolean yearKnown)
    {
        this.yearKnown = yearKnown;
    }

    public boolean isMonthKnown()
    {
        return monthKnown;
    }

    public void setMonthKnown(boolean knowMonth)
    {
        this.monthKnown = knowMonth;
    }

    public boolean isDayKnown()
    {
        return dayKnown;
    }

    public void setDayKnown(boolean dayKnown)
    {
        this.dayKnown = dayKnown;
    }
