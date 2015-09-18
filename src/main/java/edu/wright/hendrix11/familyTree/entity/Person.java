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

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Entity
@Table(name = "PERSON")
public class Person
{

    @Id
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "GENDER")
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "FATHER_OF",
            joinColumns = @JoinColumn(name = "CHILD_ID"),
            inverseJoinColumns = @JoinColumn(name = "FATHER_ID"))
    private Person father;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "MOTHER_OF",
            joinColumns = @JoinColumn(name = "CHILD_ID"),
            inverseJoinColumns = @JoinColumn(name = "MOTHER_ID"))
    private Person mother;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Person getFather()
    {
        return father;
    }

    public void setFather(Person father)
    {
        this.father = father;
    }

    public Person getMother()
    {
        return mother;
    }

    public void setMother(Person mother)
    {
        this.mother = mother;
    }
}
