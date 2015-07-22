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
public class PersonInfo
{
    private String type;
    private String description;
    
    public PersonInfo()
    {
        type = null;
        description = null;
    }
    
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public boolean isSet()
    {
        return (description != null && type != null);
    }
}
