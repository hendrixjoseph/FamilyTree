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

import edu.wright.hendrix11.familyTree.entity.Place;
import edu.wright.hendrix11.util.DataGatherer;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.io.Serializable;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Named
@ViewScoped
public class PlaceBean extends AbstractDataBean<Place> implements Serializable
{

    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    /**
     *
     */
    @Override
    @PostConstruct
    public void initialize()
    {
        dataGatherer = new DataGatherer<>(em, Place.class);
        setPage(1);
    }
}
