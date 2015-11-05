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

import edu.wright.hendrix11.familyTree.entity.Gender;
import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.entity.event.Birth;
import edu.wright.hendrix11.familyTree.entity.event.Death;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

/**
 * @author Joe Hendrix
 */
@Stateless
public class PersonDataBean extends DataBean<Person, Integer>
{

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

    /**
     * Returns the number of people of the specified gender.
     *
     * @param gender the gender to be counted
     *
     * @return the number of people of the specified gender
     */
    public long countGender(Gender gender)
    {
        TypedQuery<Long> countQuery = em.createNamedQuery(Person.COUNT_GENDERS, Long.class);
        countQuery.setParameter("gender", gender);
        return countQuery.getSingleResult();
    }

    public List<Object[]> birthsPerYear()
    {
        Query namedQuery = em.createNamedQuery(Birth.COUNT_BY_YEAR);

        return namedQuery.getResultList();
    }

    public List<Object[]> deathsPerYear()
    {
        Query namedQuery = em.createNamedQuery(Death.COUNT_BY_YEAR);

        return namedQuery.getResultList();
    }

    public List<Object[]> ages()
    {
        StringBuilder sb = new StringBuilder("SELECT COUNT(AGE),AGE FROM ");
        sb.append("(SELECT (D.YEAR-B.YEAR) AS AGE ");
        sb.append("FROM EVENT B,EVENT D ");
        sb.append("WHERE B.PERSON_ID=D.PERSON_ID ");
        sb.append("AND B.TYPE='birth' ");
        sb.append("AND D.TYPE='death') ");
        sb.append("GROUP BY AGE ");
        sb.append("HAVING AGE IS NOT NULL ");
        sb.append("ORDER BY AGE");

        Query query = em.createNativeQuery(sb.toString());

        return query.getResultList();
    }
}
