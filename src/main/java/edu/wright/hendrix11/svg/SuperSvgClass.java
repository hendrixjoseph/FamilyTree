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
import java.util.HashMap;
import java.util.Map;

/**
 * @author Joe Hendrix
 */
public abstract class SuperSvgClass
{
    private String name;

    private Map<String,Object> attributeMap = new HashMap<>();

    protected SuperSvgClass(String name)
    {
        this.name = name;
    }

    public Object put(String key, Object value)
    {
        if(value == null)
        {
            return attributeMap.remove(key);
        }
        else
        {
            return attributeMap.put(key, value);
        }
    }

    public Object get(Object key)
    {
        return attributeMap.get(key);
    }

    public String getStyleClass()
    {
        return attributeMap.get("class").toString();
    }

    public void setStyleClass(String styleClass)
    {
        put("class",styleClass);
    }

    public String getStyle()
    {
        return attributeMap.get("style").toString();
    }

    public void setStyle(String style)
    {
        put("style",style);
    }

    public void encodeBegin(ResponseWriter writer) throws IOException
    {
        writer.startElement(name,null);

        for(String attribute : attributeMap.keySet())
        {
            Object value = attributeMap.get(attribute);

            writer.writeAttribute(attribute,value,null);
        }
    }

    public abstract void encodeMiddle(ResponseWriter writer) throws IOException;

    public void encodeEnd(ResponseWriter writer) throws IOException
    {
        writer.endElement(name);
    }

    public void encodeAll(ResponseWriter writer) throws IOException
    {
        encodeBegin(writer);
        encodeMiddle(writer);
        encodeEnd(writer);
    }
}
