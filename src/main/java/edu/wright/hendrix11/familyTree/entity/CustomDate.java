/* 
 *  The MIT License (MIT)
 *  
 *  Copyright (c) 2015 Joseph Hendrix
 *  
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
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
