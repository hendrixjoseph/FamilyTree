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
import edu.wright.hendrix11.familyTree.entity.compositeKey.MarriagePK;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Entity
@IdClass(MarriagePK.class)
@NamedQuery(name = Marriage.FIND_ALL, query = "SELECT m FROM Marriage m")
public class Marriage extends Event
{
    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery}
     * to get all marriage records.
     */
    public static final String FIND_ALL = "Marriage.findAll";

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HUSBAND")
    private Person husband;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WIFE")
    private Person wife;

    /**
     * @return
     */
    public Person getHusband()
    {
        return husband;
    }

    /**
     * @param husband
     */
    public void setHusband(Person husband)
    {
        this.husband = husband;
    }

    /**
     * @return
     */
    public Person getWife()
    {
        return wife;
    }

    /**
     * @param wife
     */
    public void setWife(Person wife)
    {
        this.wife = wife;
    }

    /**
     * @return
     */
    public boolean hasHusband()
    {
        return hasSpouse(husband);
    }

    /**
     * @return
     */
    public boolean hasWife()
    {
        return hasSpouse(wife);
    }

    private boolean hasSpouse(Person spouse)
    {
        return spouse != null;
    }

}