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

package edu.wright.hendrix11.svg.component;

import javax.faces.context.ResponseWriter;

import java.io.IOException;

/**
 * @author Joe Hendrix
 */
public class Rectangle extends SvgComponent
{
    public Rectangle()
    {
        super("rect");
    }

    @Override
    public void encodeMiddle(ResponseWriter writer) throws IOException
    {

    }

    public void setHeight(Number height)
    {
        put("height",height);
    }

    public void setWidth(Number width)
    {
        put("width",width);
    }

    public void setX(Number x)
    {
        put("x",x);
    }

    public void setY(Number y)
    {
        put("y",y);
    }
}
