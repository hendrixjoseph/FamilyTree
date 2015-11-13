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

import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @param <N>
 *
 * @author Joe Hendrix
 */
public abstract class ChartModel<N extends Number & Comparable>
{
    private static final Logger LOG = Logger.getLogger(ChartModel.class.getName());
    private String title;
    private String xAxisLabel;
    private String yAxisLabel;

    /**
     * @return
     */
    public abstract Map<String, ?> getData();

    /**
     * @param <N>
     * @param label
     *
     * @return
     */
    public abstract <N> N getData(String label);

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

    /**
     * @return
     */
    public abstract Set<String> getAxisLabels();

    /**
     * @return
     */
    public Integer getNumLabels()
    {
        return getAxisLabels().size();
    }

    /**
     * @return
     */
    public abstract Integer getNumValues();

    /**
     * @return
     */
    public abstract N getMax();
}
