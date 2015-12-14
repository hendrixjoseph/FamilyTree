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

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import java.io.IOException;
import java.util.Map;
import java.util.StringJoiner;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
@FacesRenderer(rendererType = WordCloudComponent.DEFAULT_RENDERER,
               componentFamily = WordCloudComponent.COMPONENT_FAMILY)
public class WordCloudRenderer extends MasterRenderer
{
    private static final Logger LOG = Logger.getLogger(WordCloudRenderer.class.getName());

    private Map<String, ? extends Number> scaleWords(Map<String, Integer> words, Integer scaleTo, Boolean forceStretch )
    {
      int max = scaleTo;
      
      for (Integer value : words.values())
      {
        max = Math.max(max, value);
      }
      
      if(max == scaleTo || (max < scaleTo && forceStretch != null && !forceStretch))
      {
        return words;
      }
      
      Map<String, Double> scaledWords = new LinkedHashMap<>(words.size());
      
      double scale = ((double) scaleTo) / max;
      
      for(Map.Entry<String, Integer> entry : words.entrySet())
      {
        double scaled = entry.getValue() * scale;
        scaledWords.put(entry.getKey(), scaled);
      }
      
      return scaledWords;
    }

    /**
     * {@inheritDoc}
     *
     * @throws java.io.IOException
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException
    {
        WordCloudComponent cloud = (WordCloudComponent) component;
        ResponseWriter writer = context.getResponseWriter();
        
        Map<String, ? extends Number> words = cloud.getWords();
        
        if(cloud.getScaleTo() != null)
        {
          words = scaleWords(words, cloud.getScaleTo(), cloudisForceStretch());
        }

        String font = cloud.getFont() != null ? cloud.getFont() : "Impact";

        encodeContainerDiv(writer, cloud.getId(), cloud.getStyleClass(), cloud.getStyle());

        startScript(writer);

        writer.write("var fill=d3.scale.category20();");

        writer.write("var layout=d3.layout.cloud().size([");
        writer.write(cloud.getWidth().toString());
        writer.write(",");
        writer.write(cloud.getHeight().toString());
        writer.write("])");
        encodeWords(writer, words);
        encodePadding(writer, cloud.getPadding());
        writer.write(".rotate(function(){return ~~(Math.random()*2)*90;})");
        writer.write(".font('");
        writer.write(font);
        writer.write("').fontSize(function(d){return d.size;})");
        // writer.write(".text(function(d){console.log(d);return d.text;})");
        writer.write(".on('end',draw);");

        writer.write("layout.start();");

        writer.write("function draw(w){d3.select('#");
        writer.write(cloud.getId());
        writer.write("').append('svg')");
        writer.write(".attr('width',layout.size()[0])");
        writer.write(".attr('height',layout.size()[1])");
        writer.write(".append('g')");
        writer.write(".attr('transform','translate('+layout.size()[0]/2+','+layout.size()[1]/2+')')");
        writer.write(".selectAll('text').data(w)");
        writer.write(".enter().append('text')");
        writer.write(".style('font-size',function(d){return d.size+'px';})");
        writer.write(".style('font-family','");
        writer.write(font);
        writer.write("').style('fill',function(d,i){return fill(i);})");
        writer.write(".attr('text-anchor','middle')");
        writer.write(".attr('transform',function(d){");
        writer.write("return 'translate('+[d.x, d.y]+')rotate('+d.rotate+')';})");
        writer.write(".text(function(d){return d.text;});}");

        endScript(writer);
    }

    private void encodeWords(ResponseWriter writer, Map<String, ? extends Number> wordMap) throws IOException
    {
        writer.write(".words([");

        StringJoiner sj = new StringJoiner(",");

        for (Map.Entry<String, ? extends Number> entry : words.entrySet())
        {
            StringBuilder sb = new StringBuilder("['");
            sb.append(entry.getKey());
            sb.append("',");
            sb.append(entry.getValue());
            sb.append("]");

            sj.add(sb);
        }

        writer.write(sj.toString());
        writer.write("].map(function(d){return {text:d[0],size:d[1]}}))");
    }

    private void encodePadding(ResponseWriter writer, Integer padding) throws IOException
    {
        if ( padding != null )
        {
            writer.write(".padding(");
            writer.write(padding.toString());
            writer.write(")");
        }
    }
}
