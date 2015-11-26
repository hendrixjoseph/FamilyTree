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

package edu.wright.hendrix11.d3.wordCloud;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

/**
 * @author Joe Hendrix
 */
@FacesComponent(value = WordCloudComponent.COMPONENT_TYPE)
@ResourceDependencies({
                              @ResourceDependency(library = "js", name = "d3.min.js"),
                              @ResourceDependency(library = "js", name = "d3.layout.cloud.js")
                      })
public class WordCloudComponent extends UIComponentBase
{
    /**
     *
     */
    public static final String COMPONENT_FAMILY = "edu.wright.hendrix11.d3";
    /**
     *
     */
    public static final String COMPONENT_TYPE = "edu.wright.hendrix11.d3.wordCloud.WordCloudComponent";
    /**
     *
     */
    public static final String DEFAULT_RENDERER = "edu.wright.hendrix11.d3.chart.WordCloudRenderer";

    /**
     *
     */
    public WordCloudComponent()
    {
        setRendererType(DEFAULT_RENDERER);
    }

    @Override
    public String getFamily()
    {
        return COMPONENT_FAMILY;
    }
}