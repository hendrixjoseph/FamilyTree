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

import edu.wright.hendrix11.familyTree.dataBean.PlaceDataBean;
import edu.wright.hendrix11.familyTree.entity.Place;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;

/**
 * @author Joe Hendrix
 */
@Named
@ViewScoped
public class PlaceBean extends AbstractBean<Place> implements Serializable
{

    @EJB
    PlaceDataBean placeDataBean;

    @Override
    @PostConstruct
    public void initialize()
    {
        super.initialize(placeDataBean);
    }
}