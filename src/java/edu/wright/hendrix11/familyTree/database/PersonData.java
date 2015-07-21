
package edu.wright.hendrix11.familyTree.database;

import edu.wright.hendrix11.familyTree.database.interfaces.*;
import edu.wright.hendrix11.familyTree.entity.Marriage;
import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.entity.SpouseChildMap;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class PersonData extends Database implements SelectData<Person, Integer>, 
                                                    SelectAllData<Person>,
                                                    InsertData<Person>, 
                                                    UpdateData<Person>, 
                                                    DeleteData<Person>
{

    /**
     *
     */
    public PersonData()
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
        
        columnMethodMap.setPrimaryKey("ID");
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

            closeConnection(rs);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }            
        
        if(includeSpouseChildMap)
        {
            SpouseChildData mapData = new SpouseChildData();
            
            HashMap<Person, List<SpouseChildMap>> map = mapData.select(id);
            
            MarriageData marriageData = new MarriageData();
            
            List<Marriage> marriages = marriageData.select(id);
            
            for(Marriage marriage : marriages)
            {
                List<SpouseChildMap> spouseList = new ArrayList<SpouseChildMap>();
                SpouseChildMap spouseMap = new SpouseChildMap();
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
                    spouseList.add(spouseMap);
                    map.put(spouse, spouseList);
                }
            }
            
            person.setSpouseChildMap(map);
        }
                    
        return person;
    }
    
    /**
     *
     * @return
     */
    public List<Person> selectAll()
    {
        List<Person> persons = new ArrayList<Person>();
        
        try
        {        
            ResultSet rs = select("SELECT * FROM " + tableName);
            
            while(rs.next()) 
            {
                Person person = new Person();
                this.setFields(person, rs);
                persons.add(person);
            }
            
            closeConnection(rs);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return persons;
    }
    
    private Person selectLastInserted()
    {
        Person person = null;
        
        try
        {
            ResultSet rs = select("SELECT * FROM LAST_PERSON_INSERTED_VIEW");
            
            person = new Person();
            
            if(rs.next()) 
            {
                this.setFields(person, rs);
            }

            closeConnection(rs);
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
        String query = generateUpdateQuery(p);
        
        try
        {
            int id = p.getId();
            
            executeUpdate(query);
            
            return select(id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
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
        catch(Exception e)
        {
            e.printStackTrace();
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
        if(p != null && p.getId() != null)
        {
            String query = "DELETE FROM " + tableName + " WHERE ID=" + p.getId();
            
            try
            {        
                executeUpdate(query);
                
                return true;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return false;
    }    
}
