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
public class XAxis extends Axis
{
    private boolean localTime;

    public XAxis()
    {
        super("x");
    }

    @Override
    public XLabel getLabel()
    {
        return (XLabel) super.getLabel();
    }

    public void setLabel(XLabel label)
    {
        super.setLabel(label);
    }
}
