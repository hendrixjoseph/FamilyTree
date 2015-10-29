/*
* The MIT License (MIT)
*
* View the full license at:
* https://github.com/hendrixjoseph/FamilyTree/blob/master/LICENSE.md
*
* Copyright (c) 2015 Joseph Hendrix
*
* Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
*
*/

package edu.wright.hendrix11.familyTree.converter;

import edu.wright.hendrix11.familyTree.entity.place.Place;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@FacesConverter(PlaceConverter.NAME)
public class PlaceConverter implements Converter
{

    /**
     *
     */
    public static final String NAME = "hendrix11.PlaceConverter";

    /**
     * @param value
     *
     * @return
     */
    public Place getAsPlace(String value)
    {
        return (Place) getAsObject(null, null, value);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        return value.toString();
    }
}
