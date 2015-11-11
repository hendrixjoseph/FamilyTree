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
 * @author Joe Hendrix
 */
public abstract class ChartModel<N extends Number>
{
    private static final Logger LOG = Logger.getLogger(ChartModel.class.getName());
    private String title;
    private String xAxisLabel;
    private String yAxisLabel;

    public abstract Map<String, ?> getData();

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getxAxisLabel()
    {
        return xAxisLabel;
    }

    public void setxAxisLabel(String xAxisLabel)
    {
        this.xAxisLabel = xAxisLabel;
    }

    public String getyAxisLabel()
    {
        return yAxisLabel;
    }

    public void setyAxisLabel(String yAxisLabel)
    {
        this.yAxisLabel = yAxisLabel;
    }

    public abstract Set<String> getAxisLabels();

    public abstract Integer getNumLabels();

    public abstract Integer getNumValues();

    public abstract N getMax();
}
