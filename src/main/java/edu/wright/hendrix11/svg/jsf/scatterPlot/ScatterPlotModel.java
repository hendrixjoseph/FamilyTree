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
public class ScatterPlotModel<X extends Number & Comparable<X>,Y extends Number & Comparable<Y>> extends GenericModel
{
    private Map<X,Y> data;

    public Map<X, Y> getData()
    {
        return data;
    }

    public void setData(Map<X, Y> data)
    {
        this.data = data;
    }

}
