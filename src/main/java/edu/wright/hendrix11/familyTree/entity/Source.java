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
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class Source
{

    private int id;
    private String citation;

    /**
     *
     */
    public Source()
    {
    }

    /**
     * @param citation
     */
    public Source(String citation)
    {
        this.citation = citation;
    }

    /**
     * @return
     */
    public String getCitation()
    {
        return citation;
    }

    /**
     * @param citation
     */
    public void setCitation(String citation)
    {
        this.citation = citation;
    }

    /**
     * @return
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }
}
