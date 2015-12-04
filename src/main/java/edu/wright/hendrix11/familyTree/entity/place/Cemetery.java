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
@DiscriminatorValue(value = "cemetery")
@NamedQueries({
                      @NamedQuery(name = Cemetery.FIND_BY_NAME_AND_REGION,
                                  query = "SELECT p FROM Cemetery p WHERE p.name = :name AND p.region = :region"),
                      @NamedQuery(name = Cemetery.FIND_BY_NAME,
                                  query = "SELECT p FROM Cemetery p WHERE p.name = :name"),
                      @NamedQuery(name = Cemetery.FIND_ALL, query = "SELECT p FROM Cemetery p")
              })
public class Cemetery extends Place
{
    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery} to get all
     * cemeteries.
     * <p>
     * For example:
     * <blockquote><pre>{@code TypedQuery<Cemetery> query = em.createNamedQuery(Cemetery.FIND_ALL,
     * Cemetery.class);}</pre></blockquote>
     */
    public static final String FIND_ALL = "Cemetery.findAll";

    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery} to get all
     * cemeteries by name.
     * <p>
     * For example:
     * <blockquote><pre>{@code TypedQuery<Cemetery> query = em.createNamedQuery(Cemetery.FIND_BY_NAME, Cemetery.class);
     * query.setParameter("name", name);}</pre></blockquote>
     */
    public static final String FIND_BY_NAME = "Cemetery.findByName";

    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery} to get all
     * cemeteries by name and region.
     * <p>
     * For example:
     * <blockquote><pre>{@code TypedQuery<Cemetery> query = em.createNamedQuery(Cemetery.FIND_BY_NAME_AND_REGION,
     * Cemetery.class);
     * query.setParameter("name", name);
     * query.setParameter("region", region);}</pre></blockquote>
     */
    public static final String FIND_BY_NAME_AND_REGION = "Cemetery.findByNameAndRegion";

    /**
     * No-argument constructor for JPA.
     */
    public Cemetery()
    {
    }

    /**
     * Creates a new cemetery with the specified name.
     *
     * @param name the name of the cemetery
     */
    public Cemetery(String name)
    {
        super(name);
    }

    /**
     * Returns the city that the cemetery is in.
     *
     * @return the city that the cemetery is in
     */
    public City getCity()
    {
        return (City) getRegionByClass(City.class);
    }

    /**
     * Returns the county that the cemetery is in.
     *
     * @return the county that the cemetery is in
     */
    public County getCounty()
    {
        return (County) getRegionByClass(County.class);
    }

    /**
     * Returns the country that the cemetery is in.
     *
     * @return the country that the cemetery is in
     */
    public Country getCountry()
    {
        return (Country) getRegionByClass(Country.class);
    }

    /**
     * Returns the state that the cemetery is in.
     *
     * @return the state that the cemetery is in
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
        return super.queryLink(toString());
    }
}
