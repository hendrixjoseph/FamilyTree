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

package edu.wright.hendrix11.svg.jsf;

import edu.wright.hendrix11.svg.Svg;
import edu.wright.hendrix11.svg.component.Group;
import edu.wright.hendrix11.svg.component.Text;
import edu.wright.hendrix11.svg.component.shape.Rectangle;
import edu.wright.hendrix11.svg.transform.Translate;

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
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException
    {
        super.encodeEnd(context, component);

        ChartComponent chart = (ChartComponent) component;
        ChartModel model = chart.getChartModel();

        double width = 100;
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
        group.setTransform(new Translate(40, 20));

        svg.addComponent(group);

        Group xAxis = new Group();
        xAxis.setStyleClass("x-axis");

        group.addComponent(xAxis);

        double xLabel = 0;
        double xBar = 0;

        for ( String label : model.getAxisLabels() )
        {
            Text text = new Text(label);
            text.setX(xLabel);
            text.setY(9);
            // writer.writeAttribute("dy",".75em", null);
            text.setStyle("text-anchor: middle;");
            text.setStyleClass("x-axis-label");

            xAxis.addComponent(text);

            xLabel += labelWidth;

            for ( Integer i : model.getData(label) )
            {
                double barHeight = height - i * barHeightScale;

                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(barHeight);
                rectangle.setWidth(barWidth - 1);
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
