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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Joe Hendrix
 */
@Stateless
public class CorrelationDataBean
{
    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    public Map<Integer, Integer> ageToBirthYear()
    {
        Query query = em.createNativeQuery("SELECT * FROM AGE_TO_BIRTH_YEAR_VIEW");

        List<Number[]> list = query.getResultList();

        Map<Integer, Integer> map = new HashMap<>();

        for ( Number[] number : list )
        {
            map.put(number[0].intValue(), number[1].intValue());
        }

        return map;
    }
}
