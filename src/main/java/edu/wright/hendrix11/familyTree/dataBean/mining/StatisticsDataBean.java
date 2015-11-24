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

/**
 * @author Joe Hendrix
 */
@Stateless
public class StatisticsDataBean
{
    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

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
}
