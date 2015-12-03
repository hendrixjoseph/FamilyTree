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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class queries the database to get the most common names.
 *
 * @author Joe Hendrix
 */
@Stateless
public class NamesDataBean
{
    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    /**
     * Returns a map of first names to the number of occurrences of that first name.
     * <p>
     * Internally this is a {@link HashMap}. It only returns at most the top 20 names. Since the entire name is stored
     * in the database as a single field, a regular expression is used in a view inside the database itself to count the
     * first names.
     * <p>
     * The select statement defining the view appears as follows:
     * <blockquote><pre>{@code
     * SELECT COUNT(FIRST_NAME) COUNT, FIRST_NAME
     * FROM (SELECT REGEXP_SUBSTR(NAME,'^\w+') AS FIRST_NAME FROM PERSON)
     * GROUP BY FIRST_NAME
     * ORDER BY COUNT DESC;
     * }</pre></blockquote>
     *
     * @return a map of first names to the number of occurrences of that first name
     */
    public Map<String, Integer> firstNameFrequency()
    {
        return nameFrequency("FIRST");
    }

    /**
     * Returns a map of last names to the number of occurrences of that last name.
     * <p>
     * Internally this is a {@link HashMap}. It only returns at most the top 20 names. Since the entire name is stored
     * in the database as a single field, a regular expression is used in a view inside the database itself to count the
     * last names.
     * <p>
     * The select statement defining the view appears as follows:
     * <blockquote><pre>{@code
     * SELECT COUNT(LAST_NAME) COUNT, LAST_NAME
     * FROM (SELECT REGEXP_SUBSTR(NAME,'\w+$') AS LAST_NAME FROM PERSON)
     * GROUP BY LAST_NAME
     * ORDER BY COUNT DESC;
     * }</pre></blockquote>
     *
     * @return a map of last names to the number of occurrences of that last name
     */
    public Map<String, Integer> lastNameFrequency()
    {
        return nameFrequency("LAST");
    }

    private Map<String, Integer> nameFrequency(String lastOrFirst)
    {
        Map<String, Integer> map = new HashMap<>();

        String query = "SELECT * FROM " + lastOrFirst + "_NAME_COUNT_VIEW";

        List<Object[]> objects = em.createNativeQuery(query).setMaxResults(20).getResultList();

        for ( Object[] o : objects )
        {
            String name = o[1].toString();
            Integer count = ( (Number) o[0] ).intValue();
            map.put(name, count);
        }

        return map;
    }
}
