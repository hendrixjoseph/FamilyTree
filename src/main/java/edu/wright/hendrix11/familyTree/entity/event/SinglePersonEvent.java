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
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

/**
 * @author Joe Hendrix
 */
@Entity
@Table(name = "EVENT")
@IdClass(SinglePersonEventPK.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@NamedQuery(name = SinglePersonEvent.FIND_ALL, query = "SELECT e FROM SinglePersonEvent e")
public abstract class SinglePersonEvent extends Event
{
    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery} to get all
     * single-person events.
     * <p>
     * For example:
     * <blockquote><pre>{@code TypedQuery<SinglePersonEvent> query = em.createNamedQuery(SinglePersonEvent.FIND_ALL,
     * SinglePersonEvent.class);}</pre></blockquote>
     */
    public static final String FIND_ALL = "SinglePersonEvent.findAll";

    @Id
    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @Id
    private String type;

    /**
     * No-arg constructor for JPA.
     */
    protected SinglePersonEvent()
    {
        this.type = this.getClass().getSimpleName().toLowerCase();
    }

    /**
     * Returns the person associated with this event.
     *
     * @return the person associated with this event
     */
    public Person getPerson()
    {
        return person;
    }

    /**
     * Sets the person associated with this event.
     *
     * @param person the person associated with this event
     */
    public void setPerson(Person person)
    {
        this.person = person;
    }

    /**
     * Returns the discriminator column value. Normally this isn't needed with JPA, but the discriminator column is part
     * of the primary key for this entity.
     *
     * @return the discriminator column value
     */
    public String getType()
    {
        return type;
    }

    @Override
    public boolean equals(Object o)
    {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        SinglePersonEvent that = (SinglePersonEvent) o;

        if ( !person.equals(that.person) )
            return false;
        return type.equals(that.type);

    }

    @Override
    public int hashCode()
    {
        int result = person.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
