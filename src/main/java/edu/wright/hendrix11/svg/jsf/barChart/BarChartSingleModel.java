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

package edu.wright.hendrix11.svg.jsf.barChart;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @param <N>
 *
 * @author Joe Hendrix
 */
public class BarChartSingleModel<N extends Number & Comparable<N>> extends BarChartModel<N>
{
    private Map<String, N> data = new LinkedHashMap<>();

    @Override
    public Map<String, N> getData()
    {
        return data;
    }

    public void setData(Map<String, N> data)
    {
        this.data = data;
    }

    /**
     * @param label
     *
     * @return
     */
    @Override
    public N getData(String label)
    {
        return data.get(label);
    }

    /**
     * @return
     */
    @Override
    public Set<String> getAxisLabels()
    {
        return data.keySet();
    }

    /**
     * @return
     */
    @Override
    public Integer getNumValues()
    {
        return data.size();
    }

    /**
     * @return
     */
    @Override
    public N getMax()
    {
        N max = null;

        for ( N number : data.values() )
        {
            if ( max == null )
            {
                max = number;
            }
            else
            {
                max = max.compareTo(number) < 0 ? number : max;
            }
        }

        return max;
    }
}
