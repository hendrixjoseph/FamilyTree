
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
            ResultSet rs = executeQuery("SELECT * FROM PERSON_VIEW WHERE ID=" + id);
            
            if(rs.next()) 
            {
                person = new Person(rs);
            }

            rs.close();
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
        HashMap<String, Object> columnValueMap = new HashMap<String, Object>();
        
        if(p.getName() == null || p.getName().isEmpty())
            throw new NullPointerException("Person's name is required!");

        if(p.getGender() == null)
            throw new NullPointerException("Person's gender is required! If gender is unknown, set it as \"Unknown\".");
        
        columnValueMap.put("NAME", p.getName());
        columnValueMap.put("GENDER", p.getGender());
        
        if(p.hasFather())
            columnValueMap.put("FATHER_ID", Integer.toString(p.getFather().getId()));
        
        if(p.hasMother())
            columnValueMap.put("MOTHER_ID", Integer.toString(p.getMother().getId())); 
        
        if(p.getPlaceOfBirth() != null)
            columnValueMap.put("PLACE_OF_BIRTH", p.getPlaceOfBirth());
        
        if(p.getDateOfBirth() != null)
            columnValueMap.put("DATE_OF_BIRTH", createToDate(p.getDateOfBirth()));
        
        if(p.getPlaceOfDeath() != null)
            columnValueMap.put("PLACE_OF_DEATH", p.getPlaceOfDeath());
        
        if(p.getDateOfDeath() != null)
            columnValueMap.put("DATE_OF_DEATH", createToDate(p.getDateOfDeath()));
            
        String query = createInsertQuery("PERSON_VIEW", columnValueMap);
        
//        try
//        {
//            openConnection();
//            
//            Statement statement = createStatement();
//            
//            //rs.close();
//            statement.close();
//            
//            closeConnection();
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
        
        return 0;
    }
    
    public int insert(Person p, int childId)
    {
        return insert(p);
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
            ResultSet rs = executeQuery("SELECT * FROM CHILDREN_VIEW WHERE ID=" + id);
            
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
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return spouseChildTable;
    }
    
}
