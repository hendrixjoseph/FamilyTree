/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wright.hendrix11.familyTree.entity;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class Source
{
    int id;
    String citation;

    /**
     *
     */
    public Source()
    {
    }

    /**
     *
     * @param citation
     */
    public Source(String citation)
    {
        this.citation = citation;
    }

    /**
     *
     * @return
     */
    public int getId()
    {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getCitation()
    {
        return citation;
    }

    /**
     *
     * @param citation
     */
    public void setCitation(String citation)
    {
        this.citation = citation;
    }
}
