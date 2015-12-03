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

import edu.wright.hendrix11.familyTree.dataBean.DataBean;

import java.util.List;

/**
 * @param <E>
 *
 * @author Joe Hendrix
 */
public abstract class AbstractBean<E>
{

    private DataBean<E, ?> dataBean;

    /**
     *
     */
    protected abstract void initialize();

    /**
     * @param dataBean
     */
    protected void initialize(DataBean<E, ?> dataBean)
    {
        this.dataBean = dataBean;
        dataBean.setPage(1);
        dataBean.setSort(null);
    }

    /**
     * Returns the list of entities. These entities are specified by the type E.
     *
     * @return the list of entities
     *
     * @see DataBean#list
     */
    public List<E> getEntities()
    {
        return dataBean.list();
    }

    /**
     * Returns the current page number.
     *
     * @return the current page number
     *
     * @see DataBean#getPage
     */
    public int getPage()
    {
        return dataBean.getPage();
    }

    /**
     * Sets the current page number.
     *
     * @param page the current page number
     *
     * @see DataBean#setPage
     */
    public void setPage(int page)
    {
        dataBean.setPage(page);
    }

    /**
     * Returns the current sort String.
     * <p>
     * The sort string is actually a query of sorts (pun intended?) where, when {@link DataBean#list} is called, is sorted in a
     * way where the string is seperated into a string array by the period (".") character, and then the query result is
     * sorted by the first element of the string array, then the next element, and so on.
     *
     * @return the current sort String
     * 
     * @see DataBean#getSort
     */
    public String getSort()
    {
        return dataBean.getSort();
    }

    /**
     * Sets the sort String.
     * <p>
     * The sort string is actually a query of sorts (pun intended?) where, when {@link DataBean#list} is called, is sorted in a
     * way where the string is seperated into a string array by the period (".") character, and then the query result is
     * sorted by the first element of the string array, then the next element, and so on.
     *
     * @param sort the sort String
     * 
     * @see DataBean#setSort
     */
    public void setSort(String sort)
    {
        dataBean.setSort(sort);
    }

    /**
     * Returns the total number of pages.
     *
     * @return the total number of pages
     *
     * @see DataBean#getNumPages
     */
    public int getNumPages()
    {
        return dataBean.getNumPages();
    }
}
