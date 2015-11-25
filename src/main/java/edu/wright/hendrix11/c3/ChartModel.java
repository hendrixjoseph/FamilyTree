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

import edu.wright.hendrix11.c3.axis.XAxis;
import edu.wright.hendrix11.c3.axis.YAxis;

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
    private boolean isCategory = false;
    private XAxis xAxis;
    private YAxis yAxis;

    /**
     * @return
     */
    public List<String> getColors()
    {
        return colors;
    }

    /**
     * @param colors
     */
    public void setColors(List<String> colors)
    {
        this.colors = colors;
    }

    /**
     * @param color
     */
    public void addColor(String color)
    {
        colors.add(color);
    }

    /**
     * @param color
     */
    public void addColor(Color color)
    {
        colors.add(color.toString());
    }

    /**
     * @return
     */
    public boolean isCategory()
    {
        return isCategory;
    }

    /**
     * @param isCategory
     */
    public void setIsCategory(boolean isCategory)
    {
        this.isCategory = isCategory;
    }

    /**
     * @return
     */
    public XAxis getxAxis()
    {

        return xAxis;
    }

    /**
     * @param label
     */
    public void setxAxis(String label)
    {
        this.xAxis = new XAxis(label);
    }

    /**
     * @param xAxis
     */
    public void setxAxis(XAxis xAxis)
    {
        this.xAxis = xAxis;
    }

    /**
     * @return
     */
    public YAxis getyAxis()
    {
        return yAxis;
    }

    /**
     * @param label
     */
    public void setyAxis(String label)
    {
        this.yAxis = new YAxis(label);
    }

    /**
     * @param yAxis
     */
    public void setyAxis(YAxis yAxis)
    {
        this.yAxis = yAxis;
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
