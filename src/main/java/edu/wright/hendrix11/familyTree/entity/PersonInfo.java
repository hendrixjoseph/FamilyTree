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
public class PersonInfo
{

    private String type;
    private String description;

    /**
     *
     */
    public PersonInfo()
    {
        type = null;
        description = null;
    }

    /**
     *
     * @return
     */
    public String getType()
    {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public String getDescription()
    {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public boolean isSet()
    {
        return (description != null && type != null);
    }
}
