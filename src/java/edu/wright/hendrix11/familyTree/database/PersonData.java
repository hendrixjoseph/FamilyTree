
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
    public Person update(Person p)
    {
        return null;
    }

    @Override
    public Person insert(Person p)
    {
        return null;
    }

    @Override
    public Person delete(Person p)
    {
        return null;
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
