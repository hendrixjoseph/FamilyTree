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
import edu.wright.hendrix11.svg.number.Percent;
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

        Percent<Double> width = new Percent<>(100.0);
        double height = chart.getHeight().doubleValue();
        Percent<Double> barWidth = width.divide((double)model.getNumValues());
        Percent<Double> labelWidth = width.divide((double) model.getNumLabels());
        double barHeightScale = height / model.getMax();

        ResponseWriter writer = context.getResponseWriter();

        Svg svg = new Svg();
        svg.setStyleClass(chart.getStyleClass(),"styleClass");
        svg.setWidth(width);
        svg.setHeight(height);

        Group group = new Group();
        group.setTransform(new Translate(40, 20));

        svg.addComponent(group);

        Group xAxis = new Group();
        xAxis.setStyleClass("x-axis");

        group.addComponent(xAxis);

        Percent<Double> xLabel = new Percent<>(0.0);
        Percent<Double> xBar = new Percent<>(0.0);

        for ( String label : model.getAxisLabels() )
        {
            Text text = new Text(label);
            text.setX(xLabel);
            text.setY(9);
            // writer.writeAttribute("dy",".75em", null);
            text.setStyle("text-anchor: middle;");
            text.setStyleClass("x-axis-label");

            xAxis.addComponent(text);

            xLabel = xLabel.add(labelWidth.doubleValue());

            Integer[] data = model.getData(label);

            for(int i = 0; i < data.length; i++)
            {
                Integer datum = data[i];

                double barHeight = height - datum * barHeightScale;

                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(barHeight);
                rectangle.setWidth(barWidth.add(-1.0));
                rectangle.setX(xBar);
                rectangle.setY(height - barHeight);

                if(model.getBarLabels() != null && model.getBarLabels().size() > i)
                {
                    rectangle.setStyleClass("bar " + model.getBarLabels().get(i));
                }
                else
                {
                    rectangle.setStyleClass("bar");
                }

                group.addComponent(rectangle);

                xBar = xBar.add(barWidth.doubleValue());
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
