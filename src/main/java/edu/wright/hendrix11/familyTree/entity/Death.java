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

import edu.wright.hendrix11.familyTree.entity.event.Event;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Entity
public class Death implements Event
{
    @Id
    @Column(name = "PERSON_ID")
    private int id;
    
    @OneToOne
    @PrimaryKeyJoinColumn
    private Person person;

    @ManyToOne
    @JoinColumn(name="PLACE_ID")
    private String place;

    @Temporal(TemporalType.DATE)
    private Date date;
    
    @Override
    public int getId()
    {
        return id;
    }

    @Override
    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public Person getPerson()
    {
        return person;
    }

    @Override
    public void setPerson(Person person)
    {
        this.person = person;
    }

    @Override
    public String getPlace()
    {
        return place;
    }

    @Override
    public void setPlace(String place)
    {
        this.place = place;
    }

    @Override
    public Date getDate()
    {
        return date;
    }

    @Override
    public void setDate(Date date)
    {
        this.date = date;
    }

}
