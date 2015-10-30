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

public enum Gender
{
    MALE,
    FEMALE,
    OTHER,
    UNNOWN;

    public static Gender getEnum(String name)
    {
        name = name.toUpperCase();

        try
        {
            return Gender.valueOf(name);
        }
        catch(IllegalArgumentException e)
        {
            for(Gender gender : Gender.values())
            {
                if(gender.name().startsWith(name))
                    return gender;
            }

            throw e;
        }
    }

    @Override
    public String toString()
    {
        return super.toString().toLowerCase();
    }
}
