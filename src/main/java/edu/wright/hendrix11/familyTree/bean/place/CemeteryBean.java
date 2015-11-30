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

package edu.wright.hendrix11.familyTree.bean.place;

import edu.wright.hendrix11.familyTree.bean.AbstractBean;
import edu.wright.hendrix11.familyTree.dataBean.DataBean;

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
public class CemeteryBean extends AbstractBean<Cemetery> implements Serializable
{
    @EJB
    private DataBean<Cemetery, Integer> cemeteryDataBean;

    /**
     *
     */
    @Override
    @PostConstruct
    protected void initialize()
    {
        cemeteryDataBean.initialize(Cemetery.class);
        super.initialize(cemeteryDataBean);
    }
}
