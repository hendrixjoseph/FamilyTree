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
public class XLabel extends Label
{
    private XLabelPosition position;

    public XLabel(String text)
    {
        super(text);
    }

    public XLabel(String text, XLabelPosition position)
    {
        this(text);
        setPosition(position);
    }

    public XLabelPosition getPosition()
    {
        return position;
    }

    public void setPosition(XLabelPosition position)
    {
        this.position = position;
        setPosition(position.toString());
    }

    public enum XLabelPosition
    {
        inner_right,
        inner_center,
        inner_left,
        outer_right,
        outer_center,
        outer_left;

        @Override
        public String toString()
        {
            return super.toString().replace('_', '-');
        }
    }
}
