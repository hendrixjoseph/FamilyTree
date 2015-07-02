package familyTree.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.annotation.PostConstruct;

import familyTree.entity.Person;
import familyTree.database.Database;

/**
 *
 * @author Joe
 */
@ManagedBean
@RequestScoped
public class PersonBean 
{
    private Person person;    
    private Person newPerson;
    private String parent;

    public String getParent()
    {
        return parent;
    }

    public void setParent(String parent)
    {
        this.parent = parent;
    }
       
    public PersonBean()
    {
    }
    
    @PostConstruct
    public void initialize()
    {
        Database.setProperties();
        person = Database.getPerson(1);
        
        newPerson = new Person();
    }
    
    public Person getPerson()
    {   
        return person;
    }
    
    public void setPerson(Person person)
    {
        // Nothing yet...
    }

    public Person getNewPerson()
    {
        return newPerson;
    }

    public void setNewPerson(Person newPerson)
    {
        this.newPerson = newPerson;
    }

    public int getPersonId()
    {
        return person.getId();
    }

    public void setPersonId(int id)
    {
        person = Database.getPerson(id);
    }
}
