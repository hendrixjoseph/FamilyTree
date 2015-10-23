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

import edu.wright.hendrix11.familyTree.dataBean.AbstractDataBean;

import java.util.List;

/**
 * @author Joe Hendrix
 */
public abstract class AbstractBean<E>
{

    private AbstractDataBean<E, ?> dataBean;

    public abstract void initialize();

    protected void initialize(AbstractDataBean<E, ?> dataBean)
    {
        this.dataBean = dataBean;
    }

    public List<E> getEntities()
    {
        return dataBean.list();
    }

    public int getPage()
    {
        return dataBean.getPage();
    }

    public void setPage(int page)
    {
        dataBean.setPage(page);
    }

    public int getNumPages()
    {
        return dataBean.getNumPages();
    }
}
