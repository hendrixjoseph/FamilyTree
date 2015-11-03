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

package edu.wright.hendrix11.familyTree.entity.compositeKey;

import java.io.Serializable;

/**
 * @author Joe Hendrix
 */
public class SinglePersonEventPK implements Serializable
{
    private int person;
    private String type;

    /**
     * @param person
     * @param type
     */
    public SinglePersonEventPK(int person, String type)
    {
        this.person = person;
        this.type = type;
    }

    /**
     * @return
     */
    public int getPerson()
    {
        return person;
    }

    /**
     * @param person
     */
    public void setPerson(int person)
    {
        this.person = person;
    }

    /**
     * @return
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public int hashCode()
    {
        int result = person;
        result = 31 * result + getType().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        SinglePersonEventPK that = (SinglePersonEventPK) o;

        if ( person != that.getPerson() )
            return false;
        return type.equals(that.getType());

    }
}
