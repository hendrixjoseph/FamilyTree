
package edu.wright.hendrix11.familyTree.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class Marriage implements Serializable
{
    Person husband;
    Person wife;
    String place;
    Date anniversary;
    
    /**
     *
     */
    public Marriage()
    {
        husband = new Person();
        wife = new Person();
    }

    /**
     *
     * @return
     */
    public Person getHusband()
    {
        return husband;
    }

    /**
     *
     * @param husband
     */
    public void setHusband(Person husband)
    {
        this.husband = husband;
    }

    /**
     *
     * @return
     */
    public Person getWife()
    {
        return wife;
    }

    /**
     *
     * @param wife
     */
    public void setWife(Person wife)
    {
        this.wife = wife;
    }

    /**
     *
     * @return
     */
    public String getPlace()
    {
        return place;
    }

    /**
     *
     * @param place
     */
    public void setPlace(String place)
    {
        this.place = place;
    }

    /**
     *
     * @return
     */
    public Date getAnniversary()
    {
        return anniversary;
    }

    /**
     *
     * @param anniversary
     */
    public void setAnniversary(Date anniversary)
    {
        this.anniversary = anniversary;
    }
    
    /**
     *
     * @param p1
     * @param p2
     */
    public void setCouple(Person p1, Person p2)
    {
        // Supreme court is going to hate this...
        if(p1.getGender().equals(p2.getGender()))
        {
            return;
        }
        
        if(p1.getGender().equals("Male"))
            husband = p1;
        else if(p1.getGender().equals("Female"))
            wife = p1;
        
        if(p2.getGender().equals("Male"))
            husband = p2;
        else if(p2.getGender().equals("Female"))
            wife = p2;
    }
    
    /**
     *
     * @return
     */
    public boolean isSet()
    {
        return husband.exists() && wife.exists();
    }
    
    /**
     *
     * @return
     */
    public boolean hasHusband()
    {
        return hasSpouse(husband);
    }
    
    /**
     *
     * @return
     */
    public boolean hasWife()
    {
        return hasSpouse(wife);
    }
    
    private boolean hasSpouse(Person spouse)
    {
        if(spouse == null)
            return false;
        else
            return spouse.exists();
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Husband: " ).append(husband.exists() ? husband.getName() : "(null)").append("\n");
        sb.append("Wife: " ).append(wife.exists() ? wife.getName() : "(null)").append("\n");
        
        return sb.toString();
    }
}
