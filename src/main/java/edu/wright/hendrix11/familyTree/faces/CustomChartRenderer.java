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

import edu.wright.hendrix11.svg.Group;
import edu.wright.hendrix11.svg.Rectangle;
import edu.wright.hendrix11.svg.Svg;
import edu.wright.hendrix11.svg.Text;
import edu.wright.hendrix11.svg.Translate;

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

        Svg svg = new Svg();
        svg.setStyleClass(chart.getStyleClass()); // writer.writeAttribute("class", chart.getStyleClass(), "styleClass");
        svg.setWidth(width);
        svg.setHeight(height);

        Group group = new Group();
        group.setTransform(new Translate(40,20));

        svg.addComponent(group);

        Group xAxis = new Group();
        xAxis.setStyleClass("x-axis");

        group.addComponent(xAxis);

        double xLabel = 0;
        double xBar = 0;

        for(String label : model.getAxisLabels())
        {
            Text text = new Text(label);
            text.setX(xLabel);
            text.setY(9);
            // writer.writeAttribute("dy",".75em", null);
            text.setStyle("text-anchor: middle;");
            text.setStyleClass("x-axis-label");

            xAxis.addComponent(text);

            xLabel += labelWidth;

            for(Integer i : model.getData(label))
            {
                double barHeight = height - i * barHeightScale;

                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(barHeight);
                rectangle.setWidth(barWidth -  1);
                rectangle.setX(xBar);
                rectangle.setY(height - barHeight);
                rectangle.setStyleClass("bar");

                group.addComponent(rectangle);

                xBar += barWidth;
            }
        }

        svg.encodeAll(writer);
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException
    {
        super.encodeBegin(context, component);
    }
}
