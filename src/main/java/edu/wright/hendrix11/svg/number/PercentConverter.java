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

package edu.wright.hendrix11.svg.number;

import edu.wright.hendrix11.svg.jsf.ChartComponent;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * @author Joe Hendrix
 */
@FacesConverter("percentConverter")
public class PercentConverter implements Converter
{
    public static final String NAME = "hendrix11.PercentConverter";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value)
    {
        if(value == null)
        {
            return null;
        }

        value = value.trim();

        if (value.isEmpty())
        {
            return null;
        }

        if(!value.endsWith("%"))
        {
            throw new ConverterException("Value \"\" + value + \"\" does not end in percent! ");
        }

        value = value.substring(0,value.length() - 2);

        try
        {
            if ( value.contains(".") )
            {
                double d = Double.parseDouble(value);
                return new Percent<Double>(d);
            }
            else
            {
                int i = Integer.parseInt(value);
                return new Percent<Integer>(i);
            }
        }
        catch(Exception e)
        {
            throw new ConverterException("Value \"" + value + "\" not converable! ", e);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value)
    {
        if(value == null)
        {
            return null;
        }
        else
        {
            return value.toString();
        }
    }
}
