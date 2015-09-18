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
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@ManagedBean
@ViewScoped
public class DetachQueryBean extends QueryBean implements Serializable
{

    @ManagedProperty(value = "#{individualBean.person}")
    private PersonView person;

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {

    }

    /**
     *
     * @param actionEvent
     */
    @Override
    public void commit(ActionEvent actionEvent)
    {

    }

    /**
     *
     * @return
     */
    @Override
    public String getAction()
    {
        return getAction(person);
    }
}
