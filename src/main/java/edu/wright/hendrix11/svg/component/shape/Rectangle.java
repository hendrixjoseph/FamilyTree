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

    /**
     *
     */
    public Rectangle()
    {
        super("rect");
    }

    /**
     * @param writer
     *
     * @throws IOException
     */
    @Override
    public void encodeMiddle(ResponseWriter writer) throws IOException
    {

    }

    /**
     * @param height
     */
    public void setHeight(Number height)
    {
        putAttribute("height", height);
    }

    /**
     * @param width
     */
    public void setWidth(Number width)
    {
        putAttribute("width", width);
    }

    /**
     * @param x
     */
    public void setX(Number x)
    {
        putAttribute("x", x);
    }

    /**
     * @param y
     */
    public void setY(Number y)
    {
        putAttribute("y", y);
    }

    /**
     * @param rx
     */
    public void setRx(Number rx)
    {
        putAttribute("rx", rx);
    }

    /**
     * @param ry
     */
    public void setRy(Number ry)
    {
        putAttribute("ry", ry);
    }
}
