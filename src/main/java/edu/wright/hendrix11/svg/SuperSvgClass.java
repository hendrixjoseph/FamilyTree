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
    private Map<String, Object> attributeMap = new HashMap<>();
    private String name;
    private Map<String, String> propertyMap = new HashMap<>();

    /**
     * @param name
     */
    protected SuperSvgClass(String name)
    {
        this.name = name;
    }

    /**
     * @param key
     *
     * @return
     */
    public String getProperty(Object key)
    {
        return propertyMap.get(key);
    }

    /**
     * @param key
     * @param value
     *
     * @return
     */
    public String putProperty(String key, String value)
    {
        if ( value == null )
        {
            return propertyMap.remove(key);
        }
        else
        {
            return propertyMap.put(key, value);
        }
    }

    /**
     * @param key
     * @param value
     *
     * @return
     */
    public Object putAttribute(String key, Object value)
    {
        if ( value == null )
        {
            propertyMap.remove(key);
            return attributeMap.remove(key);
        }
        else
        {
            return attributeMap.put(key, value);
        }
    }

    /**
     * @param key
     *
     * @return
     */
    public Object getAttribute(Object key)
    {
        return attributeMap.get(key);
    }

    /**
     * @return
     */
    public String getStyleClass()
    {
        return attributeMap.get("class").toString();
    }

    /**
     * @param styleClass
     */
    public void setStyleClass(String styleClass)
    {
        putAttribute("class", styleClass);
    }

    /**
     * @param styleClass
     * @param property
     */
    public void setStyleClass(String styleClass, String property)
    {
        setStyleClass(styleClass);
        putProperty("class", property);
    }

    /**
     * @return
     */
    public String getStyle()
    {
        return attributeMap.get("style").toString();
    }

    /**
     * @param style
     */
    public void setStyle(String style)
    {
        putAttribute("style", style);
    }

    /**
     * @param writer
     *
     * @throws IOException
     */
    public void encodeBegin(ResponseWriter writer) throws IOException
    {
        writer.startElement(name, null);

        for ( String attribute : attributeMap.keySet() )
        {
            Object value = attributeMap.get(attribute);
            String property = propertyMap.get(attribute);

            writer.writeAttribute(attribute, value, property);
        }
    }

    /**
     * @param writer
     *
     * @throws IOException
     */
    public abstract void encodeMiddle(ResponseWriter writer) throws IOException;

    /**
     * @param writer
     *
     * @throws IOException
     */
    public void encodeEnd(ResponseWriter writer) throws IOException
    {
        writer.endElement(name);
    }

    /**
     * @param writer
     *
     * @throws IOException
     */
    public void encodeAll(ResponseWriter writer) throws IOException
    {
        encodeBegin(writer);
        encodeMiddle(writer);
        encodeEnd(writer);
    }
}
