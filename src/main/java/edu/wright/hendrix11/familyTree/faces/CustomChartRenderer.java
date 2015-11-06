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
import org.primefaces.component.chart.renderer.BarRenderer;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Joe Hendrix
 */
@FacesRenderer(rendererType = "edu.wright.hendrix11.familyTree.faces.CustomChartRenderer", componentFamily = "hendrix11.faces")
public class CustomChartRenderer extends ChartRenderer
{
    private final static String TYPE_PIE = "pie";
    private final static String TYPE_LINE = "line";
    private final static String TYPE_BAR = "bar";
    private final static String TYPE_OHLC = "ohlc";
    private final static String TYPE_DONUT = "donut";
    private final static String TYPE_BUBBLE = "bubble";
    private final static String TYPE_METERGAUGE = "metergauge";

    final static Map<String,org.primefaces.component.chart.renderer.BasePlotRenderer> CHART_RENDERERS;

    static {
        CHART_RENDERERS = new HashMap<String, org.primefaces.component.chart.renderer.BasePlotRenderer>();
        CHART_RENDERERS.put(TYPE_PIE, new PieRenderer());
        CHART_RENDERERS.put(TYPE_LINE, new LineRenderer());
        CHART_RENDERERS.put(TYPE_BAR, new BarRenderer());
        CHART_RENDERERS.put(TYPE_OHLC, new OhlcRenderer());
        CHART_RENDERERS.put(TYPE_DONUT, new DonutRenderer());
        CHART_RENDERERS.put(TYPE_BUBBLE, new BubbleRenderer());
        CHART_RENDERERS.put(TYPE_METERGAUGE, new MeterGaugeRenderer());
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        super.decodeBehaviors(context, component);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException{
        Chart chart = (Chart) component;

        encodeMarkup(context, chart);
        encodeScript(context, chart);
    }

    protected void encodeMarkup(FacesContext context, Chart chart) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String style = chart.getStyle();
        String styleClass = chart.getStyleClass();

        writer.startElement("div", null);
        writer.writeAttribute("id", chart.getClientId(context), null);
        if(style != null) writer.writeAttribute("style", style, "style");
        if(styleClass != null) writer.writeAttribute("class", styleClass, "styleClass");

        writer.endElement("div");
    }

    protected void encodeScript(FacesContext context, Chart chart) throws IOException{
        ResponseWriter writer = context.getResponseWriter();
        String type = chart.getType();
        BasePlotRenderer plotRenderer = CHART_RENDERERS.get(type);
        String clientId = chart.getClientId(context);

        startScript(writer, clientId);

        writer.write("$(function(){");
        writer.write("PrimeFaces.cw('Chart','" + chart.resolveWidgetVar() + "',{");
        writer.write("id:'" + clientId + "'");
        writer.write(",type:'" + type + "'");

        if(chart.isResponsive()) writer.write(",responsive:true");

        plotRenderer.render(context, chart);
        encodeClientBehaviors(context, chart);
        writer.write("},'charts');});");

        endScript(writer);
    }


}
