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
 
 package edu.wright.hendrix11.c3;
 
 /**
 * @author Joe Hendrix
 */
@FacesRenderer(rendererType = ChartComponent.DEFAULT_RENDERER, componentFamily = ChartComponent.COMPONENT_FAMILY)
public class ChartRenderer extends Renderer
{
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException
    {
      ChartComponent chart = (ChartComponent) component;
      
      ResponseWriter writer = context.getResponseWriter();
      
      writer.startElement("div", null);
      writer.writeAttribute("id", "chart", null);
      writer.writeAttribute("style", "width: 100%; height: 300px;", null);
      writer.endElement("div");
      
      writer.startElement("script", null);
      writer.writeAttribute("type", "text/javascript", null);
      
      writer.write("var chart = c3.generate({");
      writer.write("bindto:'#chart',");
      writer.write("data:{");
      writer.write("columns:[");
      writer.write("['data1',30,200,100,400,150,250],");
      writer.write("['data2',50,20,10,40,15,25]");
      writer.write("]}});");
      
      writer.endElement("script");
    }
}
