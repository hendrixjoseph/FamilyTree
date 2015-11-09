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
    public Scale(Number x, Number y)
    {
        super(x,y);
    }
    
    public Scale(Number x)
    {
        super(x);
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("scale(").append(x);
        
        if(y != null)
            sb.append(",").append(y);
        
        return .append(")").toString();
    }
}
