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

import edu.wright.hendrix11.svg.component.SvgComponent;

import javax.faces.context.ResponseWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joe Hendrix
 */
public class Svg extends SuperSvgClass
{
    private List<SvgComponent> components = new ArrayList<>();

    public Svg()
    {
        super("svg");
    }

    @Override
    public void encodeMiddle(ResponseWriter writer) throws IOException
    {
        for ( SvgComponent component : components )
        {
            component.encodeAll(writer);
        }
    }

    public Number getWidth()
    {
        return (Number) get("width");
    }

    public void setWidth(Number width)
    {
        put("width", width);
    }

    public Number getHeight()
    {
        return (Number) get("height");
    }

    public void setHeight(Number height)
    {
        put("height", height);
    }

    public void addComponent(SvgComponent component)
    {
        components.add(component);
    }
}
