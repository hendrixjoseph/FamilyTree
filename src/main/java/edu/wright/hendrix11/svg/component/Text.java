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

package edu.wright.hendrix11.svg.component;

import javax.faces.context.ResponseWriter;

import java.io.IOException;

/**
 * @author Joe Hendrix
 */
public class Text extends SvgComponent
{
    String text;

    public Text()
    {
        super("text");
    }

    public Text(String text)
    {
        this();
        this.text = text;
    }

    @Override
    public void encodeMiddle(ResponseWriter writer) throws IOException
    {
        writer.write(text);
    }

    public void setX(Number x)
    {
        putAttribute("x", x);
    }

    public void setY(Number y)
    {
        putAttribute("y", y);
    }

    public void setDx(Number dx)
    {
        putAttribute("dx", dx);
    }

    public void setDy(Number dy)
    {
        putAttribute("dy", dy);
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public void setTextAnchor(TextAnchor textAnchor)
    {
        putAttribute("text-anchor", textAnchor);
    }

    public enum TextAnchor
    {
        start,
        middle,
        end,
        inherit
    }
}
