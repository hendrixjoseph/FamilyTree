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

import edu.wright.hendrix11.familyTree.entity.event.Birth;
import edu.wright.hendrix11.familyTree.entity.event.Burial;
import edu.wright.hendrix11.familyTree.entity.event.Death;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.persistence.TypedQuery;
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
                                  query = "SELECT p FROM Person p"),
                      @NamedQuery(name = Person.COUNT_GENDERS,
                                  query = "SELECT COUNT(p.id) FROM Person p WHERE p.gender = :gender")
              })
@Table(name = "PERSON")
public class Person
{
    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery} 
     * to count the number of people of a specific gender.
     * 
     * For example: {@code TypedQuery<Person> query = em.createNamedQuery(Person.COUNT_GENDERS, Person.class);}
     */
    public static final String COUNT_GENDERS = "Person.countGenders";
    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery} 
     * to get all people.
     *
     * For example: {@code TypedQuery<Person> query = em.createNamedQuery(Person.FIND_ALL, Person.class);}
     *              {@code query..setParameter("gender", gender);
     */
    public static final String FIND_ALL = "Person.findAll";
    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery} 
     * to get the first person in the database.
     * 
     * For example: {@code TypedQuery<Person> query = em.createNamedQuery(Person.FIND_FIRST, Person.class);}
     */
    public static final String FIND_FIRST = "Person.findFirst";
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
    @JoinTable(name = "FATHER",
               joinColumns = @JoinColumn(name = "CHILD_ID"),
               inverseJoinColumns = @JoinColumn(name = "ID"))
    private Person father;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "MOTHER",
               joinColumns = @JoinColumn(name = "CHILD_ID"),
               inverseJoinColumns = @JoinColumn(name = "ID"))
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
     * Returns the birth record for this person.
     *
     * @return the birth record for this person
     */
    public Birth getBirth()
    {
        return birth;
    }

    /**
     * Sets the birth record for this person. Before setting the record, it associates this person with that record (via
     * {@code birth.setPerson(this)}).
     *
     * @param birth the birth record for this person
     */
    public void setBirth(Birth birth)
    {
        if ( birth != null )
            birth.setPerson(this);

        this.birth = birth;
    }

    /**
     * Returns the burial record for this person.
     *
     * @return the burial record for this person
     */
    public Burial getBurial()
    {
        return burial;
    }

    /**
     * Sets the burial record for this person. Before setting the record, it associates this person with that record
     * (via {@code burial.setPerson(this)}).
     *
     * @param burial the burial record for this person
     */
    public void setBurial(Burial burial)
    {
        if ( burial != null )
            burial.setPerson(this);

        this.burial = burial;
    }

    /**
     * @return the list of all children for this person
     */
    public List<Person> getChildren()
    {
        return children;
    }

    /**
     * @param children the list of all children for this person
     */
    public void setChildren(List<Person> children)
    {
        this.children = children;
    }

    /**
     * Returns a list of all children of this person whose other parent is the specified spouse.
     *
     * @param spouse the other parent
     *
     * @return the list of all children for this person and the specified spouse
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
     * Returns a list of all children of this person whose other parent is unknown.
     *
     * @return the list of all children for this person whose other parent is unknown
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
     * Returns the death record for this person.
     *
     * @return the death record for this person
     */
    public Death getDeath()
    {
        return death;
    }

    /**
     * Sets the death record for this person. Before setting the record, it associates this person with that record (via
     * {@code death.setPerson(this)}).
     *
     * @param death the death record for this person
     */
    public void setDeath(Death death)
    {
        if ( death != null )
            death.setPerson(this);
        this.death = death;
    }

    /**
     * Returns the father of this person.
     *
     * @return the father of this person
     */
    public Person getFather()
    {
        return father;
    }

    /**
     * Sets the father of this person.
     *
     * @param father the father of this person
     */
    public void setFather(Person father)
    {
        this.father = father;
    }

    /**
     * Returns the gender of this person. Available genders are male, female, other, and unknown.
     *
     * @return the gender of this person
     */
    public Gender getGender()
    {
        return gender;
    }

    /**
     * Sets the gender of this person. Available genders are male, female, other, and unknown.
     *
     * @param gender the gender of this person
     */
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    /**
     * Returns the database primary key for this person. This key is autogenerated by the database.
     *
     * @return the database primary key for this person
     */
    public int getId()
    {
        return id;
    }

    /**
     * Sets the database primary key for this person. This key is autogenerated by the database.
     *
     * @param id the database primary key for this person
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Returns the mother of this person.
     *
     * @return the mother of this person
     */
    public Person getMother()
    {
        return mother;
    }

    /**
     * Sets the mother of this person.
     *
     * @param mother the mother of this person
     */
    public void setMother(Person mother)
    {
        this.mother = mother;
    }

    /**
     * Returns the name of this person.
     *
     * @return the name of this person
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of this person
     *
     * @param name the name of this person
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the list of known spouses of this person. A spouse is defined as either someone a person is married to or
     * the other parent of a person's children.
     *
     * @return the list of known spouses of this person
     */
    public List<Person> getSpouses()
    {
        return spouses;
    }

    /**
     * Sets the list of known spouses of this person. A spouse is defined as either someone a person is married to or
     * the other parent of a person's children.
     *
     * @param spouses the list of known spouses of this person
     */
    public void setSpouses(List<Person> spouses)
    {
        this.spouses = spouses;
    }

    /**
     * Returns {@code true} if this person has a father, {@code false} otherwise.
     *
     * @return {@code true} if this person has a father, {@code false} otherwise
     */
    public boolean hasFather()
    {
        return hasParent(father);
    }

    /**
     * Returns {@code true} if this person has a mother, {@code false} otherwise.
     *
     * @return {@code true} if this person has a mother, {@code false} otherwise
     */
    public boolean hasMother()
    {
        return hasParent(mother);
    }

    /**
     * Returns {@code true} if this person has at least one parent, {@code false} otherwise. This is done simply by
     * or-ing {@link #hasFather()} and {@link #hasMother()}: {@code return hasFather() || hasMother();}.
     *
     * @return {@code true} if this person has at least one parent, {@code false} otherwise
     */
    public boolean hasParent()
    {
        return hasFather() || hasMother();
    }

    /**
     * Returns {@code true} if this person has both parents, {@code false} otherwise. This is done simply by and-ing
     * {@link #hasFather()} and {@link #hasMother()}: {@code return hasFather() && hasMother();}.
     *
     * @return {@code true} if this person has both parents, {@code false} otherwise
     */
    public boolean hasParents()
    {
        return hasFather() && hasMother();
    }

    private boolean hasParent(Person parent)
    {
        return !( parent == null );
    }

    /**
     * Returns a string representation of the person, which is just the person's name.
     *
     * @return A string representation of the person, which is just the person's name.
     *
     * @see #getName()
     */
    @Override
    public String toString()
    {
        return name;
    }
}
