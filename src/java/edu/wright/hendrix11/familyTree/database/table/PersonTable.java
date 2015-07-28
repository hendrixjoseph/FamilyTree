
package edu.wright.hendrix11.familyTree.database.table;

import edu.wright.hendrix11.familyTree.database.ColumnMethodMap;
import edu.wright.hendrix11.familyTree.database.DatabaseQuery;
import edu.wright.hendrix11.familyTree.database.interfaces.*;
import edu.wright.hendrix11.familyTree.entity.Marriage;
import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.entity.SpouseChildMap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class PersonTable extends DatabaseQuery implements SelectData<Person, Integer>, 
                                                    SelectAllData<Person>,
                                                    InsertData<Person>, 
                                                    UpdateData<Person>, 
                                                    DeleteData<Person>
{

    /**
     *
     */
    public PersonTable()
    {
        super("PERSON_VIEW", Person.class);
 
        ColumnMethodMap columnMethodMap = this.getColumnMethodMap();
        
        columnMethodMap.putGetter("FATHER_ID","getFather().getId()");
        columnMethodMap.putSetter("FATHER_ID","getFather().setId()");
        
        columnMethodMap.putGetter("FATHER_NAME","getFather().getName()");
        columnMethodMap.putSetter("FATHER_NAME","getFather().setName()");
        
        columnMethodMap.putGetter("MOTHER_ID","getMother().getId()");
        columnMethodMap.putSetter("MOTHER_ID","getMother().setId()");
        
        columnMethodMap.putGetter("MOTHER_NAME","getMother().getName()");
        columnMethodMap.putSetter("MOTHER_NAME","getMother().setName()");
    }
    
    /**
     *
     * @param id
     * @return
     */
    @Override
    public Person select(Integer id)
    {
        return select(id, true);
    }
    
    /**
     *
     * @param id
     * @param includeSpouseChildMap
     * @return
     */
    public Person select(Integer id, boolean includeSpouseChildMap)
    {
        Person person = new Person();
        person.setFather(new Person());
        person.setMother(new Person());
        
        try
        {
            ResultSet rs = selectWithKey(id);
            
            if(rs.next()) 
            {
                this.setFields(person, rs);
            }

            this.closeStatement(rs);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }            
        
        if(includeSpouseChildMap)
        {
            setSpouseChildMap(person, id);
        }
                    
        return person;
    }
    
    private void setSpouseChildMap(Person person, Integer id)
    {
        SpouseChildTable mapData = new SpouseChildTable();
            
        HashMap<Person, List<Person>> map = mapData.select(id);

        addSpousesWithNoChildren(map, id);           

        addChildData(map);

        person.setSpouseChildMap(map);   
    }
    
    public Person selectDefault()
    {
        Person person = new Person();
        person.setFather(new Person());
        person.setMother(new Person());
        
        try
        {
            ResultSet rs = this.executeQuery("SELECT * FROM DEFAULT_PERSON_VIEW");
            
            if(rs.next()) 
            {
                this.setFields(person, rs);
            }

            this.closeStatement(rs);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        int id = person.getId();
        
        SpouseChildTable mapTable = new SpouseChildTable();

        HashMap<Person, List<Person>> map = mapTable.select(id);

        addSpousesWithNoChildren(map, id);           

        addChildData(map);

        person.setSpouseChildMap(map);
                    
        return person;
    }
    
    private void addChildData(HashMap<Person, List<Person>> map)
    {
        for(Person spouse : map.keySet())
        {
            List<Person> childList = map.get(spouse);
            
            List<Person> newList = new ArrayList<Person>();
            
            for(Person child : childList)
            { 
                newList.add(select(child.getId(), false));
            }
            
            childList.removeAll(childList);
            
            childList.addAll(newList);
        }
    }
    
    private void addSpousesWithNoChildren(HashMap<Person, List<Person>> map, Integer id)
    {
        MarriageTable marriageTable = new MarriageTable();
        
        List<Marriage> marriages = marriageTable.select(id);

        for(Marriage marriage : marriages)
        {
            Person spouse = new Person();

            if(marriage.getHusband().getId().equals(id))
            {
                spouse = marriage.getWife();
            }
            else if(marriage.getWife().getId().equals(id))
            {
                spouse = marriage.getHusband();
            }

            if(map.get(spouse) == null)
            {
                map.put(spouse, new ArrayList<Person>());
            }
        }
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<Person> selectAll()
    {
        List<Object> objects = super.selectAllObjects();
        
        List<Person> persons = new ArrayList<Person>();
        
        for(Object object : objects)
        {
            if(object instanceof Person)
            {
                persons.add((Person)object);
            }
        }
        
        return persons;
    }
    
    @Override
    protected Object getNew()
    {
        return new Person();
    }
    
    private Person selectLastInserted()
    {
        Person person = null;
        
        try
        {
            ResultSet rs = executeQuery("SELECT * FROM LAST_PERSON_INSERTED_VIEW");
            
            person = new Person();
            
            if(rs.next()) 
            {
                this.setFields(person, rs);
            }

            closeStatement(rs);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return person;
    }

    /**
     *
     * @param p
     * @return
     */
    @Override
    public Person update(Person p)
    {
        Object o = updateObject(p);
        
        if(o instanceof Person)
            return (Person)o;
        else
            return null;
    }

    /**
     *
     * @param p
     * @return
     */
    @Override
    public Person insert(Person p)
    {
        String query = generateInsertQuery(p);     
              
        try
        {
            executeUpdate(query);
            return selectLastInserted();
        }
        catch (SQLException ex)
        {
            System.err.println(query);
            ex.printStackTrace();
        }
        
        return null;
    }
    
    /**
     *
     * @param p
     * @param childId
     * @return
     */
    public Person insert(Person p, int childId)
    {
        return insert(p);
    }

    /**
     *
     * @param p
     * @return
     */
    @Override
    public boolean delete(Person p)
    {
        return super.deleteObject(p);
    }    
}
