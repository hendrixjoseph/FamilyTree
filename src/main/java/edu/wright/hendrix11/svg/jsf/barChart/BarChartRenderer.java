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

package edu.wright.hendrix11.svg.jsf.barChart;

import edu.wright.hendrix11.svg.Svg;
import edu.wright.hendrix11.svg.component.Group;
import edu.wright.hendrix11.svg.component.Text;
import edu.wright.hendrix11.svg.component.shape.Line;
import edu.wright.hendrix11.svg.component.shape.Rectangle;
import edu.wright.hendrix11.svg.jsf.SvgJsfComponent;
import edu.wright.hendrix11.svg.number.Em;
import edu.wright.hendrix11.svg.number.Percent;
import edu.wright.hendrix11.svg.transform.Rotate;
import edu.wright.hendrix11.svg.transform.Translate;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
@FacesRenderer(rendererType = BarChartComponent.DEFAULT_RENDERER, componentFamily = SvgJsfComponent.COMPONENT_FAMILY)
public class BarChartRenderer extends Renderer
{
    private static final Logger LOG = Logger.getLogger(BarChartRenderer.class.getName());

    private static final int BOTTOM_SPACE = 25;
    private static final int TOP_SPACE = 25;
    private static final int LEGEND_BOX_SIZE = 20;

    private static final Percent<Integer> Y_AXIS_POSITION = new Percent<>(5);

    Percent<Double> width;
    private BarChartModel model;

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException
    {
        LOG.log(Level.SEVERE, "Will it render?");

        BarChartComponent chart = (BarChartComponent) component;
        model = chart.getChartModel();

        double height = chart.getHeight().doubleValue();
        width = Percent.ONE_HUNDRED;

        ResponseWriter writer = context.getResponseWriter();

        Svg svg = new Svg();
        svg.setStyleClass(chart.getStyleClass(), "styleClass");
        svg.setWidth(width);
        svg.setHeight(height);

        if ( model.getTitle() != null )
        {
            Text title = new Text();
            title.setTextAnchor(Text.TextAnchor.middle);
            title.setStyleClass("title");
            title.setX(Percent.FIFTY);
            title.setDy(Em.ONE);
            title.setText(model.getTitle());
            svg.addComponent(title);
        }

        double barHeight = height - TOP_SPACE - BOTTOM_SPACE;

        if ( model.getxAxisLabel() != null )
        {
            barHeight -= BOTTOM_SPACE;
        }

        svg.addComponent(createYAxis(barHeight));

        Svg xAxisAndBars = new Svg();
        xAxisAndBars.setHeight(Percent.ONE_HUNDRED);
        xAxisAndBars.setY(TOP_SPACE);
        xAxisAndBars.setWidth(Percent.ONE_HUNDRED.subtract(Y_AXIS_POSITION.intValue()));
        xAxisAndBars.setX(Y_AXIS_POSITION);

        xAxisAndBars.addComponent(createXAxis(barHeight));
        xAxisAndBars.addComponent(createBars(barHeight));

        svg.addComponent(xAxisAndBars);

        if ( model.hasArrayData() )
        {
            svg.addComponent(createLegend(model));
        }

        svg.encodeAll(writer);
    }

    private Svg createBars(double height)
    {
        Svg bars = new Svg();
        bars.setHeight(Percent.ONE_HUNDRED);
        bars.setWidth(Percent.ONE_HUNDRED);
        bars.setStyleClass("bars");

        Double max = model.getMax().doubleValue();

        Percent<Double> xBar = Percent.ZERO;
        Percent<Double> barWidth = width.divide(model.getNumValues().doubleValue());
        double barHeightScale = ( height ) / max;

        for ( String label : model.getAxisLabels() )
        {
            if ( model.hasArrayData() )
            {
                Number[] data = model.getArrayData(label);

                for ( int i = 0; i < data.length; i++ )
                {
                    Double datum = data[i].doubleValue();

                    double barHeight = datum * barHeightScale;

                    Rectangle bar = new Rectangle();
                    bar.setHeight(barHeight);
                    bar.setWidth(barWidth.add(-0.1));
                    bar.setX(xBar);
                    bar.setY(height - datum * barHeightScale);

                    if ( model.getBarLabels() != null && model.getBarLabels().size() > i )
                    {
                        bar.setStyleClass("bar " + model.getBarLabels().get(i));
                    }
                    else
                    {
                        bar.setStyleClass("bar");
                    }

                    bars.addComponent(bar);

                    xBar = xBar.add(barWidth.doubleValue());
                }
            }
            else
            {
                double datum = model.getData(label).doubleValue();

                double barHeight = datum * barHeightScale;

                Rectangle bar = new Rectangle();
                bar.setHeight(barHeight);
                bar.setWidth(barWidth.add(-0.1));
                bar.setX(xBar);
                bar.setY(height - datum * barHeightScale);
                bar.setStyleClass("bar");

                bars.addComponent(bar);

                xBar = xBar.add(barWidth.doubleValue());
            }
        }

        return bars;
    }

    private Group createYAxis(double height)
    {
        Group yAxis = new Group();
        yAxis.setStyleClass("y-axis");
        yAxis.setTransform(new Translate(0, TOP_SPACE));

        Group lines = new Group();
        lines.setStyleClass("lines");

        Group yAxisLabel = new Group();
        yAxisLabel.setStyleClass("labels");

        if ( model.getyAxisLabel() != null )
        {
            Group g = new Group();
            g.setTransform(new Translate(0, height / 2));

            Text label = new Text();
            label.setText(model.getyAxisLabel());
            label.setTextAnchor(Text.TextAnchor.middle);
            label.setDy(Em.ONE);
            label.setTransform(new Rotate(-90));
            g.addComponent(label);
            yAxisLabel.addComponent(g);
        }

        Line line = new Line();
        line.setX1(Y_AXIS_POSITION);
        line.setX2(Y_AXIS_POSITION);
        line.setY1(0);
        line.setY2(height);

        lines.addComponent(line);

        double max = model.getMax().doubleValue();

        for ( int i = 0; i < 5; i++ )
        {
            double y = ( i * ( height ) / 4.0 ) + 1.0;

            Line across = new Line();
            across.setX1(Y_AXIS_POSITION.subtract(0.5));
            across.setX2(Percent.ONE_HUNDRED);
            across.setY1(y);
            across.setY2(y);

            lines.addComponent(across);

            if ( i != 4 )
            {
                double labelNumber = ( 4 - i ) / 4.0 * max;
                Text yLabel = new Text();
                yLabel.setX(Y_AXIS_POSITION);
                yLabel.setY(y);
                yLabel.setDy(Em.HALF);
                yLabel.setDx(new Em<>(-0.5));
                yLabel.setText(String.format("%.2f", labelNumber));
                yLabel.setStyleClass("label");

                yAxisLabel.addComponent(yLabel);
            }
        }

        yAxis.addComponent(lines);
        yAxis.addComponent(yAxisLabel);

        return yAxis;
    }

    private Group createXAxis(double height)
    {
        Group xAxis = new Group();
        xAxis.setStyleClass("x-axis");

        if ( model.getxAxisLabel() != null )
        {
            Text xLabel = new Text();
            xLabel.setText(model.getxAxisLabel());
            xLabel.setX(Percent.FIFTY);
            xLabel.setDy(new Em<>(2));
            xLabel.setStyleClass("main label");

            xAxis.addComponent(xLabel);
        }

        xAxis.setTransform(new Translate(0, height));

        Percent<Double> startX = Percent.ZERO;
        Percent<Double> endX;
        Percent<Double> labelWidth = width.divide(model.getNumLabels().doubleValue());

        for ( String label : model.getAxisLabels() )
        {
            endX = startX.add(labelWidth.doubleValue());

            Text text = new Text(label);
            text.setX(startX.add(endX.doubleValue()).divide(2.0));
            text.setY(0);
            text.setDy(Em.ONE);
            // writer.writeAttribute("dy",".75em", null);
            text.setStyleClass("label");

            xAxis.addComponent(text);

            startX = endX;
        }

        return xAxis;
    }

    private Svg createLegend(BarChartModel model)
    {
        Svg outer = new Svg();
        outer.setX(Percent.ONE_HUNDRED);
        outer.setStyle("overflow: visible;");

        Group legend = new Group();
        legend.setTransform(new Translate(-150, TOP_SPACE + 10));
        legend.setStyleClass("legend");

        int y = 0;

        for ( String label : model.getBarLabels() )
        {
            Rectangle legendBox = new Rectangle();
            legendBox.setHeight(LEGEND_BOX_SIZE);
            legendBox.setWidth(LEGEND_BOX_SIZE);
            legendBox.setY(y);
            legendBox.setRx(5);
            legendBox.setRy(5);
            legendBox.setStyleClass("legendBox " + label);

            Text legendText = new Text();
            legendText.setText(label);
            legendText.setY(y);
            legendText.setX(LEGEND_BOX_SIZE);
            legendText.setDx(Em.ONE);
            legendText.setDy(Em.ONE);

            y += LEGEND_BOX_SIZE + 5;

            legend.addComponent(legendBox);
            legend.addComponent(legendText);
        }

        outer.addComponent(legend);
        return outer;
    }
}