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

package edu.wright.hendrix11.svg.jsf.scatterPlot;

import edu.wright.hendrix11.svg.jsf.SvgJsfComponent;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import java.io.IOException;

/**
 * @author Joe Hendrix
 */
@FacesRenderer(rendererType = ScatterPlotComponent.DEFAULT_RENDERER, componentFamily = SvgJsfComponent.COMPONENT_FAMILY)
public class ScatterPlotRenderer extends Renderer
{
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException
    {
        super.encodeEnd(context, component);
    }
}
