
package edu.wright.hendrix11.familyTree.bean.transaction;

import edu.wright.hendrix11.familyTree.database.PersonData;
import edu.wright.hendrix11.familyTree.entity.Person;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@ManagedBean
//@RequestScoped
@ViewScoped
public class InsertPersonBean extends InsertBean<Person>
{   
    
    Person person;
    
    Person spouse;
    
    Person child;
    
    @PostConstruct
    public void initialize()
    {
        person = new Person();
        child = null;
        
        //flip = true;
    }
    
    @Override
    public void commit()
    {
        System.err.println("commit()\n" + this.toString());
//        PersonData personData = new PersonData();
//        personData.insert(person);
    }
    
    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
        //System.err.println("setPerson()\n" + this.toString());
    }

    public void setChild(Person child)
    {
        this.child = child;
        System.err.println("setChild()\n" + this.toString());
    }
    
    public void setSpouse(Person spouse)
    {
        this.spouse = spouse;
        //System.err.println("setSpouse()\n" + this.toString());
    }

    public Person getSpouse()
    {
        return spouse;
    }
    
    private static boolean flip = true;
    
    public void buttonAction(ActionEvent actionEvent)
    {
        flip = !flip;
        System.err.println("Flip is now " + flip);
    }

    public boolean isFlip()
    {
        return flip;
    }

    public void setFlip(boolean flip)
    {
        this.flip = flip;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("\tChild is: ").append(child != null ? child.getName() : "(null)");
        sb.append("\n\tPerson is ").append(person != null ? (person.getName().isEmpty() ? "(null)" : person.getName()) : "(null)");
        sb.append("\n\tSpouse is ").append(spouse != null ? spouse.getName() : "(null)");
        
        return sb.toString();
    }
    
    
}
