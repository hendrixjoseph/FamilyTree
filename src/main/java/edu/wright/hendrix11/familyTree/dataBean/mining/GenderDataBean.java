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

package edu.wright.hendrix11.familyTree.dataBean.mining;

import edu.wright.hendrix11.familyTree.entity.Gender;
import edu.wright.hendrix11.familyTree.entity.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

/**
 * @author Joe Hendrix
 */
@Stateless
public class GenderDataBean
{
    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    /**
     * Returns the number of people of the specified gender.
     * <p>
     * This is calculated using the {@link NamedQuery} specified by {@link Person#COUNT_GENDERS}.
     *
     * @param gender the gender to be counted
     *
     * @return the number of people of the specified gender
     */
    public int countGender(Gender gender)
    {
        TypedQuery<Long> countQuery = em.createNamedQuery(Person.COUNT_GENDERS, Long.class);
        countQuery.setParameter("gender", gender);
        return countQuery.getSingleResult().intValue();
    }
    
    /**
     * Returns the average age of people of the specified gender.
     * <p>
     * This is calculated using the database's {@code AVG()} function.
     * 
     * @param gender the gender to be calculated
     *
     * @return the average age of people of the specified gender
     */
    public double averageAge(Gender gender)
    {
        StringBuilder sb = new StringBuilder("SELECT AVG(AGE) FROM AGE_VIEW, PERSON");
        sb.append(" WHERE AGE_VIEW.PERSON_ID=PERSON.ID");
        sb.append(" GROUP BY GENDER");
        sb.append(" HAVING GENDER='").append(gender.name()).append("'");

        List<Object> objects = em.createNativeQuery(sb.toString()).getResultList();

        if ( objects.isEmpty() )
        {
            return 0;
        }
        else
        {
            return ( (Number) objects.get(0) ).doubleValue();
        }
    }
}
