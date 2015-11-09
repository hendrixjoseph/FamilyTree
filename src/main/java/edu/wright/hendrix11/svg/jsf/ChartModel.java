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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
public class ChartModel
{
    private static final Logger LOG = Logger.getLogger(ChartModel.class.getName());

    Map<String, Integer[]> data = new LinkedHashMap<>();

    public Map<String, Integer[]> getData()
    {
        return data;
    }

    public void setData(Map<String, Integer[]> data)
    {
        this.data = data;
    }

    public void put(String key, Integer[] value)
    {
        data.put(key, value);
    }

    public Set<String> getAxisLabels()
    {
        return data.keySet();
    }

    public Integer[] getData(String label)
    {
        return data.get(label);
    }

    public int getNumLabels()
    {
        return data.keySet().size();
    }

    public int getNumValues()
    {
        return data.values().size() * data.values().iterator().next().length;
    }

    public int getMax()
    {
        int max = 0;

        for ( Integer[] integers : data.values() )
        {
            for ( Integer i : integers )
            {
                if ( max < i )
                    max = i;
            }
        }

        return max;
    }
}
