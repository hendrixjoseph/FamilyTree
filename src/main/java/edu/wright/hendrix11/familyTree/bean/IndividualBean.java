package edu.wright.hendrix11.familyTree.bean;


import javax.annotation.PostConstruct;

import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.database.table.PersonTable;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Joe Hendrix
 */
@Named
@SessionScoped
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
        person = personTable.selectDefault();
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
