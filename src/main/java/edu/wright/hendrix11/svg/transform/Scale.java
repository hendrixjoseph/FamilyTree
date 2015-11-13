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
public class Scale extends XyTransform
{

    /**
     * @param x
     * @param y
     */
    public Scale(Number x, Number y)
    {
        super(x, y);
    }

    /**
     * @param x
     */
    public Scale(Number x)
    {
        super(x);
    }

    public String toString()
    {
        Number[] args = {x, y};

        return toString("scale", args);
    }
}
