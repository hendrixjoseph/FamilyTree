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
     * Returns the width of the entire cloud.
     *
     * @return the width of the entire cloud
     */
    public Integer getWidth()
    {
        return (Integer) getStateHelper().eval(PropertyKeys.width, null);
    }

    /**
     * Sets the width of the entire cloud.
     *
     * @param width the width of the entire cloud
     */
    public void setWidth(Integer width)
    {
        getStateHelper().put(PropertyKeys.width, width);
    }

    /**
     * Returns the height of the entire cloud.
     *
     * @return the height of the entire cloud
     */
    public Integer getHeight()
    {
        return (Integer) getStateHelper().eval(PropertyKeys.height, null);
    }

    /**
     * Sets the height of the entire cloud.
     *
     * @param height the height of the entire cloud
     */
    public void setHeight(Integer height)
    {
        getStateHelper().put(PropertyKeys.height, height);
    }

    /**
     * Returns the numerical padding for each word / String.
     *
     * @return the numerical padding for each word / String
     */
    public Integer getPadding()
    {
        return (Integer) getStateHelper().eval(PropertyKeys.padding, null);
    }

    /**
     * Sets the numerical padding for each word / String.
     *
     * @param padding the numerical padding for each word / String
     */
    public void setPadding(Integer padding)
    {
        getStateHelper().put(PropertyKeys.padding, padding);
    }

    /**
     * Returns the font face for each word.
     *
     * @return the font face for each word
     */
    public String getFont()
    {
        return (String) getStateHelper().eval(PropertyKeys.font, null);
    }

    /**
     * Sets the font face for each word.
     *
     * @param font the font face for each word
     */
    public void setFont(String font)
    {
        getStateHelper().put(PropertyKeys.font, font);
    }

    /**
     * Returns the map of words (or rather, Strings, as they can have spaces) to the font size of that word. Typcially
     * the size will be the number of occurences, but that is not a requirement.
     *
     * @return the map of words to the  font size of that word
     */
    public Map<String, Integer> getWords()
    {
        return (Map<String, Integer>) getStateHelper().eval(PropertyKeys.words, null);
    }

    /**
     * Sets the map of words (or rather, Strings, as they can have spaces) to the font size of that word. Typcially the
     * size will be the number of occurences, but that is not a requirement.
     *
     * @param words the map of words to the  font size of that word
     */
    public void setWords(Map<String, Integer> words)
    {
        getStateHelper().put(PropertyKeys.words, words);
    }

    /**
     *
     */
    protected enum PropertyKeys
    {

        /**
         *
         */
        width,

        /**
         *
         */
        height,

        /**
         *
         */
        padding,

        /**
         *
         */
        font,

        /**
         *
         */
        words
    }
}
