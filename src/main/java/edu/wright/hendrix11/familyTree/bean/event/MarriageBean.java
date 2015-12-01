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
import edu.wright.hendrix11.familyTree.entity.compositeKey.MarriagePK;
import edu.wright.hendrix11.familyTree.entity.event.Marriage;

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
public class MarriageBean extends AbstractBean<Marriage> implements Serializable
{
    @EJB
    DataBean<Marriage, MarriagePK> marriageDataBean;

    /**
     *
     */
    @Override
    @PostConstruct
    protected void initialize()
    {
        marriageDataBean.initialize(Marriage.class);
        super.initialize(marriageDataBean);
    }
}
