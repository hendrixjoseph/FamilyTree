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

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 * @author Joe Hendrix
 */
@MappedSuperclass
public class SinglePersonEvent extends Event
{

    @Id
    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

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
}
