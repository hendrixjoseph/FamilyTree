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

/**
 * The padding for the y-axis will create more space on the edge of the axis. Top and bottom will be treated as pixels.
 */
public class YPadding implements Padding
{
    int top;
    int bottom;

    /**
     * @param top    the padding on the top, in pixels
     * @param bottom
     */
    public YPadding(int top, int bottom)
    {
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    public String toString()
    {
        return "{top:" + top + ",bottom:" + bottom + "}";
    }
}
