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

    public Source()
    {
    }

    public Source(String citation)
    {
        this.citation = citation;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCitation()
    {
        return citation;
    }

    public void setCitation(String citation)
    {
        this.citation = citation;
    }
}
