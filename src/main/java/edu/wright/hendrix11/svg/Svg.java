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

import javax.faces.context.ResponseWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joe Hendrix
 */
public class Svg extends SuperSvgClass
{
    private List<SuperSvgClass> components = new ArrayList<>();

    public Svg()
    {
        super("svg");
    }

    @Override
    public void encodeMiddle(ResponseWriter writer) throws IOException
    {
        for ( SuperSvgClass component : components )
        {
            component.encodeAll(writer);
        }
    }

    public Number getWidth()
    {
        return (Number) getAttribute("width");
    }

    public void setWidth(Number width)
    {
        putAttribute("width", width);
    }

    public Number getHeight()
    {
        return (Number) getAttribute("height");
    }

    public void setHeight(Number height)
    {
        putAttribute("height", height);
    }

    public void setX(Number x)
    {
        putAttribute("x", x);
    }

    public void setY(Number y)
    {
        putAttribute("y", y);
    }

    public void addComponent(SuperSvgClass component)
    {
        components.add(component);
    }
}
