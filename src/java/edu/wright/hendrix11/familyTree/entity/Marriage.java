
package edu.wright.hendrix11.familyTree.entity;

import java.util.Date;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class Marriage
{
    Person husband;
    Person wife;
    String place;
    Date anniversary;
    
    public Marriage()
    {
        husband = new Person();
        wife = new Person();
    }

    public Person getHusband()
    {
        return husband;
    }

    public void setHusband(Person husband)
    {
        this.husband = husband;
    }

    public Person getWife()
    {
        return wife;
    }

    public void setWife(Person wife)
    {
        this.wife = wife;
    }

    public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }

    public Date getAnniversary()
    {
        return anniversary;
    }

    public void setAnniversary(Date anniversary)
    {
        this.anniversary = anniversary;
    }
    
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
    
    public boolean isSet()
    {
        return husband.exists() && wife.exists();
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
