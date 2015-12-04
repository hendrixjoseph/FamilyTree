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

import java.util.StringJoiner;

/**
 * @author Joe
 */
public class Tick
{
    Integer rotate;
    Boolean multiline;
    Integer count;

    public String toString()
    {
        StringBuilder sb = new StringBuilder("tick:{");

        StringJoiner sj = new StringJoiner(",");

        if ( rotate != null )
            sj.add("rotate:" + rotate);

        if ( multiline != null )
            sj.add("multiline:" + multiline);

        if ( count != null )
            sj.add("count:" + count);

        sb.append(sj);
        sb.append("}");

        return sb.toString();
    }
}
