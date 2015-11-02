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
import javax.persistence.TypedQuery;

/**
 * @author Joe
 */
@Entity
@DiscriminatorValue(value = "county")
@NamedQueries({
                      @NamedQuery(name = County.FIND_BY_NAME, query = "SELECT p FROM County p WHERE p.name = :name"),
                      @NamedQuery(name = County.FIND_ALL, query = "SELECT p FROM County p")
              })
public class County extends Place
{
    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery} to get all
     * counties.
     * 
     * For example: {@code TypedQuery<County> query = em.createNamedQuery(County.FIND_ALL, County.class);}
     */
    public static final String FIND_ALL = "County.findAll";

    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery} to get all
     * counties by name.
     */
    public static final String FIND_BY_NAME = "County.findByName";

    /**
     * Returns the state that the county is in.
     *
     * @return the state that the county is in
     */
    public State getState()
    {
        return (State) getRegionByClass(State.class);
    }

    /**
     * Returns the country that the county is in.
     *
     * @return the country that the county is in
     */
    public Country getCountry()
    {
        return (Country) getRegionByClass(Country.class);
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
        Country country = getCountry();

        if ( state != null )
        {
            sb.append(", ").append(state.getName());
        }
        else if ( country != null )
        {
            sb.append(", ").append(country.getName());
        }

        return mapLink(sb.toString());
    }
}
