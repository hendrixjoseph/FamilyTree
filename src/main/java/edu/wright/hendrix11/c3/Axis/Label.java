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

package edu.wright.hendrix11.c3.Axis;

/**
 * @author Joe Hendrix
 */
public abstract class Label
{
    private String position;
    private String text;

    public Label(String text)
    {
        this.text = text;
    }

    public Label(String text, LabelPosition position)
    {
        this.text = text;
        this.position = position;
    }
    
    protected setPosition(String position)
    {
        this.position = position;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("{text:'");
        sb.append(text);
        sb.append("'");

        if ( position != null )
        {
            sb.append(",position:'");
            sb.append(position);
            sb.append("'");
        }

        sb.append("}");

        return sb.toString();
    }
}
