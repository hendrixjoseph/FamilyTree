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

/**
 * @author Joe Hendrix
 */
public class Ellipse extends RoundShape
{

    /**
     *
     */
    public Ellipse()
    {
        super("ellipse");
    }

    /**
     * @param rx
     */
    public void setXRadius(Number rx)
    {
        putAttribute("rx", rx);
    }

    /**
     * @param ry
     */
    public void setYRadius(Number ry)
    {
        putAttribute("ry", ry);
    }
}
