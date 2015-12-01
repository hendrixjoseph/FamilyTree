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
 * @author Joe
 */
public enum Gender
{

    /**
     *
     */
    MALE,

    /**
     *
     */
    FEMALE,

    /**
     *
     */
    OTHER,

    /**
     *
     */
    UNNOWN;

    /**
     * Returns the Gender that starts with the parameter.
     * 
     * @param startsWith the String that the Gender should start with
     *
     * @return a Gender
     * 
     * @throws IllegalArgumentException If no Gender starts with the parameter
     */
    public static Gender getEnum(String startsWith)
    {
        startsWith = startsWith.toUpperCase();

        try
        {
            return Gender.valueOf(startsWith);
        }
        catch ( IllegalArgumentException e )
        {
            for ( Gender gender : Gender.values() )
            {
                if ( gender.name().startsWith(startsWith) )
                    return gender;
            }

            throw e;
        }
    }

    /**
     * Returns the gender name, in lower case.
     * 
     * @return the gender name, in lower case
     */
    @Override
    public String toString()
    {
        return super.toString().toLowerCase();
    }
}
