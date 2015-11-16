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
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
@Stateless
public class PersonDataBean extends DataBean<Person, Integer>
{
    private static final Logger LOG = Logger.getLogger(PersonDataBean.class.getName());

    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    @PostConstruct
    private void initialize()
    {
        initialize(em, Person.class);
    }

    /**
     * Returns the first person in the database. This method uses a {@link TypedQuery} generated from the {@link
     * NamedQuery} represented by {@link Person#FIND_ALL}.
     *
     * @return the first person in the database
     *
     * @throws NoResultException if there is no result
     */
    public Person findFirst()
    {
        TypedQuery<Person> personQuery = em.createNamedQuery(Person.FIND_FIRST, Person.class);
        return personQuery.getSingleResult();
    }
}
