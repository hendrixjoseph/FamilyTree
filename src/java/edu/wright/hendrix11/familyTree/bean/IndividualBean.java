package edu.wright.hendrix11.familyTree.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.annotation.PostConstruct;

import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.database.PersonData;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Joe Hendrix
 */
@ManagedBean
//@RequestScoped
@ViewScoped
public class IndividualBean 
{
    private Person person; 
       
    public IndividualBean()
    {
    }
    
    @PostConstruct
    public void initialize()
    {
        person = new PersonData().select(1);
    }
    
    public Person getPerson()
    {   
        return person;
    }
    
    public void setPerson(Person person)
    {
        // Nothing yet...
    }

    public int getPersonId()
    {
        return person.getId();
    }

    public void setPersonId(Integer id)
    {
        person = new PersonData().select(id);
    }
    
    public void setPersonId(int id)
    {
        setPersonId(Integer.valueOf(id));
    }
}
