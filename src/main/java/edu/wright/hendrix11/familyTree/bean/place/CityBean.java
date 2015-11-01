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
import edu.wright.hendrix11.familyTree.dataBean.place.CityDataBean;
import edu.wright.hendrix11.familyTree.entity.place.City;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class CityBean extends AbstractBean<City> implements Serializable
{
    @EJB
    CityDataBean cityDataBean;

    @Override
    @PostConstruct
    protected void initialize()
    {
        super.initialize(cityDataBean);
    }
}