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
 *
 */
public class XPadding
{
    int left;
    int right;

    /**
     * @param left
     * @param right
     */
    public XPadding(int left, int right)
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
