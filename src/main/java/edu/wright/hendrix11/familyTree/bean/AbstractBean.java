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

    protected abstract void initialize();

    protected void initialize(DataBean<E, ?> dataBean)
    {
        this.dataBean = dataBean;
    }

    /**
     * @return
     */
    public List<E> getEntities()
    {
        return dataBean.list();
    }

    /**
     * @return
     */
    public int getPage()
    {
        return dataBean.getPage();
    }

    /**
     * @param page
     */
    public void setPage(int page)
    {
        dataBean.setPage(page);
    }

    /**
     * @return
     */
    public int getNumPages()
    {
        return dataBean.getNumPages();
    }
}
