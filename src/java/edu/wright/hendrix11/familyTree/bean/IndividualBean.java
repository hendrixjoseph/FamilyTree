package edu.wright.hendrix11.familyTree.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.annotation.PostConstruct;

import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.database.PersonData;
import java.io.Serializable;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Joe Hendrix
 */
@ManagedBean
//@RequestScoped
@ViewScoped
public class IndividualBean implements Serializable
{
    private Person person; 
    
    private PersonData personData;
       
    public IndividualBean()
    {
    }
    
    @PostConstruct
    public void initialize()
    {
        personData = new PersonData();
        person = personData.select(1);
    }
    
    public Person getPerson()
    {   
        return person;
    }

    public Integer getPersonId()
    {
        return person.getId();
    }

    public void setPersonId(Integer id)
    {
        System.err.println("public void setPersonId(Integer " + id + ")");
        person = personData.select(id);
    }
    
    public void setPersonId(int id)
    {
        System.err.println("public void setPersonId(int " + id + ")");
        person = personData.select(id);
    }
}
