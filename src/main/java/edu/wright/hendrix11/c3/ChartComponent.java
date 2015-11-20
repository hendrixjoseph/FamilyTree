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

package edu.wright.hendrix11.c3;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

/**
 * @author Joe Hendrix
 */
@FacesComponent(value = ChartComponent.COMPONENT_TYPE)
@ResourceDependencies({
                              @ResourceDependency(library = "css", name = "c3.css"),
                              @ResourceDependency(library = "js", name = "c3.min.js"),
                              @ResourceDependency(library = "js", name = "d3.min.js")
                      })
public class ChartComponent extends UIComponentBase
{
    /**
     *
     */
    public static final String COMPONENT_FAMILY = "edu.wright.hendrix11.c3";
    /**
     *
     */
    public static final String COMPONENT_TYPE = "edu.wright.hendrix11.c3.ChartComponent";
    /**
     *
     */
    public static final String DEFAULT_RENDERER = "edu.wright.hendrix11.c3.ChartRenderer";

    public ChartComponent()
    {
        setRendererType(DEFAULT_RENDERER);
    }

    @Override
    public String getFamily()
    {
        return COMPONENT_FAMILY;
    }

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
     * @param gridX
     */
    public void setGridX(Boolean gridX)
    {
        getStateHelper().put(PropertyKeys.gridX, string);
    }
    
    /**
     * @return
     */
    public Boolean getGridX()
    {
        return (Boolean) getStateHelper().eval(PropertyKeys.gridX, null);
    }
    
        /**
     * @param gridY
     */
    public void setGridY(Boolean gridY)
    {
        getStateHelper().put(PropertyKeys.gridY, string);
    }
    
    /**
     * @return
     */
    public Boolean getGridY()
    {
        return (Boolean) getStateHelper().eval(PropertyKeys.gridY, null);
    }

    /**
     * @param style
     */
    public void setStyle(String style)
    {
        getStateHelper().put(PropertyKeys.style, style);
    }

    /**
     * @return
     */
    public String getType()
    {
        return (String) getStateHelper().eval(PropertyKeys.type, null);
    }

    /**
     * @param string
     */
    public void setType(String string)
    {
        getStateHelper().put(PropertyKeys.type, string);
    }

    /**
     * @return
     */
    public ChartModel getChartModel()
    {
        return (ChartModel) getStateHelper().eval(PropertyKeys.chartModel, null);
    }

    /**
     * @param model
     */
    public void setChartModel(ChartModel model)
    {
        getStateHelper().put(PropertyKeys.chartModel, model);
    }

    /**
     *
     */
    protected enum PropertyKeys
    {

        /**
         *
         */
        styleClass,

        /**
         *
         */
        chartModel,

        /**
         *
         */
        style,
        type,
        gridX,
        gridY
    }
}
