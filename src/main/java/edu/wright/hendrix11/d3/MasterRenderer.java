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

package edu.wright.hendrix11.d3;

import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import java.io.IOException;

/**
 * @author Joe Hendrix
 */
public abstract class MasterRenderer extends Renderer
{

    /**
     *
     * @param writer
     * @param id
     * @param styleClass
     * @param style
     * @throws IOException
     */
    protected void encodeContainerDiv(ResponseWriter writer, String id, String styleClass, String style) throws IOException
    {
        if ( id == null )
            throw new NullPointerException("Id can't be null!");

        writer.startElement("div", null);
        writer.writeAttribute("id", id, "id");

        if ( style != null )
        {
            writer.writeAttribute("style", style, "style");
        }

        if ( styleClass != null )
        {
            writer.writeAttribute("class", styleClass, "styleClass");
        }

        writer.endElement("div");
    }

    /**
     *
     * @param writer
     * @throws IOException
     */
    protected void startScript(ResponseWriter writer) throws IOException
    {
        writer.startElement("script", null);
        writer.writeAttribute("type", "text/javascript", null);
    }

    /**
     *
     * @param writer
     * @throws IOException
     */
    protected void endScript(ResponseWriter writer) throws IOException
    {
        writer.endElement("script");
    }
}
