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

package edu.wright.hendrix11.familyTree.dataBean;

import edu.wright.hendrix11.familyTree.entity.Person;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author Joe Hendrix
 */
@Stateless
public class PersonDataBean extends AbstractDataBean<Person, Integer>
{

    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    @Override
    @PostConstruct
    protected void initialize()
    {
        initialize(em, Person.class);
    }

    /**
     * Returns the first person in the database. This method uses a {@link javax.persistence.TypedQuery}
     * generated from the {@link javax.persistence.NamedQuery} represented by {@link Person#FIND_ALL}.
     * 
     * @return the first person in the database
     * 
     * @throws javax.persistence.NoResultException if there is no result
     */
    public Person findFirst()
    {
        TypedQuery<Person> personQuery = em.createNamedQuery(Person.FIND_FIRST, Person.class);
        return personQuery.getSingleResult();
    }
}
