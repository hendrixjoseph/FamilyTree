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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Entity
@NamedQueries({
                      @NamedQuery(name = Person.FIND_FIRST,
                                  query = "SELECT p FROM Person p WHERE p.id=(SELECT MIN(p2.id) FROM Person p2)"),
                      @NamedQuery(name = Person.FIND_ALL,
                                  query = "SELECT p FROM Person p")
              })
@Table(name = "PERSON")
public class Person
{

    /**
     *
     */
    public static final String FIND_FIRST = "Person.findFirst";
    public static final String FIND_ALL = "Person.findAll";

    @Id
    @SequenceGenerator(name = "PERSON_SEQUENCE", sequenceName = "PERSON_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "PERSON_SEQUENCE")
    private int id;
    @OneToOne(cascade = ALL, mappedBy = "person")
    private Birth birth;
    @OneToOne(cascade = ALL, mappedBy = "person")
    private Burial burial;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "CHILDREN_VIEW",
            joinColumns = {@JoinColumn(name = "ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "CHILD_ID", referencedColumnName = "ID")})
    private List<Person> children;
    @OneToOne(cascade = ALL, mappedBy = "person")
    private Death death;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "FATHER_OF",
               joinColumns = @JoinColumn(name = "CHILD_ID"),
               inverseJoinColumns = @JoinColumn(name = "FATHER_ID"))
    private Person father;
    @ManyToOne
    @JoinColumn(name = "GENDER")
    @NotNull
    private Gender gender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "MOTHER_OF",
               joinColumns = @JoinColumn(name = "CHILD_ID"),
               inverseJoinColumns = @JoinColumn(name = "MOTHER_ID"))
    private Person mother;
    @NotNull
    private String name;
    @ManyToMany
    @JoinTable(
            name = "SPOUSE_VIEW",
            joinColumns = {@JoinColumn(name = "ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "SPOUSE_ID", referencedColumnName = "ID")})
    private List<Person> spouses;

    /**
     * @return
     */
    public Birth getBirth()
    {
        return birth;
    }

    /**
     * @param birth
     */
    public void setBirth(Birth birth)
    {
        if ( birth != null )
            birth.setPerson(this);

        this.birth = birth;
    }

    /**
     * @return
     */
    public Burial getBurial()
    {
        return burial;
    }

    /**
     * @param burial
     */
    public void setBurial(Burial burial)
    {
        if ( burial != null )
            burial.setPerson(this);
        this.burial = burial;
    }

    /**
     * @return
     */
    public List<Person> getChildren()
    {
        return children;
    }

    /**
     * @param children
     */
    public void setChildren(List<Person> children)
    {
        this.children = children;
    }

    /**
     * @param spouse
     *
     * @return
     */
    public List<Person> getChildren(Person spouse)
    {
        List<Person> childrenOfSpouse = new ArrayList<>();

        for ( Person child : children )
        {
            if ( child.hasFather() && child.getFather().equals(spouse) || child.hasMother() && child.getMother().equals(spouse) )
            {
                childrenOfSpouse.add(child);
            }
        }

        return childrenOfSpouse;
    }

    /**
     * @return
     */
    public List<Person> getChildrenNoSpouse()
    {
        List<Person> childrenNoSpouse = new ArrayList<>();

        for ( Person child : children )
        {
            if ( !child.hasFather() || !child.hasMother() )
            {
                childrenNoSpouse.add(child);
            }
        }

        return childrenNoSpouse;
    }

    /**
     * @return
     */
    public Death getDeath()
    {
        return death;
    }

    /**
     * @param death
     */
    public void setDeath(Death death)
    {
        if ( death != null )
            death.setPerson(this);
        this.death = death;
    }

    /**
     * @return
     */
    public Person getFather()
    {
        return father;
    }

    /**
     * @param father
     */
    public void setFather(Person father)
    {
        this.father = father;
    }

    /**
     * @return
     */
    public Gender getGender()
    {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    /**
     * @return
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * \     * @return
     *
     * @return
     */
    public Person getMother()
    {
        return mother;
    }

    /**
     * @param mother
     */
    public void setMother(Person mother)
    {
        this.mother = mother;
    }

    /**
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return
     */
    public List<Person> getSpouses()
    {
        return spouses;
    }

    /**
     * @param spouses
     */
    public void setSpouses(List<Person> spouses)
    {
        this.spouses = spouses;
    }

    /**
     * @return
     */
    public boolean hasFather()
    {
        return hasParent(father);
    }

    /**
     * @return
     */
    public boolean hasMother()
    {
        return hasParent(mother);
    }

    /**
     * @return
     */
    public boolean hasParent()
    {
        return hasFather() || hasMother();
    }

    /**
     * @return
     */
    public boolean hasParents()
    {
        return hasFather() && hasMother();
    }

    private boolean hasParent(Person parent)
    {
        return !( parent == null );
    }
}