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
@DiscriminatorValue(value = "state")
@NamedQueries({
                      @NamedQuery(name = State.FIND_BY_NAME_AND_REGION,
                                  query = "SELECT p FROM State p WHERE p.name = :name AND p.region = :region"),
                      @NamedQuery(name = State.FIND_BY_NAME, query = "SELECT p FROM State p WHERE p.name = :name"),
                      @NamedQuery(name = State.FIND_ALL, query = "SELECT p FROM State p")
              })
public class State extends Place
{
    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link
     * javax.persistence.TypedQuery} to get all states.
     * <p>
     * For example: {@code TypedQuery<State> query = em.createNamedQuery(State.FIND_ALL, State.class);}
     */
    public static final String FIND_ALL = "State.findAll";

    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link
     * javax.persistence.TypedQuery} to get all states by name. It is expected, but not required, that only one state
     * exists per name.
     * <p>
     * For example: {@code TypedQuery<State> query = em.createNamedQuery(State.FIND_BY_NAME, State.class);} {@code
     * query..setParameter("name", name);
     */
    public static final String FIND_BY_NAME = "State.findByName";

    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link
     * javax.persistence.TypedQuery} to get all states by name. It is expected, but not required, that only one state
     * exists per name and region.
     */
    public static final String FIND_BY_NAME_AND_REGION = "State.findByNameAndRegion";

    /**
     *
     */
    public State()
    {
    }

    /**
     *
     * @param name
     */
    public State(String name)
    {
        super(name);
    }

    /**
     * Returns the country that the state is in. Since most, if not all, states are US states, this will most likely
     * return USA.
     *
     * @return the country that the state is in
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
        return mapLink(getName());
    }
}
