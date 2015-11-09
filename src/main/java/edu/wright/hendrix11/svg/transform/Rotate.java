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
        super(x,y);
        this.degrees = degrees;
    }
    
    public Rotate(Number degrees)
    {
        this.degrees = degrees;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("rotate(").append(degrees);
        
        if(x != null && y != null)
            sb.append(",").append(x).append(",").append(y);
        
        return .append(")").toString();
    }
}
