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

package edu.wright.hendrix11.familyTree.entity.event;

import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.entity.compositeKey.SinglePersonEventPK;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Joe Hendrix
 */
@Entity
@Table(name = "EVENT")
@IdClass(SinglePersonEventPK.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
public abstract class SinglePersonEvent extends Event
{

    @Id
    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @Id
    private String type;

    protected SinglePersonEvent()
    {
        this.type = this.getClass().getSimpleName().toLowerCase();
    }

    /**
     * @return
     */
    public Person getPerson()
    {
        return person;
    }

    /**
     * @param person
     */
    public void setPerson(Person person)
    {
        this.person = person;
    }

    public String getType()
    {
        return type;
    }

//    public void setType(String type)
//    {
//        this.type = type;
//    }
}
