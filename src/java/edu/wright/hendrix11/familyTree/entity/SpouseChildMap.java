
package edu.wright.hendrix11.familyTree.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class SpouseChildMap
{
    Person person;
    Person spouse;
    Person child;
    
    public SpouseChildMap()
    {
        person = new Person();
        spouse = null;
        child = null;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public Person getSpouse()
    {
        if(spouse == null)
            spouse = new Person();
        
        return spouse;
    }

    public void setSpouse(Person spouse)
    {
        this.spouse = spouse;
    }

    public Person getChild()
    {
        if(child == null)
            child = new Person();
            
        return child;
    }

    public void setChild(Person child)
    {
        this.child = child;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("person:\n").append(person.toString("\t"));
        
        sb.append("\nspouse:\n").append(spouse == null ? "\t(null)" : spouse.toString("\t"));
        
        sb.append("\nchild:\n").append(child == null ? "\t(null)" : child.toString("\t"));
        
        
        return sb.toString();
    }
}
