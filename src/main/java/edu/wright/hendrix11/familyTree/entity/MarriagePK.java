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
package edu.wright.hendrix11.familyTree.entity;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class MarriagePK
{
    private int husband;
    private int wife;

    public MarriagePK(int husband, int wife)
    {
        this.husband = husband;
        this.wife = wife;
    }

    public int getHusband()
    {
        return husband;
    }

    public void setHusband(int husband)
    {
        this.husband = husband;
    }

    public int getWifeId()
    {
        return wife;
    }

    public void setWifeId(int wifeId)
    {
        this.wife = wifeId;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof MarriagePK)
        {
            MarriagePK pk = (MarriagePK) object;
            return husband == pk.husband && wife == pk.wife;
        }
        else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return husband + wife;
    }

}
