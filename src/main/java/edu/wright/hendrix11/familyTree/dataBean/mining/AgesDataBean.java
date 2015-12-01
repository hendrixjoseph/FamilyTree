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

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class performs queries to three different views in the database: the {@code AGE_VIEW}, 
 * {@code AGE_TO_BIRTH_YEAR_VIEW}, and {@code AGE_TO_DEATH_YEAR_VIEW}. The latter two views actually
 * reference the first view.
 * <p>
 * The select statement for these views are defined as follows:
 * <p>
 * {@code AGE_VIEW}
 * <blockquote><pre>{@code
 * SELECT B.PERSON_ID,
 * 	  (D.YEAR-B.YEAR) AS AGE,
 * 	  B.YEAR AS BIRTH_YEAR,
 * 	  D.YEAR AS DEATH_YEAR
 * FROM EVENT B,EVENT D
 * WHERE B.PERSON_ID=D.PERSON_ID
 * 	AND B.TYPE='birth'
 * 	AND D.TYPE='death'
 * 	AND B.YEAR IS NOT NULL
 * 	AND D.YEAR IS NOT NULL;
 * }</pre></blockquote>
 * <p>
 * {@code AGE_TO_BIRTH_YEAR_VIEW}
 * <blockquote><pre>{@code
 * SELECT AVG(AGE) AVG_AGE, MEDIAN(AGE) MEDIAN_AGE, BIRTH_YEAR
 * FROM AGE_VIEW
 * GROUP BY BIRTH_YEAR
 * ORDER BY BIRTH_YEAR;
 * }</pre></blockquote>
 * <p>
 * {@code AGE_TO_DEATH_YEAR_VIEW}
 * <blockquote><pre>{@code
 * SELECT AVG(AGE) AVG_AGE, MEDIAN(AGE) MEDIAN_AGE, DEATH_YEAR
 * FROM AGE_VIEW
 * GROUP BY DEATH_YEAR
 * ORDER BY DEATH_YEAR;
 * }</pre></blockquote>
 * <p>
 * 
 * @author Joe Hendrix
 */
@Stateless
public class AgesDataBean
{
    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    /**
     * Returns the average age in the database. This average is calculated using the database's {@code AVG()} function,
     * and only accounts for those ages that can be calculated, i.e. ages of persons that have both a known birth and death
     * date.
     * 
     * @return the average age in the database
     */
    public double averageAge()
    {
        String averageQuery = "SELECT AVG(AGE) FROM AGE_VIEW";

        return ( (BigDecimal) em.createNativeQuery(averageQuery).getSingleResult() ).doubleValue();
    }

    /**
     * Returns the median age in the database. This median is calculated using the database's {@code MEDIAN()} function,
     * and only accounts for those ages that can be calculated, i.e. ages of persons that have both a known birth and death
     * date.
     * 
     * @return the median age in the database
     */
    public double medianAge()
    {
        String medianQuery = "SELECT MEDIAN(AGE) FROM AGE_VIEW";

        return ( (BigDecimal) em.createNativeQuery(medianQuery).getSingleResult() ).doubleValue();
    }

    /**
     * Returns the minimum age in the database. This minimum is calculated using the database's {@code MIN()} function,
     * and only accounts for those ages that can be calculated, i.e. ages of persons that have both a known birth and death
     * date.
     * 
     * @return the minimum age in the database
     */
    public int minAge()
    {
        String minQuery = "SELECT MIN(AGE) FROM AGE_VIEW";

        return ( (BigDecimal) em.createNativeQuery(minQuery).getSingleResult() ).intValue();
    }

    /**
     * Returns the maximum age in the database. This maximum is calculated using the database's {@code MAX()} function,
     * and only accounts for those ages that can be calculated, i.e. ages of persons that have both a known birth and death
     * date.
     * 
     * @return the maximum age in the database
     */
    public int maxAge()
    {
        String maxQuery = "SELECT MAX(AGE) FROM AGE_VIEW";

        return ( (BigDecimal) em.createNativeQuery(maxQuery).getSingleResult() ).intValue();
    }

    /**
     * Returns the specified age quartile. Being a quartile, the parameter should range from 1 to 4. This method
     * uses a nested query ({@ code SELECT AGE, NTILE(4) OVER (ORDER BY AGE) AS QUARTILE FROM AGE_VIEW}) to assign
     * each value to a quartile, then selects the maximum value from the specified quartile.
     * <p>
     * This method only accounts for those ages that can be calculated, i.e. ages of persons that have both a known birth 
     * and death date.
     * 
     * @param quartile an integer, which should range from 1 to 4.
     *
     * @return the specified age quartile
     * 
     * @throws IllegalArgumentException if the quartile is less than 1 or greater than 4
     */
    public int ageQuartile(int quartile)
    {
        if(quartile < 1 || quartile > 4)
        {
            throw new IllegalArgumentException("Quartile should range from 1 to 4!");
        }
        
        StringBuilder quartileQuery = new StringBuilder("SELECT MAX(AGE) FROM");
        quartileQuery.append(" (SELECT AGE,");
        quartileQuery.append(" NTILE(4) OVER (ORDER BY AGE) AS QUARTILE");
        quartileQuery.append(" FROM AGE_VIEW)");
        quartileQuery.append(" WHERE QUARTILE=").append(quartile);

        return ( (BigDecimal) em.createNativeQuery(quartileQuery.toString()).getSingleResult() ).intValue();
    }

    /**
     * Returns a map of ages to number of people who lived to be that age. Internally this is a {@link LinkedHashMap}.
     * The ages are recorded as a {@link String} while the count of people is recorded as a {@link Integer}.
     * <p>
     * This method only accounts for those ages that can be calculated, i.e. ages of persons that have both a known birth 
     * and death date.
     * 
     * @return a map of ages to number of people who lived to be that age
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
     * Returns a map of years to the mean and median ages of people who where born that year.
     * Internally this is a {@link LinkedHashMap}. The year is represented by a {@String} while
     * the mean and median is represented by an {@link Integer} array of length 2. The zeroth value
     * of the array is the mean, while the last value is the median.
     * <p>
     * This method only accounts for those ages that can be calculated, i.e. ages of persons that have both a known birth 
     * and death date.
     * 
     * @return a map of years to the mean and median ages of people who where born that year
     */
    public Map<String, Integer[]> meanMeadianAgesPerBirthYear()
    {
        return meanMeadianAgesPerYear("BIRTH");
    }

    /**
     * Returns a map of years to the mean and median ages of people who died that year.
     * Internally this is a {@link LinkedHashMap}. The year is represented by a {@String} while
     * the mean and median is represented by an {@link Integer} array of length 2. The zeroth value
     * of the array is the mean, while the last value is the median.
     * <p>
     * This method only accounts for those ages that can be calculated, i.e. ages of persons that have both a known birth 
     * and death date.
     * 
     * @return a map of years to the mean and median ages of people who died that year
     */
    public Map<String, Integer[]> meanMeadianAgesPerDeathYear()
    {
        return meanMeadianAgesPerYear("DEATH");
    }

    private Map<String, Integer[]> meanMeadianAgesPerYear(String which)
    {
        Map<String, Integer[]> result = new LinkedHashMap<>();

        Query query = em.createNativeQuery("SELECT * FROM AGE_TO_" + which + "_YEAR_VIEW");

        List<Object[]> list = query.getResultList();

        for ( Object[] o : list )
        {
            Integer mean = ( (Number) o[0] ).intValue();
            Integer median = ( (Number) o[1] ).intValue();
            String year = o[2].toString();
            Integer[] array = {mean, median};
            result.put(year, array);
        }

        return result;
    }
}
