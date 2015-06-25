package familyTree.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.HashMap;

import familyTree.entity.Person;
import familyTree.database.Database;

/**
 *
 * @author Joe
 */
@ManagedBean
@SessionScoped
public class PersonBean 
{
    private Person person;
    private HashMap<Person, ArrayList<Person>> children;
       
    public PersonBean()
    {
    }
    
    @PostConstruct
    public void initialize()
    {
        Database.setProperties();
        person = Database.getPerson(1);
        children = Database.getChildren(1);
    }
    
    public Person getPerson()
    {   
        return person;
    }
    
    public void setPerson(Person person)
    {
        // Nothing yet...
    }
    
    public HashMap<Person, ArrayList<Person>> getChildren()
    {
        return children;
    }
    
    public void setChildren(HashMap<Person, ArrayList<Person>> children)
    {
        // Nothing
    }
}
