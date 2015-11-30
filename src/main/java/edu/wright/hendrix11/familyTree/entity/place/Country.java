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
@DiscriminatorValue(value = "country")
@NamedQueries({
                      @NamedQuery(name = Country.FIND_BY_NAME, query = "SELECT p FROM Country p WHERE p.name = :name"),
                      @NamedQuery(name = Country.FIND_ALL, query = "SELECT p FROM Country p")
              })
public class Country extends Place
{
    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery} to get all
     * counties.
     * <p>
     * For example: {@code TypedQuery<County> query = em.createNamedQuery(County.FIND_ALL, County.class);}
     */
    public static final String FIND_ALL = "Country.findAll";

    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery} to get all
     * counties by name.
     * <p>
     * For example: {@code TypedQuery<Country> query = em.createNamedQuery(Country.FIND_BY_NAME, Country.class);} {@code
     * query..setParameter("name", name);
     */
    public static final String FIND_BY_NAME = "Country.findByName";

    /**
     * No-argument constructor for JPA.
     */
    public Country()
    {
    }

    /**
     * @param name the name of the country
     */
    public Country(String name)
    {
        super(name);
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
