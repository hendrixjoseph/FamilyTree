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

package edu.wright.hendrix11.d3.chart;

import org.apache.commons.lang3.StringUtils;

import edu.wright.hendrix11.d3.MasterRenderer;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
@FacesRenderer(rendererType = ChartComponent.DEFAULT_RENDERER, componentFamily = ChartComponent.COMPONENT_FAMILY)
public class ChartRenderer extends MasterRenderer
{
    private static final Logger LOG = Logger.getLogger(ChartRenderer.class.getName());

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException
    {
        ChartComponent chart = (ChartComponent) component;
        ChartModel model = chart.getChartModel();

        ResponseWriter writer = context.getResponseWriter();

        if ( chart.getTitle() != null )
        {
            writer.startElement("div", null);
            writer.writeAttribute("style", "text-align: center;", null);
            writer.writeAttribute("class", "title", null);
            writer.write(chart.getTitle());
            writer.endElement("div");
        }

        encodeContainerDiv(writer, chart.getId(), chart.getStyleClass(), chart.getStyle());

        encodeScript(chart, model, writer);
    }

    private void encodeScript(ChartComponent chart, ChartModel model, ResponseWriter writer) throws IOException
    {
        startScript(writer);

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

        endScript(writer);
    }

    private void encodeColors(ChartModel model, ResponseWriter writer) throws IOException
    {
        if ( model.getColors() != null && !model.getColors().isEmpty() )
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
        String x = "x";

        if ( model.getxAxis() != null && model.getxAxis().getLabel() != null && model.getxAxis().getLabel().hasText() )
        {
            x = model.getxAxis().getLabel().getText();
        }

        writer.write("data:{");

        if ( chart.getType().equals("pie") && !model.hasArrayData() )
        {
            encodePieData(model, writer);
        }
        else
        {
            writer.write("x:'");
            writer.write(x);
            writer.write("',");

            if ( model.hasArrayData() )
            {
                encodeArrayData(model, writer, x);
            }
            else
            {
                encodeRegularData(model, writer, x);
            }
        }

        if ( chart.getType() != null )
        {
            writer.write(",type:'");
            writer.write(chart.getType());
            writer.write("'");
        }

        writer.write("}");
    }

    private void encodeRegularData(ChartModel model, ResponseWriter writer, String x) throws IOException
    {
        writer.write("columns:[['");
        writer.write(x);
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

    private void encodePieData(ChartModel model, ResponseWriter writer) throws IOException
    {
        writer.write("columns:[");

        StringJoiner sj = new StringJoiner(",");

        for ( String label : model.getAxisLabels() )
        {
            StringBuilder sb = new StringBuilder("['");
            sb.append(label);
            sb.append("','");
            sb.append(model.getData(label).toString());
            sb.append("']");

            sj.add(sb);
        }

        writer.write(sj.toString());
        writer.write("]");
    }

    private void encodeArrayData(ChartModel model, ResponseWriter writer, String x) throws IOException
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

        writer.write("rows:[['");
        writer.write(x);
        writer.write("','");
        writer.write(StringUtils.join(barLabels, "','"));
        writer.write("'],");
        writer.write(StringUtils.join(data, ","));

        writer.write("]");
    }
}
