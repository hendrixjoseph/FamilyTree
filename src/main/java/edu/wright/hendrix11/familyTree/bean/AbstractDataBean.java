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

package edu.wright.hendrix11.familyTree.bean;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public abstract class AbstractDataBean<E>
{

    private static final int RECORDS_PER_PAGE = 50;

    private EntityManager em;
    private Class<E> clazz;
    private String findAllQuery;
    private int page;
    //    private List<E> entities;

    /**
     *
     */
    public abstract void initialize();

    protected void initialize(EntityManager em, Class<E> clazz)
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

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getNumPages()
    {
        TypedQuery<E> query = em.createNamedQuery(findAllQuery, clazz);

        double size = query.getResultList().size();
        return (int)Math.ceil(size / RECORDS_PER_PAGE);
    }

    /**
     * @return
     */
    public List<E> getEntities()
    {
        TypedQuery<E> query = em.createNamedQuery(findAllQuery, clazz);
        return query.setFirstResult(( page - 1 ) * RECORDS_PER_PAGE).setMaxResults(RECORDS_PER_PAGE).getResultList();
    }
}
