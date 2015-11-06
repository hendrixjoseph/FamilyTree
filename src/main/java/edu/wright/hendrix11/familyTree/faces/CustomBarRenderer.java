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
import org.primefaces.component.chart.renderer.BarRenderer;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.util.ComponentUtils;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
public class CustomBarRenderer extends BarRenderer
{
    private static final Logger LOG = Logger.getLogger(CustomBarRenderer.class.getName());

    @Override
    protected void encodeData(FacesContext context, Chart chart) throws IOException
    {
        ResponseWriter writer = context.getResponseWriter();
        BarChartModel model = (BarChartModel) chart.getModel();
        boolean horizontal = model.getOrientation().equals("horizontal");

        //data
        StringBuilder sb = new StringBuilder(",data:[");
        for ( ChartSeries series : model.getSeries() )
        {
            LOG.log(Level.INFO, series.getLabel() + " size: " + series.getData().size());

            sb.append("[");

            for ( Number data : series.getData().values() )
            {
                int i = 1;

                String value = data != null ? data.toString() : "null";

                if ( horizontal )
                {
                    sb.append("[");
                    sb.append(value + "," + i);
                    sb.append("]");

                    i++;
                }
                else
                {
                    sb.append(value);
                }

                sb.append(",");
            }

            sb.setLength(sb.length() - 1);
            sb.append("],");
        }

        sb.setLength(sb.length() - 1);
        sb.append("]");

        writer.write(sb.toString());
    }

    @Override
    protected void encodeOptions(FacesContext context, Chart chart) throws IOException
    {
        super.encodeOptions(context, chart);

        ResponseWriter writer = context.getResponseWriter();
        BarChartModel model = (BarChartModel) chart.getModel();
        String orientation = model.getOrientation();
        int barPadding = model.getBarPadding();
        int barMargin = model.getBarMargin();
        int barWidth = model.getBarWidth();
        List<String> ticks = model.getTicks();

        writer.write(",series:[");
        for ( Iterator<ChartSeries> it = model.getSeries().iterator(); it.hasNext(); )
        {
            ChartSeries series = (ChartSeries) it.next();
            series.encode(writer);

            if ( it.hasNext() )
            {
                writer.write(",");
            }
        }
        writer.write("]");

        writer.write(",ticks:[");
        for ( Iterator<String> tickIt = ticks.iterator(); tickIt.hasNext(); )
        {
            writer.write("\"" + ComponentUtils.escapeText(tickIt.next()) + "\"");
            if ( tickIt.hasNext() )
            {
                writer.write(",");
            }
        }
        writer.write("]");

        if ( orientation != null )
            writer.write(",orientation:\"" + orientation + "\"");
        if ( barPadding != 8 )
            writer.write(",barPadding:" + barPadding);
        if ( barMargin != 10 )
            writer.write(",barMargin:" + barMargin);
        if ( barWidth != 0 )
            writer.write(",barWidth:" + barWidth);
        if ( model.isStacked() )
            writer.write(",stackSeries:true");
        if ( model.isZoom() )
            writer.write(",zoom:true");
        if ( model.isAnimate() )
            writer.write(",animate:true");
        if ( model.isShowPointLabels() )
            writer.write(",showPointLabels:true");
        if ( model.isShowDatatip() )
        {
            writer.write(",datatip:true");
            if ( model.getDatatipFormat() != null )
                writer.write(",datatipFormat:\"" + model.getDatatipFormat() + "\"");
        }
    }
}
