/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familyTree.entity;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

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
    
    public Person(String name)
    {
        this.name = name;
    }
    
    public Person(int id, String father, String mother, String name, Date dateOfBirth, String placeOfBirth, Date dateOfDeath, String placeOfDeath)
    {
        this(   id, 
                new Person(father), 
                new Person(mother),
                name, dateOfBirth, 
                placeOfBirth, 
                dateOfDeath, 
                placeOfDeath);
    }

    public Person(int id, Person father, Person mother, String name, Date dateOfBirth, String placeOfBirth, Date dateOfDeath, String placeOfDeath)
    {
        this.id = id;
        this.father = father;
        this.mother = mother;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.placeOfDeath = placeOfDeath;
    }
    
    public Person(ResultSet rs) throws Exception
    {
        this(   rs.getInt("ID"), 
                rs.getString("FATHER"),
                rs.getString("MOTHER"),
                rs.getString("NAME"),
                rs.getDate("DATE_OF_BIRTH"),
                rs.getString("PLACE_OF_BIRTH"),
                rs.getDate("DATE_OF_DEATH"),
                rs.getString("PLACE_OF_DEATH"));
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
    
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Person)
        {
            Person p = (Person)o;
            
            if(this.id != p.id)
                return false;
            
            if(!this.name.equals(p.name))
                return false;
            
            return true;
        }
        else
            return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.name);
        return hash;
    }
}
