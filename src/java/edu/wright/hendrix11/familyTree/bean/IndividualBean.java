package edu.wright.hendrix11.familyTree.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.annotation.PostConstruct;

import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.database.PersonData;

/**
 *
 * @author Joe Hendrix
 */
@ManagedBean
@RequestScoped
public class IndividualBean 
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
       
    public IndividualBean()
    {
    }
    
    @PostConstruct
    public void initialize()
    {
        person = new PersonData().select(1);
        
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
        person = new PersonData().select(id);
    }
}
