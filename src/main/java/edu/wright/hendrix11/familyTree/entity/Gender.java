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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 * @author Joe Hendrix
 */
@Entity
@NamedQuery(name = Gender.FIND_ALL, query = "SELECT g FROM Gender g")
public class Gender
{
    /**
     * Specifies the {@link String} that represents the {@link javax.persistence.NamedQuery} to create a {@link
     * javax.persistence.TypedQuery} to get all genders.
     */
    public static final String FIND_ALL = "Gender.findAll";

    @Id
    private char abbr;

    @Column(name = "FULL_WORD")
    @NotNull
    private String fullWord;

    /**
     * Public no-argument constructor for JPA.
     */
    public Gender()
    {
        super();
    }

    /**
     * @param abbr the abbreviation for the gender
     */
    public Gender(char abbr)
    {
        this.abbr = abbr;
    }

    /**
     * Returns the abbreviation for the gender. This is typically just the first letter.
     * 
     * @return the abbreviation for the gender
     * 
     * @see #getFullWord()
     */
    public char getAbbr()
    {
        return abbr;
    }

    /**
     * Sets the abbreviation for the gender. This is typically just the first letter.
     * 
     * @param abbr the abbreviation for the gender
     */
    public void setAbbr(char abbr)
    {
        this.abbr = abbr;
    }

    /**
     * Returns the entire word for the gender. Available values include Male, Female, Other, and Unknown.
     * 
     * @return the entire word for the gender
     */
    public String getFullWord()
    {
        return fullWord;
    }

    /**
     * Sets the entire word for the gender. Available values include Male, Female, Other, and Unknown.
     * 
     * @param fullWord the entire word for the gender
     */
    public void setFullWord(String fullWord)
    {
        this.fullWord = fullWord;
    }

    /**
     * @return A string representation of the gender, which is just the full word.
     *
     * @see #getFullWord()
     */
    @Override
    public String toString()
    {
        return fullWord;
    }
}
