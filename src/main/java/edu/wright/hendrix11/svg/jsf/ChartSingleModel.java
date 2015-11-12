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

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Joe Hendrix
 */
public class ChartSingleModel<N extends Number & Comparable> extends ChartModel<N>
{
    private Map<String, N> data = new LinkedHashMap<>();

    @Override
    public Map<String, N> getData()
    {
        return data;
    }

    @Override
    public N getData(String label)
    {
        return data.get(label);
    }

    @Override
    public Set<String> getAxisLabels()
    {
        return data.keySet();
    }

    @Override
    public Integer getNumValues()
    {
        return data.size();
    }

    public void setData(Map<String, N> data)
    {
        this.data = data;
    }

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
