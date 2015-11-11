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
import edu.wright.hendrix11.svg.transform.Scale;
import edu.wright.hendrix11.svg.transform.Translate;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import java.io.IOException;
import java.util.List;

/**
 * @author Joe Hendrix
 */
@FacesRenderer(rendererType = ChartComponent.DEFAULT_RENDERER, componentFamily = ChartComponent.COMPONENT_FAMILY)
public class ChartRenderer extends Renderer
{
    private static final int BOTTOM_SPACE = 25;
    private static final int LEGEND_BOX_SIZE = 10;

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
        double barHeightScale = (height - BOTTOM_SPACE) / model.getMax();

        ResponseWriter writer = context.getResponseWriter();

        Svg svg = new Svg();
        svg.setStyleClass(chart.getStyleClass(),"styleClass");
        svg.setWidth(width);
        svg.setHeight(height);

        Group legend = new Group();
        svg.addComponent(legend);

        for (  String label : model.getBarLabels() )
        {
            Rectangle legendBox = new Rectangle();
            legendBox.setHeight(LEGEND_BOX_SIZE);
            legendBox.setWidth(LEGEND_BOX_SIZE);
            legendBox.setStyleClass("bar " + label);

            Text legendText = new Text();
            legendText.setText(label);

            legend.addComponent(legendBox);
            legend.addComponent(legendText);
        }

        Group group = new Group();

        svg.addComponent(group);

        Group xAxis = new Group();
        xAxis.setStyleClass("x-axis");
        xAxis.setTransform(new Translate(0,height - BOTTOM_SPACE));

        group.addComponent(xAxis);

        Percent<Double> xLabel = new Percent<>(0.0);
        Percent<Double> xBar = new Percent<>(0.0);

        for ( String label : model.getAxisLabels() )
        {
            Percent<Double> startX;
            Percent<Double> endX;

            Integer[] data = model.getData(label);

            startX = xBar;

            for(int i = 0; i < data.length; i++)
            {
                Integer datum = data[i];

                double barHeight = datum * barHeightScale;

                Rectangle bar = new Rectangle();
                bar.setHeight(barHeight);
                bar.setWidth(barWidth.add(-0.1));
                bar.setX(xBar);
                bar.setY(height - datum * barHeightScale - BOTTOM_SPACE);

                if(model.getBarLabels() != null && model.getBarLabels().size() > i)
                {
                    bar.setStyleClass("bar " + model.getBarLabels().get(i));
                }
                else
                {
                    bar.setStyleClass("bar");
                }

                group.addComponent(bar);

                xBar = xBar.add(barWidth.doubleValue());

                xLabel = xLabel.add(labelWidth.doubleValue());
            }

            endX = xBar;

            Text text = new Text(label);
            text.setX(startX.add(endX.doubleValue()).divide(2.0));
            text.setY(9);
            // writer.writeAttribute("dy",".75em", null);
            text.setStyleClass("x-axis-label");

            xAxis.addComponent(text);
        }

        svg.encodeAll(writer);
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException
    {
        super.encodeBegin(context, component);
    }
}
