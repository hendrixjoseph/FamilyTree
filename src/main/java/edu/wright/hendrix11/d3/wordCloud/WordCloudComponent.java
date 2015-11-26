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

import edu.wright.hendrix11.d3.MasterComponent;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

import java.util.Map;

/**
 * @author Joe Hendrix
 */
@FacesComponent(value = WordCloudComponent.COMPONENT_TYPE)
@ResourceDependencies({
                              @ResourceDependency(library = "js", name = "d3.min.js"),
                              @ResourceDependency(library = "js", name = "d3.layout.cloud.js")
                      })
public class WordCloudComponent extends MasterComponent
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

    /**
     * @return
     */
    public Integer getWidth()
    {
        return (Integer) getStateHelper().eval(PropertyKeys.width, null);
    }

    /**
     * @param width
     */
    public void setWidth(Integer width)
    {
        getStateHelper().put(PropertyKeys.width, width);
    }

    /**
     * @return
     */
    public Integer getHeight()
    {
        return (Integer) getStateHelper().eval(PropertyKeys.height, null);
    }

    /**
     * @param height
     */
    public void setHeight(Integer height)
    {
        getStateHelper().put(PropertyKeys.height, height);
    }

    /**
     * @return
     */
    public Integer getPadding()
    {
        return (Integer) getStateHelper().eval(PropertyKeys.padding, null);
    }

    /**
     * @param padding
     */
    public void setPadding(Integer padding)
    {
        getStateHelper().put(PropertyKeys.padding, padding);
    }

    /**
     * @return
     */
    public String getFont()
    {
        return (String) getStateHelper().eval(PropertyKeys.font, null);
    }

    /**
     * @param font
     */
    public void setFont(String font)
    {
        getStateHelper().put(PropertyKeys.font, font);
    }

    /**
     * @return
     */
    public Map<String, Integer> getWords()
    {
        return (Map<String, Integer>) getStateHelper().eval(PropertyKeys.words, null);
    }

    /**
     * @param words
     */
    public void setWords(Map<String, Integer> words)
    {
        getStateHelper().put(PropertyKeys.words, words);
    }

    protected enum PropertyKeys
    {

        /**
         *
         */
        width,
        height,
        padding,
        font,
        words
    }
}