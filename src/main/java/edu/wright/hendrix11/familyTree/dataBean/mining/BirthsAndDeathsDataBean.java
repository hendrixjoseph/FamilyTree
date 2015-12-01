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

import java.time.Month;
import java.time.format.TextStyle;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * This class queries the database for the number of births and deaths per unit (the unit varies per method).
 * 
 * @author Joe Hendrix
 */
@Stateless
public class BirthsAndDeathsDataBean
{
    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    /**
     * Returns a map of decades (as a String) to an array integer of births and deaths in that decade. Internally,
     * this is a {@link LinkedHashMap}. The number of births is in the zeroth element, and the
     * number of deaths is in the next element.
     * <p>
     * The method gets its values from {@code PER_DECADE_COMBINED_VIEW}, which is just a sum
     * of the union of {@code PER_DECADE_CLEAN_VIEW} and {@code PER_DECADE_VIEW}.
     * <p>
     * The SQL query that defines the view is as follows:
     * <blockquote><pre>{@code 
	 * SELECT SUM( BIRTHS ) BIRTHS,
	 * 	   SUM( DEATHS ) DEATHS,
	 * 	   DECADE
	 * FROM
	 * 	(SELECT * FROM PER_DECADE_VIEW
	 * 	UNION
	 * 	SELECT * FROM PER_DECADE_CLEAN_VIEW)
	 * GROUP BY DECADE
	 * ORDER BY DECADE
     * }</pre></blockquote>
     * 
     * @return a map of decades to an integer array of births and deaths in that decade
     */
    public Map<String, Integer[]> perDecadeCombined()
    {
        Query query = em.createNativeQuery("SELECT * FROM PER_DECADE_COMBINED_VIEW");

        return processDecades(query.getResultList());
    }

    /**
     * Returns a map of decades (as a String) to an integer array of births and deaths in that decade. Internally,
     * this is a {@link LinkedHashMap}. The number of births is in the zeroth element, and the
     * number of deaths is in the next element.
     * <p>
     * The method gets its values from {@code PER_DECADE_CLEAN_VIEW}, which estimates the birth year 
     * and death year where the other is not known. It assumes an average age.
     * <p>
     * The SQL query that defines the view is as follows:
     * <blockquote><pre>{@code 
     * SELECT NVL( BIRTH.COUNT,0 ) BIRTHS,
	 * 	   NVL( DEATH.COUNT,0 ) DEATHS,
	 * 	   NVL( BIRTH.DECADE,DEATH.DECADE ) DECADE
	 * FROM
	 * 	(SELECT COUNT( * ) COUNT,
	 * 		  ( D.YEAR - AVERAGE - MOD( D.YEAR - AVERAGE,10 ) ) DECADE
	 * 	FROM EVENT D, 
	 * 		(SELECT AVG(AGE) AVERAGE FROM AGE_VIEW)
	 * 	WHERE D.TYPE = 'death'
	 * 	AND D.YEAR IS NOT NULL
	 * 	AND ( D.PERSON_ID NOT IN
	 * 		(SELECT PERSON_ID FROM EVENT WHERE TYPE = 'birth')
	 * 	OR EXISTS
	 * 		(SELECT * FROM EVENT B WHERE B.TYPE = 'birth' AND B.YEAR IS NULL AND B.PERSON_ID=D.PERSON_ID) )
	 * 	GROUP BY( D.YEAR - AVERAGE - MOD( D.YEAR - AVERAGE,10 ) ) ) BIRTH
	 * FULL OUTER JOIN
	 * 	(SELECT COUNT( * ) COUNT,
	 * 		  ( B.YEAR + AVERAGE - MOD( B.YEAR + AVERAGE ,10 ) ) DECADE
	 * 	FROM EVENT B, (SELECT AVG(AGE) AVERAGE FROM AGE_VIEW)
	 * 	WHERE B.TYPE = 'birth'
	 * 	AND B.YEAR IS NOT NULL
	 * 	AND( B.PERSON_ID NOT IN
	 * 	   (SELECT PERSON_ID FROM EVENT WHERE TYPE = 'death')
	 * 	OR EXISTS
	 * 	   (SELECT * FROM EVENT D WHERE D.TYPE = 'death' AND D.YEAR IS NULL AND D.PERSON_ID=B.PERSON_ID) )
	 * 	GROUP BY( B.YEAR + AVERAGE - MOD( B.YEAR + AVERAGE ,10 ) ) ) DEATH
	 * ON BIRTH.DECADE = DEATH.DECADE
	 * ORDER BY DECADE
     * }</pre></blockquote>
     * 
     * @return a map of decades to an integer array of births and deaths in that decade
     */
    public Map<String, Integer[]> perDecadeClean()
    {
        Query query = em.createNativeQuery("SELECT * FROM PER_DECADE_CLEAN_VIEW");

        return processDecades(query.getResultList());
    }

    /**
     * Returns a map of decades (as a String) to an integer array of births and deaths in that decade. Internally,
     * this is a {@link LinkedHashMap}. The number of births is in the zeroth element, and the
     * number of deaths is in the next element.
     * <p>
     * The method gets its values from {@code PER_DECADE_VIEW}, which is only contains the subset of births and deaths
     * where either the birth year or death year are known.
     * <p>
     * The SQL query that defines the view is as follows:
     * <blockquote><pre>{@code 
	 * SELECT NVL( BIRTH.COUNT,0 ) BIRTHS,
	 * 	   NVL( DEATH.COUNT,0 ) DEATHS,
	 * 	   NVL( BIRTH.DECADE,DEATH.DECADE ) DECADE
	 * FROM
	 * 	(SELECT COUNT( * ) COUNT,
	 * 		  ( YEAR - MOD( YEAR,10 ) ) DECADE
	 * 	FROM EVENT
	 * 	WHERE EVENT.TYPE = 'birth'
	 * 	AND EVENT.YEAR IS NOT NULL
	 * 	GROUP BY( YEAR - MOD( YEAR,10 ) ) ) BIRTH
	 * FULL OUTER JOIN
	 * 	(SELECT COUNT( * ) COUNT,
	 * 		  ( YEAR - MOD( YEAR,10 ) ) DECADE
	 * 	FROM EVENT
	 * 	WHERE EVENT.TYPE = 'death'
	 * 	AND EVENT.YEAR IS NOT NULL
	 * 	GROUP BY( YEAR - MOD( YEAR,10 ) ) ) DEATH
	 * ON BIRTH.DECADE = DEATH.DECADE
	 * ORDER BY DECADE
     * }</pre></blockquote>
     * 
     * @return a map of decades to an integer array of births and deaths in that decade
     */
    public Map<String, Integer[]> perDecade()
    {
        Query query = em.createNativeQuery("SELECT * FROM PER_DECADE_VIEW");

        return processDecades(query.getResultList());
    }

    /**
     * Returns a map of months (as a String) to an integer array of births and deaths in that month. Internally,
     * this is a {@link LinkedHashMap}. The number of births is in the zeroth element, and the
     * number of deaths is in the next element.
     * <p>
     * The method gets its values from {@code PER_MONTH_VIEW}, which is only contains the subset of births and deaths
     * where the month known.
     * <p>
     * The SQL query that defines the view is as follows:
     * <blockquote><pre>{@code 
	 * SELECT NVL( BIRTH.COUNT,0 ) BIRTHS,
	 * 	   NVL( DEATH.COUNT,0 ) DEATHS,
	 * 	   NVL( BIRTH.MONTH,DEATH.MONTH ) MONTH
	 * FROM
	 * 	(SELECT COUNT( * ) COUNT,
	 * 		    MONTH
	 * 	FROM EVENT
	 * 	WHERE TYPE = 'birth'
	 * 		AND MONTH IS NOT NULL
	 * 	GROUP BY MONTH) BIRTH
	 * FULL OUTER JOIN
	 * 	(SELECT COUNT( * ) COUNT,
	 * 		    MONTH
	 * 	FROM EVENT
	 * 	WHERE TYPE = 'death'
	 * 		AND MONTH IS NOT NULL
	 * 	GROUP BY MONTH) DEATH
	 * ON BIRTH.MONTH = DEATH.MONTH
	 * ORDER BY MONTH
     * }</pre></blockquote>
     * 
     * @return a map of months to an integer array of births and deaths in that month
     */
    public Map<String, Integer[]> perMonth()
    {
        Query query = em.createNativeQuery("SELECT * FROM PER_MONTH_VIEW");

        Map<String, Integer[]> result = new LinkedHashMap<>();

        List<Object[]> objects = query.getResultList();

        Month[] months = Month.values();

        for ( Object[] o : objects )
        {
            Integer births = ( (Number) o[0] ).intValue();
            Integer deaths = ( (Number) o[1] ).intValue();
            String month = months[( (Number) o[2] ).intValue()].getDisplayName(TextStyle.SHORT, Locale.US);

            Integer[] array = {births, deaths};
            result.put(month, array);
        }

        return result;
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
