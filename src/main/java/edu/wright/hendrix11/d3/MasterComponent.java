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

package edu.wright.hendrix11.d3;

import javax.faces.component.UIComponentBase;

/**
 * @author Joe Hendrix
 */
public abstract class MasterComponent extends UIComponentBase
{
    /**
     * Returns the value of the class attribute of the div element that contains the component.
     *
     * @return String the value of the class attribute of the div element that contains the component
     */
    public String getStyleClass()
    {
        return (String) getStateHelper().eval(PropertyKeys.styleClass, null);
    }

    /**
     * Sets the value of the class attribute of the div element that contains the component.
     *
     * @param styleClass the value of the class attribute of the div element that contains the component
     */
    public void setStyleClass(String styleClass)
    {
        getStateHelper().put(PropertyKeys.styleClass, styleClass);
    }

    /**
     * Returns the value of the style attribute of the div element that contains the component.
     *
     * @return String the value of the style attribute of the div element that contains the component
     */
    public String getStyle()
    {
        return (String) getStateHelper().eval(PropertyKeys.style, null);
    }

    /**
     * Sets the value of the style attribute of the div element that contains the component.
     *
     * @param style the value of the style attribute of the div element that contains the component
     */
    public void setStyle(String style)
    {
        getStateHelper().put(PropertyKeys.style, style);
    }

    protected enum PropertyKeys
    {

        /**
         *
         */
        styleClass,
        style
    }
}
