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

package edu.wright.hendrix11.familyTree.bean.event;

import edu.wright.hendrix11.familyTree.bean.AbstractBean;
import edu.wright.hendrix11.familyTree.dataBean.DataBean;
import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.entity.event.SinglePersonEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;

/**
 * @author Joe
 */
@Named
@ViewScoped
public class SinglePersonEventBean extends AbstractBean<SinglePersonEvent> implements Serializable
{
    @EJB
    private DataBean<SinglePersonEvent, Person> eventDataBean;

    @Override
    @PostConstruct
    protected void initialize()
    {
        eventDataBean.initialize(SinglePersonEvent.class);
        super.initialize(eventDataBean);
    }
}
