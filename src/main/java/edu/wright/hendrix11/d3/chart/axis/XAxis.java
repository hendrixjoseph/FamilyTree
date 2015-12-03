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

package edu.wright.hendrix11.d3.chart.axis;

/**
 * @author Joe Hendrix
 */
public class XAxis extends Axis
{
    private boolean localTime;

    /**
     *
     */
    public XAxis()
    {
        super("x");
    }

    /**
     * @param label
     */
    public XAxis(String label)
    {
        this();
        setLabel(new XLabel(label));
    }

    /**
     * @return
     */
    @Override
    public XLabel getLabel()
    {
        return (XLabel) super.getLabel();
    }

    /**
     * @param label
     */
    public void setLabel(XLabel label)
    {
        super.setLabel(label);
    }
    
    /**
     * @param label
     */
    public void setLabel(String label)
    {
        if(getLabel() != null)
        {
            super.setLabel(label);
        }
        else
        {
            super.setLabel(new XLabel(label));
        }
    }
}
