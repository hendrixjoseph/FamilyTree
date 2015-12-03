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

package edu.wright.hendrix11.d3.chart;

import edu.wright.hendrix11.d3.Color;
import edu.wright.hendrix11.d3.chart.axis.XAxis;
import edu.wright.hendrix11.d3.chart.axis.YAxis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
public class ChartModel
{
    private static final Logger LOG = Logger.getLogger(ChartModel.class.getName());
    private Map<String, ? extends Number[]> arrayData = new LinkedHashMap<>();
    private List<String> barLabels = new ArrayList<>();
    private List<String> colors = new ArrayList<>();
    private Map<String, ? extends Number> data = new LinkedHashMap<>();
    private XAxis xAxis;
    private YAxis yAxis;

    /**
     * Returns the custom color pattern for the data. The first data has the first color, the second data has the second
     * color, and so on.
     *
     * @return the custom color pattern for the data
     */
    public List<String> getColors()
    {
        return colors;
    }

    /**
     * Sets the custom color pattern for the data. The first data has the first color, the second data has the second
     * color, and so on.
     *
     * @param colors the custom color pattern for the data
     */
    public void setColors(List<String> colors)
    {
        this.colors = colors;
    }

    /**
     * Adds a color to the list of custom color patterns for the data. The first data has the first color, the second
     * data has the second color, and so on.
     *
     * @param color a String representation of a color
     */
    public void addColor(String color)
    {
        colors.add(color);
    }

    /**
     * Adds a color to the list of custom color patterns for the data. The first data has the first color, the second
     * data has the second color, and so on.
     *
     * @param color a color from the color enum
     */
    public void addColor(Color color)
    {
        colors.add(color.toString());
    }

    /**
     * Returns the x-axis.
     * 
     * @return the x-axis
     */
    public XAxis getxAxis()
    {

        return xAxis;
    }

    /**
     * Sets the x-axis.
     * 
     * @param xAxis the x-axis
     */
    public void setxAxis(XAxis xAxis)
    {
        this.xAxis = xAxis;
    }

    /**
     * Sets the text that will be displayed along the x axis.
     *
     * @param label the text that will be displayed along the x axis
     */
    public void setxAxis(String label)
    {
        if(xAxis != null)
        {
            xAxis.setLabel(label);
        }
        else
        {
            this.xAxis = new XAxis(label);
        }
    }

    /**
     * Returns the y-axis.
     * 
     * @return the y-axis
     */
    public YAxis getyAxis()
    {
        return yAxis;
    }

    /**
     * Sets the y-axis.
     * 
     * @param yAxis the y-axis
     */
    public void setyAxis(YAxis yAxis)
    {
        this.yAxis = yAxis;
    }

    /**
     * Sets the text that will be displayed along the y axis.
     *
     * @param label the text that will be displayed along the y axis
     */
    public void setyAxis(String label)
    {
        if(yAxis != null)
        {
            yAxis.setLabel(label);
        }
        else
        {
            this.yAxis = new YAxis(label);
        }
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
    public Map<String, ? extends Number[]> getArrayData()
    {
        return arrayData;
    }

    /**
     * @param arrayData
     */
    public void setArrayData(Map<String, ? extends Number[]> arrayData)
    {
        data = new LinkedHashMap<>();
        this.arrayData = arrayData;
    }

    /**
     * @return
     */
    public Map<String, ? extends Number> getData()
    {
        return data;
    }

    /**
     * @param data
     */
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

    /**
     * @param label
     *
     * @return
     */
    public Object[] getArrayData(Object label)
    {
        return arrayData.get(label);
    }

    /**
     * @param label
     *
     * @return
     */
    public Object getData(String label)
    {
        return data.get(label);
    }

    /**
     * @return
     */
    public boolean hasArrayData()
    {
        return arrayData != null && !arrayData.isEmpty();
    }
}
