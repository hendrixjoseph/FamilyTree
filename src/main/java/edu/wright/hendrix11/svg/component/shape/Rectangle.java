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

package edu.wright.hendrix11.svg.component.shape;

import edu.wright.hendrix11.svg.component.SvgComponent;

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
        putAttribute("height", height);
    }

    public void setWidth(Number width)
    {
        putAttribute("width", width);
    }

    public void setX(Number x)
    {
        putAttribute("x", x);
    }

    public void setY(Number y)
    {
        putAttribute("y", y);
    }

    public void setRx(Number rx)
    {
        putAttribute("rx", rx);
    }

    public void setRy(Number ry)
    {
        putAttribute("ry", ry);
    }
}
