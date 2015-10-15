/*
* The MIT License (MIT)
*
* View the full license at:
* https://github.com/hendrixjoseph/FamilyTree/blob/master/LICENSE.md
*
* Copyright (c) 2015 Joseph Hendrix
*
* Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
*
*/

package edu.wright.hendrix11.familyTree.bean;

import edu.wright.hendrix11.familyTree.entity.Place;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Named
@SessionScoped
public class PlaceBean implements Serializable
{

    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;
    private List<Place> places;

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        TypedQuery<Place> query = em.createNamedQuery(Place.FIND_ALL, Place.class);
        places = query.getResultList();
    }

    /**
     *
     * @return
     */
    public List<Place> getPlaces()
    {
        return places;
    }

    /**
     *
     * @param places
     */
    public void setPlaces(List<Place> places)
    {
        this.places = places;
    }
}
