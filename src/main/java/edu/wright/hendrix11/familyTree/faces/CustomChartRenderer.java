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
import org.primefaces.component.chart.ChartRenderer;
import org.primefaces.component.chart.renderer.BasePlotRenderer;
import org.primefaces.component.chart.renderer.BubbleRenderer;
import org.primefaces.component.chart.renderer.DonutRenderer;
import org.primefaces.component.chart.renderer.LineRenderer;
import org.primefaces.component.chart.renderer.MeterGaugeRenderer;
import org.primefaces.component.chart.renderer.OhlcRenderer;
import org.primefaces.component.chart.renderer.PieRenderer;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Joe Hendrix
 */
@FacesRenderer(rendererType = CustomChart.DEFAULT_RENDERER, componentFamily = CustomChart.COMPONENT_FAMILY)
public class CustomChartRenderer extends Renderer
{
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException
    {
        super.encodeEnd(context, component);

        CustomChart chart = (CustomChart)component;
        ChartModel model = chart.getChartModel();

        double width = 900;
        double height = 500;
        double barWidth = width / model.getNumValues();
        double labelWidth = width / model.getNumLabels();
        double barHeightScale = height / model.getMax();

        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("svg", null);
        writer.writeAttribute("class", chart.getStyleClass(), "styleClass");
        writer.writeAttribute("width", width, null);
        writer.writeAttribute("height",height, null);

        double x = 0;

        writer.startElement("g", null);
        writer.writeAttribute("transform","translate(40,20)",null);

        writer.startElement("g",null);
        writer.writeAttribute("class","x-axis", null);
//        writer.writeAttribute("transform","translate(0," + height + ")", null);

        for(String label :model.getAxisLabels())
        {
//            writer.startElement("g", null);
//            writer.writeAttribute("transform","translate(" + x + ",0",null);
//            writer.writeAttribute("class","tick",null);
//
//            writer.startElement("line",null);
//            writer.writeAttribute("x2","0",null);
//            writer.writeAttribute("y2","6",null);
//            writer.endElement("line");

            writer.startElement("text",null);
            writer.writeAttribute("x",x,null);
            writer.writeAttribute("y","9",null);
            writer.writeAttribute("dy",".75em", null);
            writer.writeAttribute("style","text-anchor: middle;",null);
            writer.writeAttribute("class","x-axis-label",null);
            writer.write(label);
            writer.endElement("text");

//            writer.endElement("g");

            x += labelWidth;
        }

        writer.endElement("g");

        x = 0;

        for(String label : model.getAxisLabels())
        {
            for(Integer i : model.getData(label))
            {
                double barHeight = height - i * barHeightScale;

                writer.startElement("rect", null);
                writer.writeAttribute("height", barHeight, null);
                writer.writeAttribute("width", barWidth - 1, null);
                writer.writeAttribute("x",x,null);
                writer.writeAttribute("y",height - barHeight,null);
                writer.writeAttribute("class","bar",null);
                writer.endElement("rect");

                x += barWidth;
            }
        }

        writer.endElement("g");

        writer.endElement("svg");
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException
    {
        super.encodeBegin(context, component);
    }
}
