
package edu.wright.hendrix11.familyTree.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Joe Hendrix
 */
public class Person implements Serializable
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
    
    private HashMap<Person, List<Person>> spouseChildMap;
    
    private List<Person> childrenNoSpouse;
    
    private List<Person> spouses;
    
    private HashMap<String, PersonInfo> personInfo;

    /**
     *
     */
    public Person()
    {
        // No arg constructor
        childrenNoSpouse = new ArrayList<Person>();
        spouseChildMap = new HashMap<Person, List<Person>>();
        //spouseChildMap = new HashMap<Person, List<SpouseChildMap>>();
    }
    
    /**
     *
     * @param name
     */
    public Person(String name)
    {
        this.name = name;
    }
    
//    public Person(int id, String name)
//    {
//        this(Integer.valueOf(id), name);
//    }
    
    /**
     *
     * @param id
     * @param name
     */
        
    public Person(Integer id, String name)
    {
        this(name);
        
        this.id = id;
    }
    
    /**
     *
     * @return
     */
    public boolean hasMother()
    {
        return hasParent(mother);
    }
    
    /**
     *
     * @return
     */
    public boolean hasFather()
    {
        return hasParent(father);
    }
    
    /**
     *
     * @return
     */
    public boolean hasParent()
    {
        return hasFather() || hasMother();
    }
    
    /**
     *
     * @return
     */
    public boolean hasParents()
    {
        return hasFather() && hasMother();
    }
    
    private boolean hasParent(Person parent)
    {
        if(parent == null || !parent.exists())
            return false;
        else
            return true;
    }
    
    /**
     *
     * @return
     */
    public Integer getId()
    {
        return id;
    }

    /**
     *
     * @return
     */
    public Person getFather()
    {
        return father;
    }

    /**
     *
     * @return
     */
    public Person getMother()
    {
        return mother;
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
     * @return
     */
    public String getGender()
    {
        return gender;
    }

    /**
     *
     * @return
     */
    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     *
     * @return
     */
    public String getPlaceOfBirth()
    {
        return placeOfBirth;
    }

    /**
     *
     * @return
     */
    public Date getDateOfDeath()
    {
        return dateOfDeath;
    }

    /**
     *
     * @return
     */
    public String getPlaceOfDeath()
    {
        return placeOfDeath;
    }

    /**
     *
     * @return
     */
    public HashMap<Person, List<Person>> getSpouseChildMap()
    {
        return spouseChildMap;
    }
    
    /**
     *
     * @return
     */
    public HashMap<Person, List<SpouseChildMap>> getSpouseChildMapOld()
    {
        return null;
    }
    
    /**
     *
     * @param spouse
     * @return
     */
    public List<Person> getChildren(Person spouse)
    {
        return getSpouseChildMap().get(spouse);
    }

    /**
     *
     * @param father
     */
    public void setFather(Person father)
    {
        this.father = father;
    }

    /**
     *
     * @param mother
     */
    public void setMother(Person mother)
    {
        this.mother = mother;
    }

    /**
     *
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     *
     * @param gender
     */
    public void setGender(String gender)
    {
        this.gender = gender;
    }

//    public void setDateOfBirth(Calendar dateOfBirth)
//    {
//        setDateOfBirth(dateOfBirth.getTime());
//    }

    /**
     *
     * @param dateOfBirth
     */
    
    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     *
     * @param placeOfBirth
     */
    public void setPlaceOfBirth(String placeOfBirth)
    {
        this.placeOfBirth = placeOfBirth;
    }

//    public void setDateOfDeath(Calendar dateOfDeath)
//    {
//        setDateOfDeath(dateOfDeath.getTime());
//    }

    /**
     *
     * @param dateOfDeath
     */
    
    public void setDateOfDeath(Date dateOfDeath)
    {
        this.dateOfDeath = dateOfDeath;
    }

    /**
     *
     * @param placeOfDeath
     */
    public void setPlaceOfDeath(String placeOfDeath)
    {
        this.placeOfDeath = placeOfDeath;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public List<Person> getChildrenNoSpouse()
    {
        return childrenNoSpouse;
    }

    /**
     *
     * @param spouseChildMap
     */
    public void setSpouseChildMap(HashMap<Person, List<Person>> spouseChildMap)
    {
        List<Person> noSpouseList = spouseChildMap.get(null);
        
        childrenNoSpouse = new ArrayList<Person>();
        
        if(noSpouseList != null)
        {
            childrenNoSpouse.addAll(noSpouseList);
            
//            for(SpouseChildMap map : noSpouseList)
//            {
//                childrenNoSpouse.add(map.getChild());
//            }
        }
        
        spouseChildMap.remove(null);
        
//        spouses = new ArrayList<Person>();
        
//        Collection<List<SpouseChildMap>> values = spouseChildMap.values();
//        Set<Person> keySet = spouseChildMap.keySet();
//        
//        for(Person spouse : )
//        {
//            
//        }
            
        this.spouseChildMap = spouseChildMap;
    }
    
    /**
     *
     * @return
     */
    public boolean exists()
    {
        boolean exists = false;
        
        if(id != null)
            exists = true;
        
        if(name != null && !name.isEmpty())
            exists = true;
        
        return exists;
    }

    /**
     *
     * @return
     */
    public List<Person> getSpouses()
    {
        return spouses;
    }

    /**
     *
     * @return
     */
    public HashMap<String, PersonInfo> getPersonInfo()
    {
        return personInfo;
    }

    /**
     *
     * @param personInfo
     */
    public void setPersonInfo(HashMap<String, PersonInfo> personInfo)
    {
        this.personInfo = personInfo;
    }
    
    /**
     *
     * @param child
     * @param spouse
     */
    public void addChild(Person child, Person spouse)
    {
        if(spouse == null)
            this.childrenNoSpouse.add(child);
        else
        {
            List<Person> map = this.spouseChildMap.get(spouse);
            
            if(map == null)
            {
                map = new ArrayList<Person>();
            }
            
//            SpouseChildMap spouseChildMap = new SpouseChildMap();
//            spouseChildMap.setPeople(this, spouse, child);
            
            map.add(child);
            
            this.spouseChildMap.put(spouse, map);
        }
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Person)
        {
            Person p = (Person)o;
            
            if(p.id != null && this.id != null)
            {
                return Objects.equals(this.id, p.id);
            }
            
            return p.getName().equals(this.getName());
        }
        else
            return false;
    }

    @Override
    public int hashCode()
    {
        if(id == null)
            return 0;
        else
            return id;
    }
    
    @Override
    public String toString()
    {
        return toString("");
    }
    
    /**
     *
     * @param tabs
     * @return
     */
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

    /**
     *
     * @param personInfo
     */
    public void addInfo(PersonInfo personInfo)
    {
        if(this.personInfo == null)
            this.personInfo = new HashMap<String, PersonInfo>();
        
        this.personInfo.put(personInfo.getType(), personInfo);
    }
}
