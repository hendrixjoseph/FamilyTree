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

import edu.wright.hendrix11.familyTree.dataBean.GenderDataBean;
import edu.wright.hendrix11.familyTree.entity.Gender;

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
public class GenderBean extends AbstractBean<Gender> implements Serializable
{

    @EJB
    GenderDataBean genderDataBean;

    /**
     *
     */
    @Override
    @PostConstruct
    public void initialize()
    {
        super.initialize(genderDataBean);
    }
}