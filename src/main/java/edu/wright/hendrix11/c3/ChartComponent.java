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

package edu.wright.hendrix11.svg.jsf.barChart;

/**
 * @author Joe Hendrix
 */
@FacesComponent(value = ChartComponent.COMPONENT_TYPE)
@ResourceDependency(library = "css", name = "chart.css")
public class ChartComponent extends UIComponentBase
{
    /**
     *
     */
    public static final String COMPONENT_TYPE = "edu.wright.hendrix11.c3.ChartComponent";

    /**
     *
     */
    public static final String DEFAULT_RENDERER = "edu.wright.hendrix11.c3.ChartRenderer";
    
        /**
     *
     */
    public static final String COMPONENT_FAMILY = "edu.wright.hendrix11.c3";

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
     * @param string
     */
    public void setStyleClass(String string)
    {
        getStateHelper().put(PropertyKeys.styleClass, string);
    }
    
        /**
     * @return
     */
    public String getStyle()
    {
        return (String) getStateHelper().eval(PropertyKeys.style, null);
    }

    /**
     * @param string
     */
    public void setStyle(String string)
    {
        getStateHelper().put(PropertyKeys.style, string);
    }

    public BarChartComponent()
    {
        setRendererType(DEFAULT_RENDERER);
    }

    /**
     * @return
     */
    public BarChartModel getChartModel()
    {
        return (BarChartModel) getStateHelper().eval(PropertyKeys.chartModel, null);
    }

    /**
     * @param model
     */
    public void setChartModel(BarChartModel model)
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
        style
    }
}
