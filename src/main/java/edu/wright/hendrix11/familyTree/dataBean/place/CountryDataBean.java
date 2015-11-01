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

package edu.wright.hendrix11.familyTree.dataBean.place;

import edu.wright.hendrix11.familyTree.dataBean.AbstractDataBean;
import edu.wright.hendrix11.familyTree.entity.place.Country;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Joe Hendrix
 */
@Stateless
public class CountryDataBean extends AbstractDataBean<Country, Integer>
{
    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    @Override
    @PostConstruct
    protected void initialize()
    {
        initialize(em, Country.class);
    }
}