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
 * @author Joe Hendrix
 */
@Stateless
public class AgesDataBean
{
    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    /**
     * @return
     */
    public double averageAge()
    {
        String averageQuery = "SELECT AVG(AGE) FROM AGE_VIEW";

        return ( (BigDecimal) em.createNativeQuery(averageQuery).getSingleResult() ).doubleValue();
    }

    public double medianAge()
    {
        String medianQuery = "SELECT MEDIAN(AGE) FROM AGE_VIEW";

        return ( (BigDecimal) em.createNativeQuery(medianQuery).getSingleResult() ).doubleValue();
    }

    public int minAge()
    {
        String minQuery = "SELECT MIN(AGE) FROM AGE_VIEW";

        return ( (BigDecimal) em.createNativeQuery(minQuery).getSingleResult() ).intValue();
    }

    public int maxAge()
    {
        String maxQuery = "SELECT MAX(AGE) FROM AGE_VIEW";

        return ( (BigDecimal) em.createNativeQuery(maxQuery).getSingleResult() ).intValue();
    }

    public int ageQuartile(int quartile)
    {
        StringBuilder quartileQuery = new StringBuilder("SELECT MAX(AGE) FROM");
        quartileQuery.append(" (SELECT AGE,");
        quartileQuery.append(" NTILE(4) OVER (ORDER BY AGE) AS QUARTILE");
        quartileQuery.append(" FROM AGE_VIEW)");
        quartileQuery.append(" WHERE QUARTILE=").append(quartile);

        return ( (BigDecimal) em.createNativeQuery(quartileQuery.toString()).getSingleResult() ).intValue();
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
}
