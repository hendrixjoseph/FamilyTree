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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Joe Hendrix
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Gender.FIND_ALL, query = "SELECT g FROM Gender g")
    })
public class Gender
{
    public static final String FIND_ALL = "Gender.findAll";

    @Id
    private char abbr;

    @Column(name = "FULL_WORD")
    private String fullWord;

    /**
     *
     * @return
     */
    public char getAbbr()
    {
        return abbr;
    }

    /**
     *
     * @param abbr
     */
    public void setAbbr(char abbr)
    {
        this.abbr = abbr;
    }

    /**
     *
     * @return
     */
    public String getFullWord()
    {
        return fullWord;
    }

    /**
     *
     * @param fullWord
     */
    public void setFullWord(String fullWord)
    {
        this.fullWord = fullWord;
    }

public String toString()
{
return fullWord;
}
}
