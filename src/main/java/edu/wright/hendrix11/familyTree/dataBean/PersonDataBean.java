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

import java.math.BigDecimal;
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

    public double averageAge()
    {
        String countQuery = "SELECT COUNT(AGE) FROM AGE_VIEW";
        String sumQuery = "SELECT SUM(AGE) FROM AGE_VIEW";

        double count = ((BigDecimal) em.createNativeQuery(countQuery).getSingleResult()).doubleValue();
        double sum = ((BigDecimal) em.createNativeQuery(sumQuery).getSingleResult()).doubleValue();

        return sum / count;
    }

    public List<Object[]> ages()
    {
        StringBuilder sb = new StringBuilder("SELECT COUNT(AGE),AGE FROM AGE_VIEW ");
        sb.append("GROUP BY AGE ORDER BY AGE");

        Query query = em.createNativeQuery(sb.toString());

        return query.getResultList();
    }

    public List<Object[]> birthsPerDecade()
    {
        Query query = em.createNativeQuery(perDecadeQuery("birth"));

        return query.getResultList();
    }

    public List<Object[]> deathsPerDecade()
    {
        Query query = em.createNativeQuery(perDecadeQuery("death"));

        return query.getResultList();
    }



    private String perDecadeQuery(String event)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(*),(YEAR-MOD(YEAR,10)) ");
        sb.append("FROM EVENT WHERE EVENT.TYPE='").append(event).append("' ");
        sb.append("AND EVENT.YEAR IS NOT NULL ");
        sb.append("GROUP BY (YEAR-MOD(YEAR,10)) ");
        sb.append("ORDER BY (YEAR-MOD(YEAR,10))");
        return sb.toString();
    }
}
