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
    public String getName()
    {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    } 
}
