
package edu.wright.hendrix11.familyTree.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Joe Hendrix
 */
public class Person
{
    private Integer id;
    private Person father;
    private Person mother;
    private String name = "";
    private String gender;
    private Date dateOfBirth;
    private String placeOfBirth;
    private Date dateOfDeath;
    private String placeOfDeath;
    
    private HashMap<Person, List<SpouseChildMap>> spouseChildMap;
    
    private List<Person> childrenNoSpouse;

    public Person()
    {
        // No arg constructor
    }
    
    public Person(String name)
    {
        this.name = name;
    }
    
//    public Person(int id, String name)
//    {
//        this(Integer.valueOf(id), name);
//    }
    
    public Person(Integer id, String name)
    {
        this(name);
        
        this.id = id;
    }
    
    public Person(Integer id, Integer fatherId, String father, Integer motherId, String mother, String name, String gender, Date dateOfBirth, String placeOfBirth, Date dateOfDeath, String placeOfDeath)
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

    public Person(Integer id, Person father, Person mother, String name, String gender, Date dateOfBirth, String placeOfBirth, Date dateOfDeath, String placeOfDeath)
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
    
    public boolean hasMother()
    {
        return hasParent(mother);
    }
    
    public boolean hasFather()
    {
        return hasParent(father);
    }
    
    private boolean hasParent(Person parent)
    {
        if(parent == null || !parent.exists())
            return false;
        else
            return true;
    }
    
    public Integer getId()
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

    public HashMap<Person, List<SpouseChildMap>> getSpouseChildMap()
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

//    public void setDateOfBirth(Calendar dateOfBirth)
//    {
//        setDateOfBirth(dateOfBirth.getTime());
//    }

    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth)
    {
        this.placeOfBirth = placeOfBirth;
    }

//    public void setDateOfDeath(Calendar dateOfDeath)
//    {
//        setDateOfDeath(dateOfDeath.getTime());
//    }

    public void setDateOfDeath(Date dateOfDeath)
    {
        this.dateOfDeath = dateOfDeath;
    }

    public void setPlaceOfDeath(String placeOfDeath)
    {
        this.placeOfDeath = placeOfDeath;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public List<Person> getChildrenNoSpouse()
    {
        return childrenNoSpouse;
    }

    public void setSpouseChildMap(HashMap<Person, List<SpouseChildMap>> spouseChildMap)
    {
        List<SpouseChildMap> noSpouseList = spouseChildMap.get(null);
        
        childrenNoSpouse = new ArrayList<Person>();
        
        if(noSpouseList != null)
        {
            for(SpouseChildMap map : noSpouseList)
            {
                childrenNoSpouse.add(map.getChild());
            }
        }
        
        spouseChildMap.remove(null);
            
        this.spouseChildMap = spouseChildMap;
    }
    
    public boolean exists()
    {
        boolean exists = false;
        
        if(id != null)
            exists = true;
        
        if(name != null && !name.isEmpty())
            exists = true;
        
        return exists;
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
        return toString("");
    }
    
    public String toString(String tabs)
    {            
        StringBuilder sb = new StringBuilder();
        
        if(exists())
        {
            sb.append(tabs).append("id:\t").append(id).append("\n");
            sb.append(tabs).append("name:\t").append(name).append("\n");
            sb.append(tabs).append("gender:\t").append(gender).append("\n");

            sb.append(tabs).append("father:\t");

            if(father != null && father.exists())
                sb.append(father.getId()).append(" ").append(father.getName()).append("\n");
            else
                sb.append("(null)").append("\n");

            sb.append(tabs).append("mother:\t");

            if(mother != null && mother.exists())
                sb.append(mother.getId()).append(" ").append(mother.getName());
            else
                sb.append("(null)");
        }
        else
        {
            sb.append(tabs).append("(null)");
        }
        
//        private Date dateOfBirth;
//        private String placeOfBirth;
//        private Date dateOfDeath;
//        private String placeOfDeath;
        
        return sb.toString();
    }
}
