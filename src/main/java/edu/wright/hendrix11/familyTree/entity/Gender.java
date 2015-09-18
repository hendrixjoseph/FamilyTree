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

/**
 *
 * @author Joe Hendrix
 */
@Entity
public class Gender
{

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
}
