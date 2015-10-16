/*
 *  The MIT License (MIT)
 *
 *  View the full license at:
 *  https://github.com/hendrixjoseph/FamilyTree/blob/master/LICENSE.md
 *
 *  Copyright (c) 2015 Joseph Hendrix
 *
 *  Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
 */

package edu.wright.hendrix11.util;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Joe on 10/15/2015.
 */
public class DataGatherer<O>
{

    private static final Logger LOG = Logger.getLogger(DataGatherer.class.getName());

    private static final int RECORDS_PER_PAGE = 50;

    private EntityManager em;
    private Class<O> clazz;
    private String findAllQuery;
    private int page;

    public DataGatherer(EntityManager em, Class<O> clazz)
    {
        if ( !clazz.isAnnotationPresent(Entity.class) )
        {
            throw new IllegalArgumentException(clazz.getName() + " does not have an @Entity annotation!");
        }

        try
        {
            findAllQuery = clazz.getField("FIND_ALL").get(null).toString();

            this.em = em;
            this.clazz = clazz;
        }
        catch ( IllegalAccessException | NoSuchFieldException e )
        {
            throw new IllegalArgumentException(clazz.getName() + " does not have field \"public static final String FIND_ALL\"!");
        }
    }

    public List<O> getItems(int page)
    {
        this.page = page;
        TypedQuery<O> query = em.createNamedQuery(findAllQuery, clazz);
        return query.setFirstResult(( page - 1 ) * RECORDS_PER_PAGE).setMaxResults(RECORDS_PER_PAGE).getResultList();
    }

    public int getPage()
    {
        return page;
    }
}
