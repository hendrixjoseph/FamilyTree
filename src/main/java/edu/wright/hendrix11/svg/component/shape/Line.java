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
public class Line extends SvgComponent
{
    public Line()
    {
        super("line");
    }

    public void setX1(Number x1)
    {
        putAttribute("x1", x1);
    }

    public void setY1(Number y1)
    {
        putAttribute("y1", y1);
    }

    public void setX2(Number x2)
    {
        putAttribute("x2", x2);
    }

    public void setY2(Number y2)
    {
        putAttribute("y2", y2);
    }

    @Override
    public void encodeMiddle(ResponseWriter writer) throws IOException
    {

    }
}
