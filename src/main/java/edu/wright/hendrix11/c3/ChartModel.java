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

package edu.wright.hendrix11.c3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
public class ChartModel
{
    private static final Logger LOG = Logger.getLogger(ChartModel.class.getName());
    private Map<String, ? extends Number[]> arrayData = new LinkedHashMap<>();
    private List<String> barLabels = new ArrayList<>();
    private Map<String, ? extends Number> data = new LinkedHashMap<>();
    private String title;
    private String xAxisLabel;
    private String yAxisLabel;

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

    public Map<String, ? extends Number[]> getArrayData()
    {
        return arrayData;
    }

    public void setArrayData(Map<String, ? extends Number[]> arrayData)
    {
        data = new LinkedHashMap<>();
        this.arrayData = arrayData;
    }

    public Map<String, ? extends Number> getData()
    {
        return data;
    }

    public void setData(Map<String, ? extends Number> data)
    {
        arrayData = new LinkedHashMap<>();
        this.data = data;
    }

    /**
     * @return
     */
    public Set<String> getAxisLabels()
    {
        if ( hasArrayData() )
            return arrayData.keySet();
        else
            return data.keySet();
    }

    public Number[] getArrayData(String label)
    {
        return arrayData.get(label);
    }

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
    public Integer getNumValues()
    {
        if ( hasArrayData() )
        {
            int i = 0;

            for ( Number[] n : arrayData.values() )
            {
                i += n.length;
            }

            return i;
        }
        else
        {
            return data.size();
        }
    }

    /**
     * @return
     */
    public Number getMax()
    {
        Number max = null;

        if ( hasArrayData() )
        {
            for ( Number[] numbers : arrayData.values() )
            {
                for ( Number number : numbers )
                {
                    max = max(max, number);
                }
            }
        }
        else
        {
            for ( Number number : data.values() )
            {
                LOG.log(Level.SEVERE, "Max before: " + max);
                max = max(max, number);
                LOG.log(Level.SEVERE, "Max after: " + max);
            }
        }

        return max;
    }

    public Number getData(String label)
    {
        return data.get(label);
    }

    public boolean hasArrayData()
    {
        return arrayData != null && !arrayData.isEmpty();
    }

    private Number max(Number n1, Number n2)
    {
        if ( n1 == null && n2 == null )
        {
            return null;
        }
        else if ( n1 == null )
        {
            return n2;
        }
        else if ( n2 == null )
        {
            return n1;
        }
        else
        {
            if ( n1 instanceof Comparable )
            {
                Comparable compare = (Comparable<?>) n1;
                return compare.compareTo(n2) < 0 ? n2 : n1;
            }
            else
            {
                return n1.doubleValue() - n2.doubleValue() < 0 ? n2 : n1;
            }
        }
    }
}
