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
package edu.wright.hendrix11.familyTree.bean.query;

import edu.wright.hendrix11.familyTree.entity.PersonView;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public abstract class QueryBean
{

    /**
     *
     * @param actionEvent
     */
    public abstract void commit(ActionEvent actionEvent);

    /**
     *
     * @param person
     * @return
     */
    protected String getAction(PersonView person)
    {
        StringBuilder sb = new StringBuilder();

        sb.append("index?personId=");
        sb.append(person.getId());
        sb.append("&amp;").append(getRedirectAction());

        return sb.toString();
    }

    /**
     *
     * @return
     */
    protected String getRedirectAction()
    {
        return "faces-redirect=true";
    }

    /**
     *
     * @return
     */
    public abstract String getAction();
}
