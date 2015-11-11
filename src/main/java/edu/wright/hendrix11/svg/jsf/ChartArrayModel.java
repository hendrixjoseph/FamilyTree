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
public class ChartArrayModel<N extends Number> extends ChartModel<N>
{
    private List<String> barLabels;
    private Map<String, N[]> data = new LinkedHashMap<>();

    public void setBarLabels(String[] barLabels)
    {
        setBarLabels(Arrays.asList(barLabels));
    }

    public List<String> getBarLabels()
    {
        return new ArrayList<String>();
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

    public N[] getData(String label)
    {
        return data.get(label);
    }

    @Override
    public Integer getNumLabels()
    {
        return data.keySet().size();
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
                    if ( number instanceof Double )
                    {
                        max = max.doubleValue() < number.doubleValue() ? number : max;
                    }
                    else
                    {
                        max = max.intValue() < number.intValue() ? number : max;
                    }
                }
            }
        }

        return max;
    }
}
