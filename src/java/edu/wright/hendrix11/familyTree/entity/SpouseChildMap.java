
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
    List<Person> children;
    
    public SpouseChildMap()
    {
        person = new Person();
        spouse = null;
        children = new ArrayList<Person>();
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
        return spouse;
    }

    public void setSpouse(Person spouse)
    {
        this.spouse = spouse;
    }

    public List<Person> getChildren()
    {
        return children;
    }

    public void setChildren(List<Person> children)
    {
        this.children = children;
    }
}
