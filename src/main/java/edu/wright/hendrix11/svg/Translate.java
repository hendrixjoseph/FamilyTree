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

package edu.wright.hendrix11.svg;

/**
 * @author Joe Hendrix
 */
public class Translate implements Transform
{
    Number x;
    Number y;

    public Translate(Number x, Number y)
    {
        this.x = x;
        this.y = y;
    }

    public String toString()
    {
        return new StringBuilder("translate(").append(x).append(",").append(y).append(")").toString();
    }
}
