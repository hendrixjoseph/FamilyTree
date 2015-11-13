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
public abstract class Skew extends Transform
{
    private Number a;
    private String xy;

    /**
     * @param a
     * @param xy
     */
    protected Skew(Number a, String xy)
    {
        this.a = a;
        this.xy = xy;
    }

    @Override
    public String toString()
    {
        Number[] args = {a};

        return toString("skew" + xy.toUpperCase(), args);
    }
}
