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

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class MarriagePK
{

    private int husband;
    private int wife;

    /**
     * @param husband
     * @param wife
     */
    public MarriagePK(int husband, int wife)
    {
        this.husband = husband;
        this.wife = wife;
    }

    /**
     * @return
     */
    public int getHusband()
    {
        return husband;
    }

    /**
     * @param husband
     */
    public void setHusband(int husband)
    {
        this.husband = husband;
    }

    /**
     *
     * @return
     */
    public int getWife()
    {
        return wife;
    }

    /**
     *
     * @param wife
     */
    public void setWife(int wife)
    {
        this.wife = wife;
    }

    @Override
    public int hashCode()
    {
        return husband + wife;
    }

    @Override
    public boolean equals(Object object)
    {
        if ( object instanceof MarriagePK )
        {
            MarriagePK pk = (MarriagePK) object;
            return husband == pk.getHusband() && wife == pk.getWife();
        }
        else
        {
            return false;
        }
    }
}
