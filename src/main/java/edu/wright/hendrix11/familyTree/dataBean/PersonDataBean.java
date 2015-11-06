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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    public List<Object[]> ages()
    {
        StringBuilder sb = new StringBuilder("SELECT COUNT(AGE),AGE FROM AGE_VIEW ");
        sb.append("GROUP BY AGE ORDER BY AGE");

        Query query = em.createNativeQuery(sb.toString());

        return query.getResultList();
    }

    public Map<Object,Number> perDecadeClean(PerDecadeType event)
    {
        Query query = em.createNativeQuery(perDecadeQueryClean(event));

        return null;
    }

    public Map<Object,Number>[] perDecade()
    {
        StringBuilder sb = new StringBuilder("SELECT NVL(CB.C,0),NVL(CD.C,0), NVL(CB.D,CD.D) DECADE FROM (");
        sb.append(perDecadeQuery(PerDecadeType.BIRTHS)).append(") CB");
        sb.append(" FULL OUTER JOIN (");
        sb.append(perDecadeQuery(PerDecadeType.DEATHS)).append(") CD");
        sb.append(" ON CB.D = CD.D ORDER BY DECADE");

        Query query = em.createNativeQuery(sb.toString());

        return processDecades(query.getResultList());
    }

    private Map<Object,Number>[] processDecades(List<Object[]> decades)
    {
        Map<Object,Number> birthDecades = new LinkedHashMap<>();
        Map<Object,Number> deathDecades = new LinkedHashMap<>();

        for ( Object[] o : decades )
        {
            Number births = ( (Number) o[0] );
            Number deaths = ( (Number) o[1] );
            Number decade = ( (Number) o[1] );

            birthDecades.put(decade, births);
            deathDecades.put(decade, deaths);
        }

        return new Map[]{birthDecades, deathDecades};
    }

    private String perDecadeQueryClean(PerDecadeType event)
    {
        PerDecadeType otherEvent = PerDecadeType.BIRTHS;
        String plusMinus = "+";
        int age = (int)averageAge();

        if(event == PerDecadeType.BIRTHS)
        {
            plusMinus = "-";
            otherEvent = PerDecadeType.DEATHS;
        }

        String groupBy = new StringBuilder("(YEAR").append(plusMinus).append(age).append("-MOD(YEAR").append(plusMinus).append(age).append(",10))").toString();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(*),").append(groupBy);
        sb.append(" FROM EVENT");
        sb.append(" WHERE TYPE = '").append(otherEvent).append("' AND YEAR IS NOT NULL AND");
        sb.append(" (NOT PERSON_ID IN (SELECT PERSON_ID FROM EVENT WHERE TYPE='").append(event).append("')");
        sb.append(" OR EXISTS (SELECT * FROM EVENT WHERE TYPE='").append(event).append("' AND YEAR IS NULL))");
        sb.append(" GROUP BY ").append(groupBy);
        sb.append(" ORDER BY ").append(groupBy);
        LOG.log(Level.INFO, sb.toString());
        return sb.toString();
    }

    private String perDecadeQuery(PerDecadeType event)
    {
        String groupBy = "(YEAR-MOD(YEAR,10))";

        StringBuilder sb = new StringBuilder("SELECT COUNT(*) C,").append(groupBy).append(" D");
        sb.append(" FROM EVENT WHERE EVENT.TYPE='").append(event).append("' ");
        sb.append(" AND EVENT.YEAR IS NOT NULL ");
        sb.append(" GROUP BY ").append(groupBy);
        return sb.toString();
    }

    public enum PerDecadeType
    {
        BIRTHS("birth"),
        DEATHS("death");

        private String string;

        private PerDecadeType(String string)
        {
            this.string = string;
        }

        @Override
        public String toString()
        {
            return string;
        }
    }
}
