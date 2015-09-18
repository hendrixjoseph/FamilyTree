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

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class SpouseChildMap
{

    PersonView person;
    PersonView spouse;
    PersonView child;

    /**
     *
     */
    public SpouseChildMap()
    {
        person = new PersonView();
        spouse = null;
        child = null;
    }

    /**
     *
     * @return
     */
    public PersonView getPerson()
    {
        return person;
    }

    /**
     *
     * @param person
     */
    public void setPerson(PersonView person)
    {
        this.person = person;
    }

    /**
     *
     * @return
     */
    public PersonView getSpouse()
    {
        if (spouse == null)
        {
            spouse = new PersonView();
        }

        return spouse;
    }

    /**
     *
     * @param spouse
     */
    public void setSpouse(PersonView spouse)
    {
        this.spouse = spouse;
    }

    /**
     *
     * @return
     */
    public PersonView getChild()
    {
        if (child == null)
        {
            child = new PersonView();
        }

        return child;
    }

    /**
     *
     * @param child
     */
    public void setChild(PersonView child)
    {
        this.child = child;
    }

    /**
     *
     * @param person
     * @param spouse
     * @param child
     */
    public void setPeople(PersonView person, PersonView spouse, PersonView child)
    {
        this.person = person;
        this.spouse = spouse;
        this.child = child;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("person:\n").append(person.toString("\t"));

        sb.append("\nspouse:\n").append(spouse == null ? "\t(null)" : spouse.toString("\t"));

        sb.append("\nchild:\n").append(child == null ? "\t(null)" : child.toString("\t"));

        return sb.toString();
    }
}
