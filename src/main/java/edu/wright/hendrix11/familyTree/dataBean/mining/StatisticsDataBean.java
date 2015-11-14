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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * @return
     */
    public double averageAge()
    {
        String averageQuery = "SELECT AVG(AGE) FROM AGE_VIEW";

        return ( (BigDecimal) em.createNativeQuery(averageQuery).getSingleResult() ).doubleValue();
    }

    /**
     * @return
     */
    public Map<String, Integer> ages()
    {
        Map<String, Integer> ageMap = new LinkedHashMap<>();

        StringBuilder sb = new StringBuilder("SELECT COUNT(AGE),AGE FROM AGE_VIEW ");
        sb.append("GROUP BY AGE ORDER BY AGE");

        Query query = em.createNativeQuery(sb.toString());

        List<Object[]> list = query.getResultList();

        for ( Object[] o : list )
        {
            ageMap.put(o[1].toString(), ( (Number) o[0] ).intValue());
        }

        return ageMap;
    }

    /**
     * @return
     */
    public Map<String, Integer[]> perDecadeCombined()
    {
        Query query = em.createNativeQuery("SELECT * FROM PER_DECADE_COMBINED_VIEW");

        return processDecades(query.getResultList());
    }

    /**
     * @return
     */
    public Map<String, Integer[]> perDecadeClean()
    {
        Query query = em.createNativeQuery("SELECT * FROM PER_DECADE_CLEAN_VIEW");

        return processDecades(query.getResultList());
    }

    /**
     * @return
     */
    public Map<String, Integer[]> perDecade()
    {
        Query query = em.createNativeQuery("SELECT * FROM PER_DECADE_VIEW");

        return processDecades(query.getResultList());
    }

    private Map<String, Integer[]> processDecades(List<Object[]> decades)
    {
        Map<String, Integer[]> result = new LinkedHashMap<>();

        for ( Object[] o : decades )
        {
            Integer births = ( (Number) o[0] ).intValue();
            Integer deaths = ( (Number) o[1] ).intValue();
            String decade = o[2].toString();
            Integer[] array = {births, deaths};
            result.put(decade, array);
        }

        return result;
    }
}
