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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Joe Hendrix
 */
@Entity
@Table(name = "PERSON_VIEW")
public class PersonView implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FATHER_ID")
    private PersonView father;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOTHER_ID")
    private PersonView mother;

    private String name;
    private String gender;

    @Column(name = "DATE_OF_BIRTH")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "PLACE_OF_BIRTH")
    private String placeOfBirth;

    @Column(name = "DATE_OF_DEATH")
    @Temporal(TemporalType.DATE)
    private Date dateOfDeath;

    @Column(name = "PLACE_OF_DEATH")
    private String placeOfDeath;

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
    private List<PersonView> spouses;

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
    private List<PersonView> children;

    /**
     *
     */
    public PersonView()
    {
        // No arg constructor
    }

    /**
     *
     * @param name
     */
    public PersonView(String name)
    {
        this.name = name;
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

    private boolean hasParent(PersonView parent)
    {
        return !(parent == null || !parent.exists());
    }

    /**
     *
     * @return
     */
    public Integer getId()
    {
        return id;
    }

    /**
     *
     * @return
     */
    public PersonView getFather()
    {
        return father;
    }

    /**
     *
     * @return
     */
    public PersonView getMother()
    {
        return mother;
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
     * @return
     */
    public String getGender()
    {
        return gender;
    }

    /**
     *
     * @return
     */
    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     *
     * @return
     */
    public String getPlaceOfBirth()
    {
        return placeOfBirth;
    }

    /**
     *
     * @return
     */
    public Date getDateOfDeath()
    {
        return dateOfDeath;
    }

    /**
     *
     * @return
     */
    public String getPlaceOfDeath()
    {
        return placeOfDeath;
    }

    /**
     *
     * @param spouse
     * @return
     */
    public List<PersonView> getChildren(PersonView spouse)
    {
        List<PersonView> childrenOfSpouse = new ArrayList<>();

        for (PersonView child : children)
        {
            if (child.getFather().equals(spouse) || child.getMother().equals(spouse))
            {
                childrenOfSpouse.add(child);
            }
        }

        return childrenOfSpouse;
    }

    /**
     *
     * @param father
     */
    public void setFather(PersonView father)
    {
        this.father = father;
    }

    /**
     *
     * @param mother
     */
    public void setMother(PersonView mother)
    {
        this.mother = mother;
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
     * @param gender
     */
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    /**
     *
     * @param dateOfBirth
     */
    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     *
     * @param placeOfBirth
     */
    public void setPlaceOfBirth(String placeOfBirth)
    {
        this.placeOfBirth = placeOfBirth;
    }

    /**
     *
     * @param dateOfDeath
     */
    public void setDateOfDeath(Date dateOfDeath)
    {
        this.dateOfDeath = dateOfDeath;
    }

    /**
     *
     * @param placeOfDeath
     */
    public void setPlaceOfDeath(String placeOfDeath)
    {
        this.placeOfDeath = placeOfDeath;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public List<PersonView> getChildrenNoSpouse()
    {
        List<PersonView> childrenNoSpouse = new ArrayList<>();

        for (PersonView child : children)
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
    public boolean exists()
    {
        return name != null && !name.isEmpty();
    }

    /**
     *
     * @return
     */
    public List<PersonView> getChildren()
    {
        return children;
    }

    /**
     *
     * @param children
     */
    public void setChildren(List<PersonView> children)
    {
        this.children = children;
    }

    /**
     *
     * @return
     */
    public List<PersonView> getSpouses()
    {
        return spouses;
    }

    //@Override
    public boolean equals(Object o)
    {
        if (o instanceof PersonView)
        {
            PersonView p = (PersonView) o;

            //if (p.id != null && this.id != null)
            {
                return Objects.equals(this.id, p.id);
            }

            //return p.getName().equals(this.getName());
        }
        else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return id;
    }

    @Override
    public String toString()
    {
        return toString("");
    }

    /**
     *
     * @param tabs
     * @return
     */
    public String toString(String tabs)
    {
        StringBuilder sb = new StringBuilder();

        if (exists())
        {
            sb.append(tabs).append("id:\t").append(id).append("\n");
            sb.append(tabs).append("name:\t").append(name).append("\n");
            sb.append(tabs).append("gender:\t").append(gender).append("\n");

            sb.append(tabs).append("father:\t");

            if (father != null && father.exists())
            {
                sb.append(father.getId()).append(" ").append(father.getName()).append("\n");
            }
            else
            {
                sb.append("(null)").append("\n");
            }

            sb.append(tabs).append("mother:\t");

            if (mother != null && mother.exists())
            {
                sb.append(mother.getId()).append(" ").append(mother.getName());
            }
            else
            {
                sb.append("(null)");
            }
        }
        else
        {
            sb.append(tabs).append("(null)");
        }

//        private Date dateOfBirth;
//        private String placeOfBirth;
//        private Date dateOfDeath;
//        private String placeOfDeath;
        return sb.toString();
    }
}
