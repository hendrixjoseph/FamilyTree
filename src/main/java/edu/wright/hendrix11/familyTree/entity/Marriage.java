/* 
 *  The MIT License (MIT)
 *  
 *  Copyright (c) 2015 Joseph Hendrix
 *  
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *  
 *  Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
 *  
 */
package edu.wright.hendrix11.familyTree.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Entity
@IdClass(MarriagePK.class)
public class Marriage implements Serializable
{
    @Id
    private int husbandId;
    
    @Id
    private int wifeId;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="HUSBAND")
    private Person husband;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="WIFE")
    private Person wife;
    
    private String place;
    
    @Temporal(TemporalType.DATE)
    private Date anniversary;
    
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
    
    public class MarriagePK
    {
        private int husbandId;
        private int wifeId;
        
        public MarriagePK(int husbandId, int wifeId)
        {
            this.husbandId = husbandId;
            this.wifeId = wifeId;
        }

        public boolean equals(Object object)
        {
            if (object instanceof MarriagePK)
            {
                MarriagePK pk = (MarriagePK)object;
                return husbandId == pk.husbandId && wifeId == pk.wifeId;
            } 
            else 
            {
                return false;
            }
        }

        public int hashCode() 
        {
            return husbandId + wifeId;
        }
    }
}
