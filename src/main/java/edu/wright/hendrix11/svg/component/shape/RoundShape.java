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

package edu.wright.hendrix11.svg.component.shape;

import edu.wright.hendrix11.svg.component.SvgComponent;

import javax.faces.context.ResponseWriter;

import java.io.IOException;

/**
 * @author Joe Hendrix
 */
public abstract class RoundShape extends SvgComponent
{
    protected RoundShape(String name)
    {
        super(name);
    }

    public void setCenterX(Number cx)
    {
        putAttribute("cx", cx);
    }

    public void setCenterY(Number cy)
    {
        putAttribute("cy", cy);
    }

    @Override
    public void encodeMiddle(ResponseWriter writer) throws IOException
    {

    }
}
