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
 * @author Joe Hendrix
 */
@Stateless
public class BirthsAndDeathsDataBean
{
    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

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

    /**
     * @return
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
