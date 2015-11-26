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

package edu.wright.hendrix11.d3.wordCloud;

import edu.wright.hendrix11.d3.MasterRenderer;
import edu.wright.hendrix11.d3.chart.ChartComponent;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
@FacesRenderer(rendererType = WordCloudComponent.DEFAULT_RENDERER, componentFamily = WordCloudComponent.COMPONENT_FAMILY)
public class WordCloudRenderer extends MasterRenderer
{

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException
    {
        WordCloudComponent cloud = (WordCloudComponent) component;
        ResponseWriter writer = context.getResponseWriter();

        encodeContainerDiv(writer, "test", null, null);

        startScript(writer);

        writer.write("var fill = d3.scale.category20();");

        writer.write("var layout = d3.layout.cloud()");
        writer.write(".size([960, 600])");
        writer.write(".words([");
        writer.write("'Hello', 'world', 'normally', 'you', 'want', 'more', 'words',");
        writer.write("'than', 'this'].map(function(d) {");
        writer.write("return {text: d, size: 10 + Math.random() * 90};");
        writer.write("}))");
        writer.write(".padding(5)");
        writer.write(".rotate(function() { return ~~(Math.random() * 2) * 90; })");
        writer.write(".font('Impact')");
        writer.write(".fontSize(function(d) { return d.size; })");
        writer.write(".on('end', draw);");

        writer.write("layout.start();");

        writer.write("function draw(words) {");
        writer.write("d3.select('#test').append('svg')");
        writer.write(".attr('width', layout.size()[0])");
        writer.write(".attr('height', layout.size()[1])");
        writer.write(".append('g')");
        writer.write(".attr('transform', 'translate(' + layout.size()[0] / 2 + ',' + layout.size()[1] / 2 + ')')");
        writer.write(".selectAll('text')");
        writer.write(".data(words)");
        writer.write(".enter().append('text')");
        writer.write(".style('font-size', function(d) { return d.size + 'px'; })");
        writer.write(".style('font-family', 'Impact')");
        writer.write(".style('fill', function(d, i) { return fill(i); })");
        writer.write(".attr('text-anchor', 'middle')");
        writer.write(".attr('transform', function(d) {");
        writer.write("return 'translate(' + [d.x, d.y] + ')rotate(' + d.rotate + ')';");
        writer.write("})");
        writer.write(".text(function(d) { return d.text; });");
        writer.write("}");

        endScript(writer);
    }
}