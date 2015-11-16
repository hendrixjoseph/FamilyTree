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

package edu.wright.hendrix11.svg.jsf;

/**
 * @author Joe Hendrix
 */
public abstract class GenericModel
{
    private String title;
    private String xAxisLabel;
    private String yAxisLabel;

    /**
     * @return
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return
     */
    public String getxAxisLabel()
    {
        return xAxisLabel;
    }

    /**
     * @param xAxisLabel
     */
    public void setxAxisLabel(String xAxisLabel)
    {
        this.xAxisLabel = xAxisLabel;
    }

    /**
     * @return
     */
    public String getyAxisLabel()
    {
        return yAxisLabel;
    }

    /**
     * @param yAxisLabel
     */
    public void setyAxisLabel(String yAxisLabel)
    {
        this.yAxisLabel = yAxisLabel;
    }
}
