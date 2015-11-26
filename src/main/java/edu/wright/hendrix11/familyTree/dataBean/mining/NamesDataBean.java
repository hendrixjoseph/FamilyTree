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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Joe Hendrix
 */
@Stateless
public class NamesDataBean
{
    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    public Map<String, Integer> firstNameFrequency()
    {
        return nameFrequency("FIRST");
    }

    public Map<String, Integer> lastNameFrequency()
    {
        return nameFrequency("LAST");
    }

    private Map<String, Integer> nameFrequency(String lastOrFirst)
    {
        Map<String, Integer> map = new LinkedHashMap<>();

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
