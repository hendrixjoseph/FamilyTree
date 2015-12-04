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

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @param <E> a class that has an {@link Entity @Entity} annotation
 * @param <K> the key for that class, which will have the {@link Id @Id} annotation in the associated {@link Entity
 *            @Entity} class
 *
 * @author Joe Hendrix
 */
@Stateless
public class DataBean<E, K>
{
    private static final Logger LOG = Logger.getLogger(DataBean.class.getName());

    /**
     *
     */
    public static final int RECORDS_PER_PAGE = 50;

    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;
    private Class<E> clazz;
    private String findAllQuery;
    private int page = 1;
    private String sort;

    /**
     * This method should be called before any other method is called. The reason this method is provided rather than
     * simply using a constructor is that JPA will inject it into another class via the {@link EJB @EJB} annotation
     * using a no-argument constructor.
     * <p>
     * The {@link Class} object must be the same type specified by E. This class must have both an {@link Entity
     * @Entity} annotation and field {@code public static final String FIND_ALL}
     *
     * @param clazz a {@link Class} object of the same type specified by E
     *
     * @throws IllegalArgumentException if the class clazz does not have an {@link Entity @Entity} annotation or field
     *                                  {@code public static final String FIND_ALL}
     */
    public void initialize(Class<E> clazz)
    {
        if ( !clazz.isAnnotationPresent(Entity.class) )
        {
            throw new IllegalArgumentException(clazz.getName() + " does not have an @Entity annotation!");
        }

        try
        {
            findAllQuery = clazz.getField("FIND_ALL").get(null).toString();

            this.clazz = clazz;
        }
        catch ( IllegalAccessException | NoSuchFieldException e )
        {
            throw new IllegalArgumentException(clazz.getName() + " does not have field \"public static final String FIND_ALL\"!");
        }
    }

    /**
     * @param em
     * @param clazz
     */
    protected void initialize(EntityManager em, Class<E> clazz)
    {
        initialize(clazz);
        this.em = em;
    }

    /**
     * Returns the current sort String.
     * <p>
     * The sort string is actually a query of sorts (pun intended?) where, when {@link #list} is called, is sorted in a
     * way where the string is seperated into a string array by the period (".") character, and then the query result is
     * sorted by the first element of the string array, then the next element, and so on.
     *
     * @return the current sort String
     */
    public String getSort()
    {
        return sort;
    }

    /**
     * Sets the sort String.
     * <p>
     * The sort string is actually a query of sorts (pun intended?) where, when {@link #list} is called, is sorted in a
     * way where the string is seperated into a string array by the period (".") character, and then the query result is
     * sorted by the first element of the string array, then the next element, and so on.
     *
     * @param sort the sort String
     */
    public void setSort(String sort)
    {
        this.sort = sort;
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
     * Returns the number of pages available. This is calculated by taking the ceiling os the size of the result list
     * divided by {@code RECORDS_PER_PAGE}, where {@code RECORDS_PER_PAGE} is currently set to {@value
     * #RECORDS_PER_PAGE}.
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
     * Returns a list of the entities. This list is at most the size of {@code RECORDS_PER_PAGE}, which is currently set
     * to {@value #RECORDS_PER_PAGE}.
     *
     * @return a list of entities
     */
    public List<E> list()
    {
        if ( sort == null )
        {
            TypedQuery<E> query = em.createNamedQuery(findAllQuery, clazz);
            return query.setFirstResult(( page - 1 ) * RECORDS_PER_PAGE).setMaxResults(RECORDS_PER_PAGE).getResultList();
        }
        else
        {
            try
            {
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<E> q = cb.createQuery(clazz);
                Root<E> root = q.from(clazz);

                List<Order> order = new ArrayList<>();

                if ( "date".equals(sort) )
                {
                    order.addAll(dateOrder(cb, root));
                }
                else
                {
                    String[] sorts = sort.split("\\.");

                    Path<Object> path = root.get(sorts[0]);

                    for ( int i = 1; i < sorts.length; i++ )
                    {
                        if ( "date".equals(sorts[i]) )
                        {
                            order.addAll(dateOrder(cb, root));
                            break;
                        }
                        else
                        {
                            path = path.get(sorts[i]);
                        }
                    }

                    order.add(0, cb.asc(path));
                }

                q.select(root).orderBy(order);

                TypedQuery<E> query = em.createQuery(q);
                return query.setFirstResult(( page - 1 ) * RECORDS_PER_PAGE).setMaxResults(RECORDS_PER_PAGE).getResultList();
            }
            catch ( Exception e )
            {
                for ( Throwable cause = e; cause != null; cause = cause.getCause() )
                {
                    if ( cause instanceof IllegalArgumentException )
                    {
                        LOG.log(Level.SEVERE, e.getMessage());

                        sort = null;
                        return list();
                    }
                }

                throw e;
            }
        }
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

    private List<Order> dateOrder(CriteriaBuilder cb, Root<E> root)
    {
        List<Order> order = new ArrayList<>();

        order.add(cb.asc(root.get("year")));
        order.add(cb.asc(root.get("month")));
        order.add(cb.asc(root.get("day")));

        return order;
    }
}
