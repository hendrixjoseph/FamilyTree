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
     * @return
     */
    public int getMax()
    {
        return (int) map.get("max:");
    }

    /**
     * @param max
     */
    public void setMax(int max)
    {
        map.put("max:", max);
    }

    /**
     * @return
     */
    public int getMin()
    {
        return (int) map.get("min:");
    }

    /**
     * @param min
     */
    public void setMin(int min)
    {
        map.put("min:", min);
    }

    /**
     * @return
     */
    public boolean isShow()
    {
        return (boolean) map.get("show:");
    }

    /**
     * @param show
     */
    public void setShow(boolean show)
    {
        map.put("show:", show);
    }

    /**
     * @return
     */
    public Type getType()
    {
        return type;
    }

    /**
     * @param type
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
     * @return
     */
    protected Label getLabel()
    {
        return (Label) map.get("label:");
    }
    
    /**
     * @param label
     */
    public abstract void setLabel(String label);

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

        for ( String field : map.keySet() )
        {
            StringBuilder sb = new StringBuilder(field);
            sb.append(map.get(field));

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

    /**
     *
     */
    public class Padding
    {
        int left;
        int right;

        /**
         * @param left
         * @param right
         */
        public Padding(int left, int right)
        {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString()
        {
            return "{left:" + left + ",right:" + right + "}";
        }
    }
}
