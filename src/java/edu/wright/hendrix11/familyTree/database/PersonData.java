
package edu.wright.hendrix11.familyTree.database;

import edu.wright.hendrix11.familyTree.entity.Person;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class PersonData extends Database<Person>
{
    @Override
    public Person select(int id)
    {
        return select(id, true);
    }
    
    public Person select(int id, boolean includeSpouseChildMap)
    {
        Person person = null;
        
        try
        {
            openConnection();

            // Convert to try-with-resources
            // try (Statement statement = con.createStatement())
            // But I need the openConnection() to be in the try-catch block too!
            Statement statement = createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM PERSON_VIEW WHERE ID=" + id);
            
            if(rs.next()) 
            {
                person = new Person(rs);
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

    @Override
    public boolean update(Person p)
    {
        return false;
    }

    @Override
    public int insert(Person p)
    {
        StringBuilder sb = new StringBuilder();
        
        HashMap<String, Object> columnValueMap = new HashMap<String, Object>();
        
        if(p.getName() != null && p.getName().length() > 0)
            columnValueMap.put("NAME", p.getName());
        
        if(p.hasFather())
            columnValueMap.put("FATHER_ID", Integer.toString(p.getFather().getId()));
        
        if(p.hasMother())
            columnValueMap.put("MOTHER_ID", Integer.toString(p.getMother().getId()));
        
        if(p.getGender() != null)
            columnValueMap.put("GENDER", p.getGender());
        
        if(p.getPlaceOfBirth() != null)
            columnValueMap.put("PLACE_OF_BIRTH", p.getPlaceOfBirth());
        
        //if(p.getDateOfBirth() != null)
            
        
        try
        {
            openConnection();
            
            Statement statement = createStatement();
            
            //rs.close();
            statement.close();
            
            closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return 0;
    }

    @Override
    public boolean delete(Person p)
    {
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
