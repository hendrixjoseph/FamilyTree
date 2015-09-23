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

import java.util.ArrayList;
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
import javax.persistence.PrimaryKeyJoinColumn;
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
    @PrimaryKeyJoinColumn
    private Birth birth;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Death death;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Burial burial;

    /**
     *
     * @return
     */
    public List<Person> getSpouses()
    {
        return spouses;
    }

    /**
     *
     * @param spouses
     */
    public void setSpouses(List<Person> spouses)
    {
        this.spouses = spouses;
    }

    /**
     *
     * @return
     */
    public List<Person> getChildren()
    {
        return children;
    }

    /**
     *
     * @return
     */
    public List<Person> getChildren(Person spouse)
    {
        List<Person> childrenOfSpouse = new ArrayList<>();

        for(Person child : children)
        {
            if(child.hasFather() && child.getFather().equals(spouse))
                childrenOfSpouse.add(child);
            else if(child.hasMother() && child.getMother().equals(spouse))
                childrenOfSpouse.add(child);
        }

        return children;
    }

    /**
     *
     * @return
     */
    public List<Person> getChildrenNoSpouse()
    {
        List<Person> childrenNoSpouse = new ArrayList<>();

        for(Person child : children)
        {
            if(!child.hasFather() || !child.hasMother())
                childrenNoSpouse.add(child);
        }

        return children;
    }

    /**
     *
     * @param children
     */
    public void setChildren(List<Person> children)
    {
        this.children = children;
    }

    /**
     *
     * @return
     */
    public Birth getBirth()
    {
        return birth;
    }

    /**
     *
     * @param birth
     */
    public void setBirth(Birth birth)
    {
        this.birth = birth;
    }

    /**
     *
     * @return
     */
    public Death getDeath()
    {
        return death;
    }

    /**
     *
     * @param burial
     */
    public void setBurial(Burial burial)
    {
        this.burial = burial;
    }

    /**
     *
     * @return
     */
    public Burial getBurial()
    {
        return burial;
    }

    /**
     *
     * @param death
     */
    public void setDeath(Death death)
    {
        this.death = death;
    }

    /**
     *
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Gender getGender()
    {
        return gender;
    }

    /**
     *
     * @param gender
     */
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    /**
     *
     * @return
     */
    public int getId()
    {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Person getFather()
    {
        return father;
    }

    /**
     *
     * @param father
     */
    public void setFather(Person father)
    {
        this.father = father;
    }

    /**
     *
     * @return
     */
    public Person getMother()
    {
        return mother;
    }

    /**
     *
     * @param mother
     */
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
