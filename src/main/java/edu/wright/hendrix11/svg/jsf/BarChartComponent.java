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

import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

/**
 * @author Joe Hendrix
 */
@FacesComponent(value = BarChartComponent.COMPONENT_TYPE)
@ResourceDependency(library = "css", name = "chart.css")
public class BarChartComponent extends SvgJsfComponent
{
    public static final String COMPONENT_TYPE = "edu.wright.hendrix11.svg.jsf.BarChartComponent";
    public static final String DEFAULT_RENDERER = "edu.wright.hendrix11.svg.jsf.BarChartRenderer";

    public BarChartComponent()
    {
        setRendererType(DEFAULT_RENDERER);
    }


}
