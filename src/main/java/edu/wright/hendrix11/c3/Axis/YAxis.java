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

/**
 * @author Joe Hendrix
 */
public class YAxis extends Axis
{
    private boolean localTime;

    public YAxis()
    {
        super("y");
    }

    @Override
    public YLabel getLabel()
    {
        return (YLabel) super.getLabel();
    }

    public void setLabel(YLabel label)
    {
        super.setLabel(label);
    }
}
