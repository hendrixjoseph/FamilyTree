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
public class Group extends SvgComponent
{
    private List<SvgComponent> components = new ArrayList<>();

    public Group()
    {
        super("g");
    }

    @Override
    public void encodeMiddle(ResponseWriter writer) throws IOException
    {
        for(SvgComponent component : components)
        {
            component.encodeAll(writer);
        }
    }

    public void addComponent(SvgComponent component)
    {
        components.add(component);
    }
}
