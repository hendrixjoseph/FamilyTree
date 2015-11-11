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

package edu.wright.hendrix11.svg.jsf;

import edu.wright.hendrix11.svg.number.Percent;

import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.behavior.ClientBehavior;

/**
 * @author Joe Hendrix
 */
@FacesComponent(value = ChartComponent.COMPONENT_TYPE)
@ResourceDependency(library = "css", name = "chart.css")
public class ChartComponent extends UIComponentBase
{
    public static final String COMPONENT_FAMILY = "edu.wright.hendrix11.svg";
    public static final String COMPONENT_TYPE = "edu.wright.hendrix11.svg.jsf.ChartComponent";
    public static final String DEFAULT_RENDERER = "edu.wright.hendrix11.svg.jsf.ChartRenderer";

    public ChartComponent()
    {
        setRendererType(DEFAULT_RENDERER);
    }

    @Override
    public String getFamily()
    {
        return COMPONENT_FAMILY;
    }

    @Override
    public void addClientBehavior(String eventName, ClientBehavior behavior)
    {
        super.addClientBehavior(eventName, behavior);
    }

    public String getStyleClass()
    {
        return (String) getStateHelper().eval(PropertyKeys.styleClass, null);
    }

    public void setStyleClass(String string)
    {
        getStateHelper().put(PropertyKeys.styleClass, string);
    }

    public ChartModel getChartModel()
    {
        return (ChartModel) getStateHelper().eval(PropertyKeys.chartModel, null);
    }

    public void setChartModel(ChartModel model)
    {
        getStateHelper().put(PropertyKeys.chartModel, model);
    }

    public Number getHeight()
    {
        return (Number) getStateHelper().eval(PropertyKeys.height, null);
    }

    public Number getWidth()
    {
        return (Number) getStateHelper().eval(PropertyKeys.width, null);
    }

    public void setWidth(Integer width)
    {
        getStateHelper().put(PropertyKeys.width, width);
    }

    public void setWidth(Double width)
    {
        getStateHelper().put(PropertyKeys.width, width);
    }

    public void setHeight(Integer height)
    {
        getStateHelper().put(PropertyKeys.height, height);
    }

    public void setHeight(Double height)
    {
        getStateHelper().put(PropertyKeys.height, height);
    }

    protected enum PropertyKeys
    {
        styleClass,
        chartModel,
        width,
        height
    }
}
