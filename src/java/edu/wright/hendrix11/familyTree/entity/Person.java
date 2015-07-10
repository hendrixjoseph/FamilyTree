
package edu.wright.hendrix11.familyTree.entity;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author Joe Hendrix
 */
public class Person
{
    private int id;
    private Person father;
    private Person mother;
    private String name = "";
    private String gender;
    private Date dateOfBirth;
    private String placeOfBirth;
    private Date dateOfDeath;
    private String placeOfDeath;
    
    private HashMap<Person, ArrayList<Person>> spouseChildMap;

    public Person()
    {
        // No arg constructor
        this("");
    }
    
    public Person(String name)
    {
        this.name = name;
    }
    
    public Person(int id, String name)
    {
        this(name);
        
        this.id = id;
    }
    
    public Person(int id, int fatherId, String father, int motherId, String mother, String name, String gender, Date dateOfBirth, String placeOfBirth, Date dateOfDeath, String placeOfDeath)
    {
        this(   id, 
                father!=null?new Person(fatherId, father):null, 
                mother!=null?new Person(motherId, mother):null,
                name, 
                gender,
                dateOfBirth, 
                placeOfBirth, 
                dateOfDeath, 
                placeOfDeath);
    }

    public Person(int id, Person father, Person mother, String name, String gender, Date dateOfBirth, String placeOfBirth, Date dateOfDeath, String placeOfDeath)
    {        
        this.id = id;
        this.father = father;
        this.mother = mother;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.placeOfDeath = placeOfDeath;
    }
    
    public Person(ResultSet rs) throws Exception
    {
        this(   rs.getInt("ID"),
                rs.getInt("FATHER_ID"),
                rs.getString("FATHER"),
                rs.getInt("MOTHER_ID"),
                rs.getString("MOTHER"),
                rs.getString("NAME"),
                rs.getString("GENDER"),
                rs.getDate("DATE_OF_BIRTH"),
                rs.getString("PLACE_OF_BIRTH"),
                rs.getDate("DATE_OF_DEATH"),
                rs.getString("PLACE_OF_DEATH"));
    }
    
    public boolean hasMother()
    {
        return mother != null;
    }
    
    public boolean hasFather()
    {
        return father != null;
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

    public String getGender()
    {
        return gender;
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

    public HashMap<Person, ArrayList<Person>> getSpouseChildMap()
    {
        return spouseChildMap;
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

    public void setGender(String gender)
    {
        this.gender = gender;
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

    public void setSpouseChildMap(HashMap<Person, ArrayList<Person>> spouseChildMap)
    {
        this.spouseChildMap = spouseChildMap;
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
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Person.java:\n");
        
        Field[] fields = this.getClass().getFields();
        
        for(Field field : fields)
        {        
            sb.append(field.getName()).append(":\t").append(field.getType().getName()).append("\n");
        }
        
        return sb.toString();
    }
}