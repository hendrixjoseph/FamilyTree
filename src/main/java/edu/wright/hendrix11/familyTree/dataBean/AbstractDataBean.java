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

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

/**
 * @param <E> a class that has an {@link javax.persistence.Entity} annotation
 * @param <K> the key for that class, will have the {@link javax.persistence.Id} 
 * annotation in the associated  {@link javax.persistence.Entity} class
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public abstract class AbstractDataBean<E, K>
{

    private static final int RECORDS_PER_PAGE = 50;

    private EntityManager em;
    private Class<E> clazz;
    private String findAllQuery;
    private int page = 1;

    protected abstract void initialize();

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

    /**
     * Returns the page number.
     * 
     * @return the page number
     */
    public int getPage()
    {
        return page;
    }

    /**
     * Sets the page number.
     * 
     * @param page the page number
     */
    public void setPage(int page)
    {
        this.page = page;
    }

    /**
     * Returns the number of pages available. This is calculated by taking the ceiling os the size of the
     * result list divided by {@code RECORDS_PER_PAGE}, where {@code RECORDS_PER_PAGE} is currently set to
     * {@value #RECORDS_PER_PAGE}.
     * 
     * @return the number of pages available
     */
    public int getNumPages()
    {
        TypedQuery<E> query = em.createNamedQuery(findAllQuery, clazz);

        double size = query.getResultList().size();
        return (int) Math.ceil(size / RECORDS_PER_PAGE);
    }

    /**
     * Returns a list of the entities. This list is at most the size of {@code RECORDS_PER_PAGE}, which is currently
     * set to {@value #RECORDS_PER_PAGE}.
     * 
     * @return a list of entities
     */
    public List<E> list()
    {
        TypedQuery<E> query = em.createNamedQuery(findAllQuery, clazz);
        return query.setFirstResult(( page - 1 ) * RECORDS_PER_PAGE).setMaxResults(RECORDS_PER_PAGE).getResultList();
    }

    /**
     * Returns the entity associated with the primary key.
     * 
     * @param key the primary key
     *
     * @return the entity with the given primary key
     */
    public E find(K key)
    {
        return em.find(clazz, key);
    }

    /**
     * @param e
     *
     * @return
     */
    public K save(E e)
    {
        return null;
    }

    /**
     * @param e
     */
    public void update(E e)
    {
        em.merge(e);
    }

    /**
     * @param e
     */
    public void delete(E e)
    {

    }
}
