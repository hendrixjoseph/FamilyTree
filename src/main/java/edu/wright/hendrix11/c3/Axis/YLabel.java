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
public class YLabel extends Label
{
    private YLabelPosition position;

    public YLabel(String text)
    {
        super(text);
    }

    public YLabel(String text, YLabelPosition position)
    {
        this(text);
        setPosition(position);
    }

    public YLabelPosition getPosition()
    {
        return position;
    }

    public void setPosition(YLabelPosition position)
    {
        this.position = position;
        setPosition(position.toString());
    }

    public enum YLabelPosition
    {
        inner_top,
        inner_middle,
        inner_bottom,
        outer_top,
        outer_middle,
        outer_bottom;

        @Override
        public String toString()
        {
            return super.toString().replace('_', '-');
        }
    }
}
