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

package edu.wright.hendrix11.svg.transform;

/**
 * @author Joe Hendrix
 */
public class Rotate extends XyTransform
{
    private Number degrees;

    public Rotate(Number degrees, Number x, Number y)
    {
        super(x, y);
        this.degrees = degrees;
    }

    public Rotate(Number degrees)
    {
        this.degrees = degrees;
    }

    @Override
    public String toString()
    {
        Number[] args = {degrees, x, y};

        return toString("rotate", args);
    }
}
