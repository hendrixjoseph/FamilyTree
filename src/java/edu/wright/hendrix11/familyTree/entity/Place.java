package edu.wright.hendrix11.familyTree.entity;

import java.io.Serializable;

/**
*
* @author Joe Hendrix
*/
public class Place implements Serializable
{
    private int id;
    private String name;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    } 
}
