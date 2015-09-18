/*
 *  The MIT License (MIT)
 *
 *  View the full license at:
 *  https://github.com/hendrixjoseph/FamilyTree/blob/master/LICENSE.md
 *
 *  Copyright (c) 2015 Joseph Hendrix
 *
 *  Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
 *
 */
package edu.wright.hendrix11.familyTree.entity;

import edu.wright.hendrix11.familyTree.entity.Marriage.MarriagePK;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * //@author Joe Hendrix <hendrix.11//@wright.edu>
 */
//@Entity
//@IdClass(MarriagePK.class)
public class Marriage implements Serializable
{
    //private static final long serialVersionUID = 1L;

    //@Id
    //@Column(name="HUSBAND")
    private int husbandId;

    //@Id
    //@Column(name="WIFE")
    private int wifeId;

//    //@ManyToOne(fetch=FetchType.LAZY)
//    //@JoinColumn(name="HUSBAND")
    //@Transient
    private PersonView husband;

//    //@ManyToOne(fetch=FetchType.LAZY)
//    //@JoinColumn(name="WIFE")
    //@Transient
    private PersonView wife;

    private String place;

    //@Temporal(TemporalType.DATE)
    private Date anniversary;

    /**
     *
     */
    public Marriage()
    {
        husband = new PersonView();
        wife = new PersonView();
    }

    /**
     *
     * //@return
     */
    public PersonView getHusband()
    {
        return husband;
    }

    /**
     *
     * //@param husband
     */
    public void setHusband(PersonView husband)
    {
        this.husband = husband;
    }

    /**
     *
     * //@return
     */
    public PersonView getWife()
    {
        return wife;
    }

    /**
     *
     * //@param wife
     */
    public void setWife(PersonView wife)
    {
        this.wife = wife;
    }

    /**
     *
     * //@return
     */
    public String getPlace()
    {
        return place;
    }

    /**
     *
     * //@param place
     */
    public void setPlace(String place)
    {
        this.place = place;
    }

    /**
     *
     * //@return
     */
    public Date getAnniversary()
    {
        return anniversary;
    }

    /**
     *
     * //@param anniversary
     */
    public void setAnniversary(Date anniversary)
    {
        this.anniversary = anniversary;
    }

    /**
     *
     * //@param p1 //@param p2
     */
    public void setCouple(PersonView p1, PersonView p2)
    {
        // Supreme court is going to hate this...
        if (p1.getGender().equals(p2.getGender()))
        {
            return;
        }

        if (p1.getGender().equals("Male"))
        {
            husband = p1;
        }
        else if (p1.getGender().equals("Female"))
        {
            wife = p1;
        }

        if (p2.getGender().equals("Male"))
        {
            husband = p2;
        }
        else if (p2.getGender().equals("Female"))
        {
            wife = p2;
        }
    }

    /**
     *
     * //@return
     */
    public boolean isSet()
    {
        return husband.exists() && wife.exists();
    }

    /**
     *
     * //@return
     */
    public boolean hasHusband()
    {
        return hasSpouse(husband);
    }

    /**
     *
     * //@return
     */
    public boolean hasWife()
    {
        return hasSpouse(wife);
    }

    private boolean hasSpouse(PersonView spouse)
    {
        if (spouse == null)
        {
            return false;
        }
        else
        {
            return spouse.exists();
        }
    }

    public int getHusbandId()
    {
        return husbandId;
    }

    public void setHusbandId(int husbandId)
    {
        this.husbandId = husbandId;
    }

    public int getWifeId()
    {
        return wifeId;
    }

    public void setWifeId(int wifeId)
    {
        this.wifeId = wifeId;
    }

    //@Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("Husband: ").append(husband.exists() ? husband.getName() : "(null)").append("\n");
        sb.append("Wife: ").append(wife.exists() ? wife.getName() : "(null)").append("\n");

        return sb.toString();
    }

    public class MarriagePK implements Serializable
    {

        private int husbandId;
        private int wifeId;

        public MarriagePK(int husbandId, int wifeId)
        {
            this.husbandId = husbandId;
            this.wifeId = wifeId;
        }

        public int getHusbandId()
        {
            return husbandId;
        }

        public void setHusbandId(int husbandId)
        {
            this.husbandId = husbandId;
        }

        public int getWifeId()
        {
            return wifeId;
        }

        public void setWifeId(int wifeId)
        {
            this.wifeId = wifeId;
        }

        //@Override
        public boolean equals(Object object)
        {
            if (object instanceof MarriagePK)
            {
                MarriagePK pk = (MarriagePK) object;
                return husbandId == pk.husbandId && wifeId == pk.wifeId;
            }
            else
            {
                return false;
            }
        }

        //@Override
        public int hashCode()
        {
            return husbandId + wifeId;
        }
    }
}
