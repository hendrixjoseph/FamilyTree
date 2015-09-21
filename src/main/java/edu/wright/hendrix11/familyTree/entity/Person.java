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

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

    @ManyToMany
    @JoinTable(
            name = "SPOUSE_VIEW",
            joinColumns =
            {
                @JoinColumn(name = "ID", referencedColumnName = "ID")
            },
            inverseJoinColumns =
            {
                @JoinColumn(name = "SPOUSE_ID", referencedColumnName = "ID")
            })
    private List<Person> spouses;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "CHILDREN_VIEW",
            joinColumns =
            {
                @JoinColumn(name = "ID", referencedColumnName = "ID")
            },
            inverseJoinColumns =
            {
                @JoinColumn(name = "CHILD_ID", referencedColumnName = "ID")
            })
    private List<Person> children;

    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private Birth birth;
    
    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private Death death;

    public List<Person> getSpouses()
    {
        return spouses;
    }

    public void setSpouses(List<Person> spouses)
    {
        this.spouses = spouses;
    }

    public List<Person> getChildren()
    {
        return children;
    }

    public void setChildren(List<Person> children)
    {
        this.children = children;
    }

    public Birth getBirth()
    {
        return birth;
    }

    public void setBirth(Birth birth)
    {
        this.birth = birth;
    }

    public Death getDeath()
    {
        return death;
    }

    public void setDeath(Death death)
    {
        this.death = death;
    }


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

    /**
     *
     * @return
     */
    public boolean hasMother()
    {
        return hasParent(mother);
    }

    /**
     *
     * @return
     */
    public boolean hasFather()
    {
        return hasParent(father);
    }

    /**
     *
     * @return
     */
    public boolean hasParent()
    {
        return hasFather() || hasMother();
    }

    /**
     *
     * @return
     */
    public boolean hasParents()
    {
        return hasFather() && hasMother();
    }

    private boolean hasParent(Person parent)
    {
        return !(parent == null);
    }
}
