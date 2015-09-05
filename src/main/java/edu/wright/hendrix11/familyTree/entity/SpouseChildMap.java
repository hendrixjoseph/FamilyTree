
package edu.wright.hendrix11.familyTree.entity;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class SpouseChildMap
{
    Person person;
    Person spouse;
    Person child;
    
    /**
     *
     */
    public SpouseChildMap()
    {
        person = new Person();
        spouse = null;
        child = null;
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
     * @param person
     */
    public void setPerson(Person person)
    {
        this.person = person;
    }

    /**
     *
     * @return
     */
    public Person getSpouse()
    {
        if(spouse == null)
            spouse = new Person();
        
        return spouse;
    }

    /**
     *
     * @param spouse
     */
    public void setSpouse(Person spouse)
    {
        this.spouse = spouse;
    }

    /**
     *
     * @return
     */
    public Person getChild()
    {
        if(child == null)
            child = new Person();
            
        return child;
    }

    /**
     *
     * @param child
     */
    public void setChild(Person child)
    {
        this.child = child;
    }
    
    /**
     *
     * @param person
     * @param spouse
     * @param child
     */
    public void setPeople(Person person, Person spouse, Person child)
    {
        this.person = person;
        this.spouse = spouse;
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
