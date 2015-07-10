
package edu.wright.hendrix11.familyTree.bean.transaction;

import edu.wright.hendrix11.familyTree.entity.Person;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@ManagedBean
@RequestScoped
public class InsertPersonBean extends InsertBean<Person>
{
    Person person;
    
    @PostConstruct
    public void initialize()
    {
        person = new Person();
    }
    
    @Override
    public void commit()
    {
        
    }
    
    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }
}
