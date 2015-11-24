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

package edu.wright.hendrix11.c3.axis;

/**
 * @author Joe Hendrix
 */
public abstract class Label
{
    private String position;
    private String text;

    /**
     *
     * @param text
     */
    public Label(String text)
    {
        this.text = text;
    }

    /**
     *
     * @param text
     * @param position
     */
    public Label(String text, String position)
    {
        this.text = text;
        this.position = position;
    }

    /**
     *
     * @param position
     */
    protected void setPosition(String position)
    {
        this.position = position;
    }
    
    public String getText()
    {
        return text;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
    public boolean hasText()
    {
        return text != null && !text.isEmpty();
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
