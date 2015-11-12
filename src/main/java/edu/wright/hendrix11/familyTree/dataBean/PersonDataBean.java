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

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
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

    public double averageAge()
    {
        String countQuery = "SELECT COUNT(AGE) FROM AGE_VIEW";
        String sumQuery = "SELECT SUM(AGE) FROM AGE_VIEW";

        double count = ( (BigDecimal) em.createNativeQuery(countQuery).getSingleResult() ).doubleValue();
        double sum = ( (BigDecimal) em.createNativeQuery(sumQuery).getSingleResult() ).doubleValue();

        return sum / count;
    }

    public Map<String,Integer> ages()
    {
        Map<String,Integer> ageMap = new LinkedHashMap<>();

        StringBuilder sb = new StringBuilder("SELECT COUNT(AGE),AGE FROM AGE_VIEW ");
        sb.append("GROUP BY AGE ORDER BY AGE");

        Query query = em.createNativeQuery(sb.toString());

        List list = query.getResultList();

        for(Object object : list)
        {
            Object[] o = (Object[])object;

            ageMap.put(o[1].toString(),((Number)o[0]).intValue());
        }

        return ageMap;
    }

    public Map<String, Integer[]> perDecadeClean()
    {
        Query query = em.createNativeQuery("SELECT * FROM PER_DECADE_CLEAN_VIEW");

        return processDecades(query.getResultList());
    }

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
