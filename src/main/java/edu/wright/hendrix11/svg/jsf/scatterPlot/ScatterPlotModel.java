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

package edu.wright.hendrix11.svg.jsf.scatterPlot;

import edu.wright.hendrix11.svg.jsf.GenericModel;

import java.util.Map;

/**
 * @author Joe Hendrix
 */
public class ScatterPlotModel extends GenericModel
{
    private Map<? extends Number, ? extends Number> data;

    /**
     * @return
     */
    public Map<? extends Number, ? extends Number> getData()
    {
        return data;
    }

    /**
     * @param data
     */
    public void setData(Map<? extends Number, ? extends Number> data)
    {
        this.data = data;
    }
}
