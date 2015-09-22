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
    private int husbandId;
    private int wifeId;

    public MarriagePK(int husbandId, int wifeId, final Marriage outer)
    {
        this.husbandId = husbandId;
        this.wifeId = wifeId;
    }

    public int getHusbandId()
    {
        return husbandId;
    }

    public void setHusbandId(int husbandId)
    {
        this.husbandId = husbandId;
    }

    public int getWifeId()
    {
        return wifeId;
    }

    public void setWifeId(int wifeId)
    {
        this.wifeId = wifeId;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof MarriagePK)
        {
            MarriagePK pk = (MarriagePK) object;
            return husbandId == pk.husbandId && wifeId == pk.wifeId;
        }
        else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return husbandId + wifeId;
    }

}
