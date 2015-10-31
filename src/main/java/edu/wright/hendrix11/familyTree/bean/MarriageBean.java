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

import edu.wright.hendrix11.familyTree.dataBean.MarriageDataBean;
import edu.wright.hendrix11.familyTree.entity.event.Marriage;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class MarriageBean extends AbstractBean<Marriage> implements Serializable
{
    @EJB
    MarriageDataBean marriageDataBean;

    @Override
    @PostConstruct
    protected void initialize()
    {
        super.initialize(marriageDataBean);
    }
}