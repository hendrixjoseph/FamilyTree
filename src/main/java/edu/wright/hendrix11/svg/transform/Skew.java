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
public abstract class Skew implements Transform
{
    private Number a;
    private String xy;

    protected Skew(Number a, String xy)
    {
        this.a = a;
        this.xy = xy;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("skew").append(xy.toUpper()).apppend("(").append(a).append(")");
        
        return sb.toString();
    }
}
