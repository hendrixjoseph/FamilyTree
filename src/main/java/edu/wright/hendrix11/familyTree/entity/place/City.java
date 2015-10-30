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
 * @author Joe Hendrix
 */
@Entity
@DiscriminatorValue(value = "4")
@NamedQueries({
                      @NamedQuery(name = City.FIND_BY_NAME, query = "SELECT p FROM City p WHERE p.name = :name"),
                      @NamedQuery(name = City.FIND_ALL, query = "SELECT p FROM City p")
              })
public class City extends Place
{
    /**
     * Specifies the {@link String} that represents the {@link javax.persistence.NamedQuery} to create a {@link
     * javax.persistence.TypedQuery} to get all cities.
     */
    public static final String FIND_ALL = "City.findAll";

    /**
     * Specifies the {@link String} that represents the {@link javax.persistence.NamedQuery} to create a {@link
     * javax.persistence.TypedQuery} to get all states by name.
     */
    public static final String FIND_BY_NAME = "City.findByName";

    /**
     * Returns the country that the city is in.
     * 
     * @return the country that the city is in
     */
    public Country getCountry()
    {
        return (Country) getRegionByClass(Country.class);
    }

    /**
     * Returns the county that the city is in.
     * 
     * @return the county that the city is in
     */
    public County getCounty()
    {
        return (County) getRegionByClass(County.class);
    }

    /**
     * Returns the state that the city is in.
     * 
     * @return the state that the city is in
     */
    public State getState()
    {
        return (State) getRegionByClass(State.class);
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@inheritDoc}
     */
    @Override
    public String getLink()
    {
        StringBuilder sb = new StringBuilder(getName());

        State state = getState();

        if ( state != null )
        {
            sb.append(", ").append(state.getName());
        }
        else
        {
            Country country = getCountry();

            if ( country != null )
                sb.append(", ").append(country.getName());
        }

        return sb.toString();
    }
}
