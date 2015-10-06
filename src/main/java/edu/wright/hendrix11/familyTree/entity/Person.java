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

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.*;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Entity
@Table(name = "PERSON")
public class Person
{

    @Id
    @SequenceGenerator(name = "PERSON_SEQUENCE", sequenceName = "PERSON_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "PERSON_SEQUENCE")
    private int id;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "GENDER")
    @NotNull
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY, cascade = ALL)
    @JoinTable(name = "FATHER_OF",
            joinColumns = @JoinColumn(name = "CHILD_ID"),
            inverseJoinColumns = @JoinColumn(name = "FATHER_ID"))
    private Person father;

    @ManyToOne(fetch = FetchType.LAZY, cascade = ALL)
    @JoinTable(name = "MOTHER_OF",
            joinColumns = @JoinColumn(name = "CHILD_ID"),
            inverseJoinColumns = @JoinColumn(name = "MOTHER_ID"))
    private Person mother;

    @ManyToMany
    @JoinTable(
            name = "SPOUSE_VIEW",
            joinColumns = {@JoinColumn(name = "ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "SPOUSE_ID", referencedColumnName = "ID")})
    private List<Person> spouses;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "CHILDREN_VIEW",
            joinColumns = {@JoinColumn(name = "ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "CHILD_ID", referencedColumnName = "ID")})
    private List<Person> children;

    @OneToOne(cascade = ALL, mappedBy = "person")
    private Birth birth;

    @OneToOne(cascade = ALL, mappedBy = "person")
    private Death death;

    @OneToOne(cascade = ALL, mappedBy = "person")
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
     * @param children
     */
    public void setChildren(List<Person> children)
    {
        this.children = children;
    }

    /**
     *
     * @param spouse
     * @return
     */
    public List<Person> getChildren(Person spouse)
    {
        List<Person> childrenOfSpouse = new ArrayList<>();

        for (Person child : children)
        {
            if (child.hasFather() && child.getFather().equals(spouse))
            {
                childrenOfSpouse.add(child);
            }
            else if (child.hasMother() && child.getMother().equals(spouse))
            {
                childrenOfSpouse.add(child);
            }
        }

        return childrenOfSpouse;
    }

    /**
     *
     * @return
     */
    public List<Person> getChildrenNoSpouse()
    {
        List<Person> childrenNoSpouse = new ArrayList<>();

        for (Person child : children)
        {
            if (!child.hasFather() || !child.hasMother())
            {
                childrenNoSpouse.add(child);
            }
        }

        return childrenNoSpouse;
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
        if (birth != null)
            birth.setPerson(this);

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
     * @param death
     */
    public void setDeath(Death death)
    {
        if (death != null)
            death.setPerson(this);
        this.death = death;
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
     * @param burial
     */
    public void setBurial(Burial burial)
    {
        if (burial != null)
            burial.setPerson(this);
        this.burial = burial;
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
     * \     * @return
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