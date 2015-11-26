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
     * @return
     */
    public String getStyleClass()
    {
        return (String) getStateHelper().eval(PropertyKeys.styleClass, null);
    }

    /**
     * @param styleClass
     */
    public void setStyleClass(String styleClass)
    {
        getStateHelper().put(PropertyKeys.styleClass, styleClass);
    }

    /**
     * @return
     */
    public String getStyle()
    {
        return (String) getStateHelper().eval(PropertyKeys.style, null);
    }

    /**
     * @param style
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
