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

import org.apache.commons.lang3.StringUtils;

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
        
        encodeScript(chart, model, writer);
    }
    
    private void encodeScript(ChartComponent chart, ChartModel model, ResponseWriter writer)
    {
        writer.startElement("script", null);
        writer.writeAttribute("type", "text/javascript", null);
        
        writer.write("var chart = c3.generate({");
        writer.write("bindto:'#");
        writer.write(chart.getId());
        writer.write("',");

        encodeData(chart, model, writer);
        encodeGrid(chart, writer);

        writer.write("});");

        writer.endElement("script");
    }
    
    private void encodeGrid(ChartComponent chart, ResponseWriter writer)
    {
        if(chart.getGridX() != null || chart.getGridY() != null)
        {
            writer.write(",grid:{");
            
            if(chart.getGridX() != null)
            {
                writer.write("x:{show:");
                writer.write(chart.getGridX());
                writer.write("}");
            }
            
            if(chart.getGridX() != null && chart.getGridY() != null)
            {
                writer.write(",");
            }
            
            if(chart.getGridY() != null)
            {
                writer.write("y:{show:");
                writer.write(chart.getGridY());
                writer.write("}");
            }
            
            writer.write("}");
        }
    }
    
    private void encodeData(ChartComponent chart, ChartModel model, ResponseWriter writer)
    {
        writer.write("data:{");
        
        if(model.hasArrayData())
        {
            
        }
        else
        {
            writer.write("columns:[['data',");
            writer.write(StringUtils.join(model.getData().values(),',');
            writer.write("]]");
        }

//        writer.write("['mydata1',30,200,100,400,150,250],");
//        writer.write("['data2',50,20,10,40,15,25]");
//        writer.write("]");

        if(chart.getType() != null)
        {
            writer.write(",type:'");
            writer.write(chart.getType());
            writer.write("'");
        }
        
        writer.write("}");
    }
}
