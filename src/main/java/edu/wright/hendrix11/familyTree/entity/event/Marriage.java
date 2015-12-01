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
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery} to get all
     * marriage records.
     * For example: 
     * <blockquote><pre>{@code TypedQuery<Marriage> query = em.createNamedQuery(Marriage.FIND_ALL, Marriage.class);}</pre></blockquote>
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
     * Returns the male person of the marriage.
     * 
     * @return the male person of the marriage
     */
    public Person getHusband()
    {
        return husband;
    }

    /**
     * Sets the male person of the marriage.
     * 
     * @param husband the male person of the marriage
     */
    public void setHusband(Person husband)
    {
        this.husband = husband;
    }

    /**
     * Returns the female person of the marriage.
     * 
     * @return the female person of the marriage
     */
    public Person getWife()
    {
        return wife;
    }

    /**
     * Sets the female person of the marriage.
     * 
     * @param wife the female person of the marriage
     */
    public void setWife(Person wife)
    {
        this.wife = wife;
    }
}
