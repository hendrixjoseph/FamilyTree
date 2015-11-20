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

package edu.wright.hendrix11.c3;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import java.io.IOException;

/**
 * @author Joe Hendrix
 */
@FacesRenderer(rendererType = ChartComponent.DEFAULT_RENDERER, componentFamily = ChartComponent.COMPONENT_FAMILY)
public class ChartRenderer extends Renderer
{
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException
    {
        ChartComponent chart = (ChartComponent) component;
        ChartModel model = chart.getChartModel();

        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("div", null);
        writer.writeAttribute("id", chart.getId(), "id");

        if ( chart.getStyle() != null )
        {
            writer.writeAttribute("style", chart.getStyle(), "style");
        }

        writer.endElement("div");

        writer.startElement("script", null);
        writer.writeAttribute("type", "text/javascript", null);

        writer.write("var chart = c3.generate({");
        writer.write("bindto:'#");
        writer.write(chart.getId());
        writer.write("',");
        writer.write("data:{");
        writer.write("columns:[['mydata1',");

        StringBuilder sb = new StringBuilder(model.getData().values().size() * 3);

        for(Number data : model.getData().values())
        {
            sb.append(data).append(",");
        }

        sb.setLength(sb.length() - 1);

        writer.write(sb.toString());
        writer.write("]]");

//        writer.write("['mydata1',30,200,100,400,150,250],");
//        writer.write("['data2',50,20,10,40,15,25]");
//        writer.write("]");

        if(chart.getType() != null)
        {
            writer.write(",type:'");
            writer.write(chart.getType());
            writer.write("'");
        }

        writer.write("}});");

        writer.endElement("script");
    }
}
