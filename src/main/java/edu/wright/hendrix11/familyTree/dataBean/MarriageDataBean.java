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

package edu.wright.hendrix11.familyTree.dataBean;

import edu.wright.hendrix11.familyTree.entity.compositeKey.MarriagePK;
import edu.wright.hendrix11.familyTree.entity.event.Marriage;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author Joe Hendrix
 */
@Stateless
public class MarriageDataBean extends AbstractDataBean<Marriage, MarriagePK>
{
    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    @Override
    @PostConstruct
    public void initialize()
    {
        initialize(em, Marriage.class);
    }
}