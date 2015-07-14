
package edu.wright.hendrix11.familyTree.database;

import edu.wright.hendrix11.familyTree.entity.Person;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class PersonData extends Database<Person>
{
    public PersonData()
    {
        super();
        this.tableName = "PERSON_VIEW";
        setColumnMethodMap("PERSON_VIEW", new Person());
        setOtherMethods();
    }
    
    public PersonData(String propertyFile)
    {
        super("PERSON_VIEW", propertyFile);
        setColumnMethodMap("PERSON_VIEW", new Person());
        setOtherMethods();
    }
    
    private void setOtherMethods()
    {
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
    
    @Override
    public Person select(int id)
    {
        return select(id, true);
    }
    
    public Person select(int id, boolean includeSpouseChildMap)
    {
        Person person = new Person();
        person.setFather(new Person());
        person.setMother(new Person());
        
        try
        {        
            openConnection();
            Statement statement = createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName + " WHERE ID=" + id);
            
            if(rs.next()) 
            {
                this.setFields(person, rs);
            }

            rs.close();
            statement.close();
            closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }            
        
        if(includeSpouseChildMap)
            person.setSpouseChildMap(getSpouseChildMap(id));
                    
        return person;
    }
    
    public List<Person> select()
    {
        List<Person> persons = new ArrayList<Person>();
        
        try
        {        
            openConnection();
            Statement statement = createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName);
            
            while(rs.next()) 
            {
                Person person = new Person();
                this.setFields(person, rs);
                persons.add(person);
            }

            rs.close();
            statement.close();
            closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return persons;
    }
    
    private Integer selectMaxId()
    {
        Integer maxId = null;
        
        String query = "SELECT MAX(ID) AS ID FROM " + tableName;
        
        try
        {        
            openConnection();
            Statement statement = createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            if(rs.next()) 
            {
                maxId = rs.getInt("ID");
            }

            rs.close();
            statement.close();
            closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return maxId;
    }

    @Override
    public boolean update(Person p)
    {
        return false;
    }

    @Override
    public int insert(Person p)
    {
        String query = generateInsertQuery(p);
        
        try
        {        
            openConnection();
            Statement statement = createStatement();
            statement.executeUpdate(query);
            statement.close();
            closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        Integer justInserted = selectMaxId();
        
        if(justInserted == null)
            return 0;
        else
            return justInserted;
    }
    
    public int insert(Person p, int childId)
    {
        return insert(p);
    }

    @Override
    public boolean delete(Person p)
    {
        if(p != null && p.getId() != null)
        {
            String query = "DELETE FROM " + tableName + " WHERE ID=" + p.getId();
            
            try
            {        
                openConnection();
                Statement statement = createStatement();
                statement.executeUpdate(query);
                statement.close();
                closeConnection();
                
                return true;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return false;
    }
    
    private HashMap<Person, ArrayList<Person>> getSpouseChildMap(int id)
    {
        HashMap<Person, ArrayList<Person>> spouseChildTable = new HashMap<Person, ArrayList<Person>>();  
        ArrayList<Person> children;
        
        try
        {
            openConnection();
            Statement statement = createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM CHILDREN_VIEW WHERE ID=" + id);
            
            Person child;
            Person spouse;
            
            while(rs.next())
            {
                spouse = new Person(rs.getInt("SPOUSE_ID"), rs.getString("SPOUSE"));
                child = new Person(rs.getInt("CHILD_ID"), rs.getString("CHILD"));
                
                if(spouseChildTable.get(spouse) == null)
                    spouseChildTable.put(spouse, new ArrayList<Person>());
                
                children = spouseChildTable.get(spouse);
                
                children.add(child);
            }
            
            rs.close();
            statement.close();
            closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return spouseChildTable;
    }
    
}
