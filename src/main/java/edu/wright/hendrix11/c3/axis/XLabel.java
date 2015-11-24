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

package edu.wright.hendrix11.c3.axis;

/**
 * @author Joe Hendrix
 */
public class XLabel extends Label
{
    private Position position;

    /**
     *
     * @param text
     */
    public XLabel(String text)
    {
        super(text);
    }

    /**
     *
     * @param text
     * @param position
     */
    public XLabel(String text, Position position)
    {
        this(text);
        setPosition(position);
    }

    /**
     *
     * @return
     */
    public Position getPosition()
    {
        return position;
    }

    /**
     *
     * @param position
     */
    public void setPosition(Position position)
    {
        this.position = position;
        setPosition(position.toString());
    }

    /**
     *
     */
    public enum Position
    {

        /**
         *
         */
        inner_right,

        /**
         *
         */
        inner_center,

        /**
         *
         */
        inner_left,

        /**
         *
         */
        outer_right,

        /**
         *
         */
        outer_center,

        /**
         *
         */
        outer_left;

        @Override
        public String toString()
        {
            return super.toString().replace('_', '-');
        }
    }
}
