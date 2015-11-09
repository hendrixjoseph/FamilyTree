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
public class Translate extends XyTransform
{
    public Translate(Number x, Number y)
    {
        super(x,y);
    }
    
    public Translate(Number x)
    {
        super(x);
    }

    @Override
    public String toString()
    {
        Number[] args = {x, y};
        
        return toString("translate", args);
    }
}
