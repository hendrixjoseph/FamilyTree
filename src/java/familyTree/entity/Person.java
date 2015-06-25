/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familyTree.entity;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Joe
 */
public class Person
{
    private int id;
    private Person father;
    private Person mother;
    private String name;
    private Date dateOfBirth;
    private String placeOfBirth;
    private Date dateOfDeath;
    private String placeOfDeath;

    public Person()
    {
        // No arg constructor
    }
    
    public Person(ResultSet rs)
    {
        try
        {
            id = rs.getInt("ID");
            father = new Person();
            father.setName(rs.getString("FATHER_NAME"));
            mother = new Person();
            mother.setName(rs.getString("MOTHER_NAME"));
            name = rs.getString("NAME");
            placeOfBirth = rs.getString("PLACE_OF_BIRTH");
            dateOfBirth = rs.getDate("DATE_OF_BIRTH");
            placeOfDeath = rs.getString("PLACE_OF_DEATH");
            dateOfDeath = rs.getDate("DATE_OF_DEATH");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public int getId()
    {
        return id;
    }

    public Person getFather()
    {
        return father;
    }

    public Person getMother()
    {
        return mother;
    }

    public String getName()
    {
        return name;
    }

    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    public String getPlaceOfBirth()
    {
        return placeOfBirth;
    }

    public Date getDateOfDeath()
    {
        return dateOfDeath;
    }

    public String getPlaceOfDeath()
    {
        return placeOfDeath;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setFather(Person father)
    {
        this.father = father;
    }

    public void setMother(Person mother)
    {
        this.mother = mother;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDateOfBirth(Calendar dateOfBirth)
    {
        setDateOfBirth(dateOfBirth.getTime());
    }

    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth)
    {
        this.placeOfBirth = placeOfBirth;
    }

    public void setDateOfDeath(Calendar dateOfDeath)
    {
        setDateOfDeath(dateOfDeath.getTime());
    }

    public void setDateOfDeath(Date dateOfDeath)
    {
        this.dateOfDeath = dateOfDeath;
    }

    public void setPlaceOfDeath(String placeOfDeath)
    {
        this.placeOfDeath = placeOfDeath;
    }
}
