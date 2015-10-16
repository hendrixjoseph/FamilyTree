/*
* The MIT License (MIT)
*
* View the full license at:
* https://github.com/hendrixjoseph/FamilyTree/blob/master/LICENSE.md
*
* Copyright (c) 2015 Joseph Hendrix
*
* Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
*
*/

package edu.wright.hendrix11.familyTree.bean;

import edu.wright.hendrix11.util.DataGatherer;

import java.util.List;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public abstract class AbstractDataBean<E>
{

    protected DataGatherer<E> dataGatherer;
    private List<E> entities;

    /**
     *
     */
    public abstract void initialize();

    public int getPage()
    {
        return dataGatherer.getPage();
    }

    public void setPage(int page)
    {
        entities = dataGatherer.getItems(page);
    }

    /**
     * @return
     */
    public List<E> getEntities()
    {
        return entities;
    }

    /**
     * @param entities
     */
    public void setEntities(List<E> entities)
    {
        this.entities = entities;
    }
}
