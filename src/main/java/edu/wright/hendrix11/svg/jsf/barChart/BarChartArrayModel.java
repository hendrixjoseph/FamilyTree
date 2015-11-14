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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @param <N>
 *
 * @author Joe Hendrix
 */
public class BarChartArrayModel<N extends Number & Comparable<N>> extends BarChartModel<N>
{
    private List<String> barLabels = new ArrayList<>();
    private Map<String, N[]> data = new LinkedHashMap<>();

    @Override
    public Map<String, N[]> getData()
    {
        return data;
    }

    public void setData(Map<String, N[]> data)
    {
        this.data = data;
    }

    /**
     * @param barLabels
     */
    public void setBarLabels(String[] barLabels)
    {
        this.barLabels = Arrays.asList(barLabels);
    }

    /**
     * @return
     */
    public List<String> getBarLabels()
    {
        return barLabels;
    }

    /**
     * @param barLabels
     */
    public void setBarLabels(List<String> barLabels)
    {
        this.barLabels = barLabels;
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
     * @param label
     *
     * @return
     */
    @Override
    public N[] getData(String label)
    {
        return data.get(label);
    }

    /**
     * @return
     */
    @Override
    public Integer getNumValues()
    {
        int i = 0;

        for ( N[] n : data.values() )
        {
            i += n.length;
        }

        return i;
    }

    /**
     * @return
     */
    @Override
    public N getMax()
    {
        N max = null;

        for ( N[] numbers : data.values() )
        {
            for ( N number : numbers )
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
        }

        return max;
    }
}
