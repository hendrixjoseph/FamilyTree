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

package edu.wright.hendrix11.d3.chart.axis;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author Joe Hendrix
 */
public abstract class Axis
{
    private String axis;
    private boolean localTime;
    private Map<String, Object> map = new HashMap<>();
    private Type type;

    /**
     * @param axis
     */
    protected Axis(String axis)
    {
        this.axis = axis;
    }

    /**
     * @return
     */
    public int getHeight()
    {
        return (int) map.get("height:");
    }

    /**
     * @param height
     */
    public void setHeight(int height)
    {
        map.put("height:", height);
    }

    /**
     * @return
     */
    public boolean isLocalTime()
    {
        return localTime;
    }

    /**
     * @param localTime
     */
    public void setLocalTime(boolean localTime)
    {
        this.localTime = localTime;
    }

    /**
     * Returns the maximum value of the axis range.
     *
     * @return the maximum value of the axis range
     */
    public int getMax()
    {
        return (int) map.get("max:");
    }

    /**
     * Sets the maximum value of the axis range.
     *
     * @param max the maximum value of the axis range
     */
    public void setMax(int max)
    {
        map.put("max:", max);
    }

    /**
     * Returns the minimum value of the axis range.
     *
     * @return the minimum value of the axis range
     */
    public int getMin()
    {
        return (int) map.get("min:");
    }

    /**
     * Sets the minimum value of the axis range.
     *
     * @param min the minimum value of the axis range
     */
    public void setMin(int min)
    {
        map.put("min:", min);
    }

    /**
     * Returns whether to show or hide the axis.
     *
     * @return whether to show or hide the axis
     */
    public boolean isShow()
    {
        return (boolean) map.get("show:");
    }

    /**
     * Sets whether to show or hide the axis.
     *
     * @param show whether to show or hide the axis
     */
    public void setShow(boolean show)
    {
        map.put("show:", show);
    }

    /**
     * Returns the type of the axis. Uses the nested Type enum. Values are: <ul> <li>timeseries</li> <li>category</li>
     * <li>indexed</li> </ul>
     *
     * @return the type of the axis
     */
    public Type getType()
    {
        return type;
    }

    /**
     * Sets the type of the axis. Uses the nested Type enum. Values are: <ul> <li>timeseries</li> <li>category</li>
     * <li>indexed</li> </ul>
     *
     * @param type the type of the axis
     */
    public void setType(Type type)
    {
        this.type = type;
    }

    /**
     * @return
     */
    public Padding getPadding()
    {
        return (Padding) map.get("padding:");
    }

    /**
     * @param padding
     */
    public void setPadding(Padding padding)
    {
        map.put("padding:", padding);
    }

    /**
     * Sets the text for the axis label
     *
     * @param label the text for the axis label
     */
    public void setLabel(String label)
    {
        getLabel().setText(label);
    }

    /**
     * @return
     */
    protected Label getLabel()
    {
        return (Label) map.get("label:");
    }

    /**
     * @param label
     */
    protected void setLabel(Label label)
    {
        map.put("label:", label);
    }

    @Override
    public String toString()
    {
        StringJoiner sj = new StringJoiner(",");

        for (Map.Entry<String, Object> entry : map.entrySet())
        {
            StringBuilder sb = new StringBuilder(entry.getKey());
            sb.append(entry.getValue());

            sj.add(sb);
        }

        if ( type != null )
        {
            sj.add("type:'" + type + "'");
        }

        StringBuilder returnValue = new StringBuilder(axis);
        returnValue.append(":{");
        returnValue.append(sj);
        returnValue.append("}");

        return returnValue.toString();
    }

    /**
     *
     */
    public enum Type
    {

        /**
         *
         */
        timeseries,

        /**
         *
         */
        category,

        /**
         *
         */
        indexed
    }

    /**
     *
     */
    public enum Side
    {

        /**
         *
         */
        left,

        /**
         *
         */
        right
    }
}
