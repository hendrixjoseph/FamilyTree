package edu.wright.hendrix11.familyTree.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.annotation.PostConstruct;

import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.database.table.PersonTable;
import java.io.Serializable;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Joe Hendrix
 */
@ManagedBean
@ViewScoped
public class IndividualBean implements Serializable
{
    private Person person; 
    
    private PersonTable personTable;
       
    /**
     *
     */
    public IndividualBean()
    {
    }
    
    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        personTable = new PersonTable();
        person = personTable.select(1);
    }
    
    /**
     *
     * @return
     */
    public Person getPerson()
    {   
        return person;
    }

    /**
     *
     * @return
     */
    public Integer getPersonId()
    {
        return person.getId();
    }

    /**
     *
     * @param id
     */
    public void setPersonId(Integer id)
    {
        person = personTable.select(id);
    }
    
    /**
     *
     * @param id
     */
    public void setPersonId(int id)
    {
        person = personTable.select(id);
    }
}
