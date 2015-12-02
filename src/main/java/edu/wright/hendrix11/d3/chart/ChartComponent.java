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

import edu.wright.hendrix11.d3.MasterComponent;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

/**
 * @author Joe Hendrix
 */
@FacesComponent(value = ChartComponent.COMPONENT_TYPE)
@ResourceDependencies({
                              @ResourceDependency(library = "css", name = "c3.css"),
                              @ResourceDependency(library = "js", name = "c3.min.js"),
                              @ResourceDependency(library = "js", name = "d3.min.js")
                      })
public class ChartComponent extends MasterComponent
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
     * Returns whether to show the grid along the x axis.
     * 
     * @return Boolean whether to show the grid along the x axis
     */
    public Boolean getGridX()
    {
        return (Boolean) getStateHelper().eval(PropertyKeys.gridX, null);
    }

    /**
     * Sets whether to show the grid along the x axis.
     * 
     * @param gridX whether to show the grid along the x axis
     */
    public void setGridX(Boolean gridX)
    {
        getStateHelper().put(PropertyKeys.gridX, gridX);
    }

    /**
     * Returns whether to show the legend.
     * 
     * @return Boolean whether to show the legend
     */
    public Boolean getShowLegend()
    {
        return (Boolean) getStateHelper().eval(PropertyKeys.showLegend, null);
    }

    /**
     * Sets whether to show the legend.
     * 
     * @param showLegend whether to show the legend
     */
    public void setShowLegend(Boolean showLegend)
    {
        getStateHelper().put(PropertyKeys.showLegend, showLegend);
    }

    /**
     * Returns whether to show the grid along the y axis.
     * 
     * @return Boolean whether to show the grid along the y axis
     */
    public Boolean getGridY()
    {
        return (Boolean) getStateHelper().eval(PropertyKeys.gridY, null);
    }

    /**
     * Sets whether to show the grid along the y axis.
     * 
     * @param gridY whether to show the grid along the y axis
     */
    public void setGridY(Boolean gridY)
    {
        getStateHelper().put(PropertyKeys.gridY, gridY);
    }

    /**
     * Returns the type of chart to render. Available values are:
     * <ul>
     *  <li>line</li>
     *  <li>spline</li>
     *  <li>step</li>
     *  <li>area</li>
     *  <li>area-spline</li>
     *  <li>area-step</li>
     *  <li>bar</li>
     *  <li>scatter</li>
     *  <li>pie</li>
     *  <li>donut</li>
     *  <li>gauge</li>
     * </ul>
     * 
     * @return String the type of chart to render
     */
    public String getType()
    {
        return (String) getStateHelper().eval(PropertyKeys.type, null);
    }

    /**
     * Sets the type of chart to render. Available values are:
     * <ul>
     *  <li>line</li>
     *  <li>spline</li>
     *  <li>step</li>
     *  <li>area</li>
     *  <li>area-spline</li>
     *  <li>area-step</li>
     *  <li>bar</li>
     *  <li>scatter</li>
     *  <li>pie</li>
     *  <li>donut</li>
     *  <li>gauge</li>
     * </ul>
     * 
     * @param type the type of chart to render
     */
    public void setType(String type)
    {
        getStateHelper().put(PropertyKeys.type, type);
    }

    /**
     * Returns the title of the chart.
     * 
     * @return String the title of the chart
     */
    public String getTitle()
    {
        return (String) getStateHelper().eval(PropertyKeys.title, null);
    }

    /**
     * Sets the title of the chart.
     * 
     * @param title the title of the chart
     */
    public void setTitle(String title)
    {
        getStateHelper().put(PropertyKeys.title, title);
    }

    /**
     * Returns where to show the legend. Possible values include:
     * <ul>
     *  <li>bottom</li>
     *  <li>right</li>
     *  <li>inset</li>
     * </ul>
     * 
     * @return String where to show the legend
     */
    public String getLegendPosition()
    {
        return (String) getStateHelper().eval(PropertyKeys.legendPosition, null);
    }

    /**
     * Sets where to show the legend. Possible values include:
     * <ul>
     *  <li>bottom</li>
     *  <li>right</li>
     *  <li>inset</li>
     * </ul>
     * 
     * @param legendPosition where to show the legend
     */
    public void setLegendPosition(String legendPosition)
    {
        getStateHelper().put(PropertyKeys.legendPosition, legendPosition);
    }

    /**
     * Returns the Java object holding the chart data and data configuration.
     * 
     * @return the Java object holding the chart data and data configuration
     */
    public ChartModel getChartModel()
    {
        return (ChartModel) getStateHelper().eval(PropertyKeys.chartModel, null);
    }

    /**
     * Sets the Java object holding the chart data and data configuration.
     * 
     * @param model the Java object holding the chart data and data configuration
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
        chartModel,

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

        /**
         *
         */
        title
    }
}
