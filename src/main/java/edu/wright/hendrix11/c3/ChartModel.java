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

import edu.wright.hendrix11.c3.Axis.Axis;

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
    private Map<?, ? extends Number[]> arrayData = new LinkedHashMap<>();
    private List<String> barLabels = new ArrayList<>();
    private Map<?, ? extends Number> data = new LinkedHashMap<>();
    private String title;
    private XAxis xAxis;
    private YAxis yAxis;
    private boolean isCategory = false;

    public boolean isCategory()
    {
        return isCategory;
    }

    public void setIsCategory(boolean isCategory)
    {
        this.isCategory = isCategory;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public XAxis getxAxis()
    {

        return xAxis;
    }

    public void setxAxis(XAxis xAxis)
    {
        this.xAxis = xAxis;
    }

    public YAxis getyAxis()
    {
        return yAxis;
    }

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

    public Map<?, ? extends Number[]> getArrayData()
    {
        return arrayData;
    }

    public void setArrayData(Map<?, ? extends Number[]> arrayData)
    {
        data = new LinkedHashMap<>();
        this.arrayData = arrayData;
    }

    public Map<?, ? extends Number> getData()
    {
        return data;
    }

    public void setData(Map<?, ? extends Number> data)
    {
        arrayData = new LinkedHashMap<>();
        this.data = data;
    }

    /**
     * @return
     */
    public Set<?> getAxisLabels()
    {
        if ( hasArrayData() )
            return arrayData.keySet();
        else
            return data.keySet();
    }

    public Object[] getArrayData(Object label)
    {
        return arrayData.get(label);
    }

    public Object getData(String label)
    {
        return data.get(label);
    }

    public boolean hasArrayData()
    {
        return arrayData != null && !arrayData.isEmpty();
    }
}
