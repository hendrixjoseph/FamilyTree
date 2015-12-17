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

package edu.wright.hendrix11.d3.chart.axis.tick;

import java.util.StringJoiner;

/**
 * @author Joe Hendrix
 */
public class XTick extends Tick
{
    private Boolean multiline;
    private Integer rotate;

    public Integer getRotate()
    {
        return rotate;
    }

    public void setRotate(Integer rotate)
    {
        this.rotate = rotate;
    }

    public Boolean isMultiline()
    {
        return multiline;
    }

    public void setMultiline(Boolean multiline)
    {
        this.multiline = multiline;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("{");

        StringJoiner sj = new StringJoiner(",");

        if ( super.toString() != null )
            sj.add(super.toString());

        if ( rotate != null )
            sj.add("rotate:" + rotate);

        if ( multiline != null )
            sj.add("multiline:" + multiline);

        return sb.append(sj).append("}").toString();
    }
}
