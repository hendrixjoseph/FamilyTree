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

package edu.wright.hendrix11.familyTree.faces;

import org.primefaces.component.chart.Chart;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.behavior.ClientBehavior;

import java.util.Map;

/**
 * @author Joe Hendrix
 */
@FacesComponent(value = CustomChart.COMPONENT_TYPE)
@ResourceDependency(library="css", name="chart.css")
public class CustomChart extends UIComponentBase
{
    public static final String COMPONENT_FAMILY = "edu.wright.hendrix11.familyTree.faces";
    public static final String COMPONENT_TYPE = "edu.wright.hendrix11.familyTree.faces.CustomChart";
    public static final String DEFAULT_RENDERER = "edu.wright.hendrix11.familyTree.faces.CustomChartRenderer";

    protected enum PropertyKeys {
        styleClass, chartModel
    }

    public CustomChart()
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

    public String getStyleClass() {
        return (String) getStateHelper().eval(PropertyKeys.styleClass, null);
    }

    public void setStyleClass(String string) {
        getStateHelper().put(PropertyKeys.styleClass, string);
    }

    public ChartModel getChartModel() {
        return (ChartModel) getStateHelper().eval(PropertyKeys.chartModel, null);
    }

    public void setChartModel(ChartModel model) {
        getStateHelper().put(PropertyKeys.chartModel, model);
    }
}
