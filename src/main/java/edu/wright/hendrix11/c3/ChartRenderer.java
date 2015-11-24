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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
@FacesRenderer(rendererType = ChartComponent.DEFAULT_RENDERER, componentFamily = ChartComponent.COMPONENT_FAMILY)
public class ChartRenderer extends Renderer
{
    private static final Logger LOG = Logger.getLogger(ChartRenderer.class.getName());

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

        if ( chart.getStyleClass() != null )
        {
            writer.writeAttribute("class", chart.getStyleClass(), "styleClass");
        }

        writer.endElement("div");

        encodeScript(chart, model, writer);
    }

    private void encodeScript(ChartComponent chart, ChartModel model, ResponseWriter writer) throws IOException
    {
        writer.startElement("script", null);
        writer.writeAttribute("type", "text/javascript", null);

        writer.write("var chart = c3.generate({");
        writer.write("bindto:'#");
        writer.write(chart.getId());
        writer.write("',");

        encodeData(chart, model, writer);
        encodeColors(model, writer);
        encodeAxis(model, writer);
        encodeLegend(chart, writer);
        encodeGrid(chart, writer);

        writer.write("});");

        writer.endElement("script");
    }
    
    private void encodeColors(ChartModel model, ResponseWriter writer) throws IOException
    {
        if(model.getColors() != null && !model.getColors().isEmpty())
        {
            writer.write(",color:{pattern:['");
            writer.write(StringUtils.join(model.getColors(), "','"));
            writer.write("']}");
        }
    }

    private void encodeLegend(ChartComponent chart, ResponseWriter writer) throws IOException
    {
        if ( chart.getShowLegend() != null || chart.getLegendPosition() != null )
        {
            writer.write(",legend:{");

            if ( chart.getShowLegend() != null )
            {
                writer.write("show:");
                writer.write(chart.getShowLegend().toString());
            }

            if ( chart.getShowLegend() != null && chart.getLegendPosition() != null )
            {
                writer.write(",");
            }

            if ( chart.getLegendPosition() != null )
            {
                writer.write("position:'");
                writer.write(chart.getLegendPosition());
                writer.write("'");
            }

            writer.write("}");
        }
    }

    private void encodeGrid(ChartComponent chart, ResponseWriter writer) throws IOException
    {
        if ( chart.getGridX() != null || chart.getGridY() != null )
        {
            writer.write(",grid:{");

            if ( chart.getGridX() != null )
            {
                writer.write("x:{show:");
                writer.write(chart.getGridX().toString());
                writer.write("}");
            }

            if ( chart.getGridX() != null && chart.getGridY() != null )
            {
                writer.write(",");
            }

            if ( chart.getGridY() != null )
            {
                writer.write("y:{show:");
                writer.write(chart.getGridY().toString());
                writer.write("}");
            }

            writer.write("}");
        }
    }

    private void encodeAxis(ChartModel model, ResponseWriter writer) throws IOException
    {
        if ( model.getxAxis() != null || model.getyAxis() != null )
        {
            writer.write(",axis:{");

            if ( model.getxAxis() != null )
            {
                writer.write(model.getxAxis().toString());
            }

            if ( model.getxAxis() != null && model.getyAxis() != null )
            {
                writer.write(",");
            }

            if ( model.getyAxis() != null )
            {
                writer.write(model.getyAxis().toString());
            }

            writer.write("}");
        }
    }

    private void encodeData(ChartComponent chart, ChartModel model, ResponseWriter writer) throws IOException
    {
        writer.write("data:{x:'x',");

        if ( model.hasArrayData() )
        {
            encodeArrayData(chart, model, writer);
        }
        else
        {
            writer.write("columns:[['");
            
            if ( model.getxAxis() != null && model.getxAxis().getLabel() != null && model.getxAxis().getLabel().hasText() )
            {
                writer.write(model.getxAxis().getLabel().getText());
            }
            else
            {
                writer.write("x");
            }
            
            writer.write("',");

            writer.write("'");
            writer.write(StringUtils.join(model.getAxisLabels(), "','"));
            writer.write("'");

            writer.write("],['");
                
            if ( model.getyAxis() != null && model.getyAxis().getLabel() != null && model.getyAxis().getLabel().hasText() )
            {
                writer.write(model.getyAxis().getLabel().getText());
            }
            else
            {
                writer.write("data");
            }
                
            writer.write("','");
            writer.write(StringUtils.join(model.getData().values(), "','"));
            writer.write("']]");
        }

        if ( chart.getType() != null )
        {
            writer.write(",type:'");
            writer.write(chart.getType());
            writer.write("'");
        }

        writer.write("}");
    }

    private void encodeArrayData(ChartComponent chart, ChartModel model, ResponseWriter writer) throws IOException
    {
        List<String> barLabels = model.getBarLabels();

        if ( barLabels == null || barLabels.isEmpty() )
        {
            barLabels = new ArrayList<>();

            for ( int i = 0; i < model.getArrayData().values().iterator().next().length; i++ )
            {
                barLabels.add("data" + i);
            }
        }

        List<StringBuilder> data = new ArrayList<>();

        for ( Object label : model.getAxisLabels() )
        {
            StringBuilder sb = new StringBuilder();

            sb.append("[");
            sb.append("'");
            sb.append(label);
            sb.append("','");
            sb.append(StringUtils.join(model.getArrayData(label), "','"));
            sb.append("']");

            data.add(sb);
        }

        writer.write("rows:[['x','");
        writer.write(StringUtils.join(barLabels, "','"));
        writer.write("'],");
        writer.write(StringUtils.join(data, ","));

        writer.write("]");
    }
}
