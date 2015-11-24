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

package edu.wright.hendrix11.familyTree.entity.place;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Joe
 */
@Entity
@DiscriminatorValue(value = "cemetery")
@NamedQueries({
                      @NamedQuery(name = Cemetery.FIND_BY_NAME,
                                  query = "SELECT p FROM Cemetery p WHERE p.name = :name"),
                      @NamedQuery(name = Cemetery.FIND_ALL, query = "SELECT p FROM Cemetery p")
              })
public class Cemetery extends Place
{

    /**
     *
     */
    public static final String FIND_ALL = "Cemetary.findAll";

    /**
     *
     */
    public static final String FIND_BY_NAME = "Cemetary.findByName";

    /**
     *
     */
    public Cemetery()
    {
    }

    /**
     * @param name
     */
    public Cemetery(String name)
    {
        super(name);
    }

    /**
     * Returns the city that the cemetary is in.
     *
     * @return the city that the cemetary is in
     */
    public City getCity()
    {
        return (City) getRegionByClass(City.class);
    }

    /**
     * Returns the county that the cemetary is in.
     *
     * @return the county that the cemetary is in
     */
    public County getCounty()
    {
        return (County) getRegionByClass(County.class);
    }

    /**
     * Returns the country that the cemetary is in.
     *
     * @return the country that the cemetary is in
     */
    public Country getCountry()
    {
        return (Country) getRegionByClass(Country.class);
    }

    /**
     * Returns the state that the cemetary is in.
     *
     * @return the state that the cemetary is in
     */
    public State getState()
    {
        return (State) getRegionByClass(State.class);
    }

    @Override
    public String getLink()
    {
        return super.queryLink(toString());
    }
}