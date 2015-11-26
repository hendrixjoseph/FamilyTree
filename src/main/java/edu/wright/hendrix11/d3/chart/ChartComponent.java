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

package edu.wright.hendrix11.d3.chart;

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
    public static final String COMPONENT_FAMILY = "edu.wright.hendrix11.d3";
    /**
     *
     */
    public static final String COMPONENT_TYPE = "edu.wright.hendrix11.d3.chart.ChartComponent";
    /**
     *
     */
    public static final String DEFAULT_RENDERER = "edu.wright.hendrix11.d3.chart.ChartRenderer";

    /**
     *
     */
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
     * @param style
     */
    public void setStyle(String style)
    {
        getStateHelper().put(PropertyKeys.style, style);
    }

    /**
     * @return
     */
    public Boolean getGridX()
    {
        return (Boolean) getStateHelper().eval(PropertyKeys.gridX, null);
    }

    /**
     * @param gridX
     */
    public void setGridX(Boolean gridX)
    {
        getStateHelper().put(PropertyKeys.gridX, gridX);
    }

    /**
     * @return
     */
    public Boolean getShowLegend()
    {
        return (Boolean) getStateHelper().eval(PropertyKeys.showLegend, null);
    }

    /**
     * @param showLegend
     */
    public void setShowLegend(Boolean showLegend)
    {
        getStateHelper().put(PropertyKeys.showLegend, showLegend);
    }

    /**
     * @return
     */
    public Boolean getGridY()
    {
        return (Boolean) getStateHelper().eval(PropertyKeys.gridY, null);
    }

    /**
     * @param gridY
     */
    public void setGridY(Boolean gridY)
    {
        getStateHelper().put(PropertyKeys.gridY, gridY);
    }

    /**
     * @return
     */
    public String getType()
    {
        return (String) getStateHelper().eval(PropertyKeys.type, null);
    }

    /**
     * @param type
     */
    public void setType(String type)
    {
        getStateHelper().put(PropertyKeys.type, type);
    }

    /**
     * @return
     */
    public String getTitle()
    {
        return (String) getStateHelper().eval(PropertyKeys.title, null);
    }

    /**
     * @param title
     */
    public void setTitle(String title)
    {
        getStateHelper().put(PropertyKeys.title, title);
    }

    /**
     * @return
     */
    public String getLegendPosition()
    {
        return (String) getStateHelper().eval(PropertyKeys.legendPosition, null);
    }

    /**
     * @param legendPosition
     */
    public void setLegendPosition(String legendPosition)
    {
        getStateHelper().put(PropertyKeys.legendPosition, legendPosition);
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

        /**
         *
         */
        type,

        /**
         *
         */
        gridX,

        /**
         *
         */
        gridY,

        /**
         *
         */
        showLegend,

        /**
         *
         */
        legendPosition,

        title
    }
}
