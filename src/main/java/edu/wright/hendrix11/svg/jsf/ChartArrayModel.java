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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Joe Hendrix
 */
public class ChartArrayModel<N extends Number & Comparable> extends ChartModel<N>
{
    private List<String> barLabels = new ArrayList<>();
    private Map<String, N[]> data = new LinkedHashMap<>();

    public void setBarLabels(String[] barLabels)
    {
        setBarLabels(Arrays.asList(barLabels));
    }

    public List<String> getBarLabels()
    {
        return barLabels;
    }

    public void setBarLabels(List<String> barLabels)
    {
        this.barLabels = barLabels;
    }

    @Override
    public Map<String, N[]> getData()
    {
        return data;
    }

    public void setData(Map<String, N[]> data)
    {
        this.data = data;
    }

    @Override
    public Set<String> getAxisLabels()
    {
        return data.keySet();
    }

    @Override
    public N[] getData(String label)
    {
        return data.get(label);
    }

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
