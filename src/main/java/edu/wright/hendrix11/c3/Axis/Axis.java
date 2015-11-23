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

package edu.wright.hendrix11.c3.Axis;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author Joe Hendrix
 */
public abstract class Axis
{
    Map<String, Object> map = new HashMap<>();
    private String axis;
    private boolean localTime;

    public Axis(String axis)
    {
        this.axis = axis;
    }

    public int getHeight()
    {
        return (int) map.get("height:");
    }

    public void setHeight(int height)
    {
        map.put("height:", height);
    }

    public boolean isLocalTime()
    {
        return localTime;
    }

    public void setLocalTime(boolean localTime)
    {
        this.localTime = localTime;
    }

    public int getMax()
    {
        return (int) map.get("max:");
    }

    public void setMax(int max)
    {
        map.put("max:", max);
    }

    public int getMin()
    {
        return (int) map.get("min:");
    }

    public void setMin(int min)
    {
        map.put("min:", min);
    }

    public boolean isShow()
    {
        return (boolean) map.get("show:");
    }

    public void setShow(boolean show)
    {
        map.put("show:", show);
    }

    public Type getType()
    {
        return (Type) map.get("type:");
    }

    public void setType(Type type)
    {
        map.put("type:", type);
    }

    public Padding getPadding()
    {
        return (Padding) map.get("padding:");
    }

    public void setPadding(Padding padding)
    {
        map.put("padding:", padding);
    }

    public String getAxis()
    {
        return axis;
    }

    protected Label getLabel()
    {
        return (Label) map.get("label:");
    }

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

        StringBuilder returnValue = new StringBuilder("axis:{");
        returnValue.append(which);
        returnValue.append("{");
        returnValue.append(sj);
        returnValue.append("}}");

        return returnValue.toString();
    }

    public enum Type
    {
        timeseries,
        category,
        indexed
    }

    public enum Side
    {
        left,
        right
    }

    public class Padding
    {
        int left;
        int right;

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
