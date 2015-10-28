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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
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

    public static final String FIND_ALL = "City.findAll";
    public static final String FIND_BY_NAME = "City.findByName";
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "REGION_VIEW",
               joinColumns = @JoinColumn(name = "PLACE_ID"),
               inverseJoinColumns = @JoinColumn(name = "REGION_ID"))
    private Country country;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "REGION_VIEW",
               joinColumns = @JoinColumn(name = "PLACE_ID"),
               inverseJoinColumns = @JoinColumn(name = "REGION_ID"))
    private County county;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "REGION_VIEW",
               joinColumns = @JoinColumn(name = "PLACE_ID"),
               inverseJoinColumns = @JoinColumn(name = "REGION_ID"))
    private State state;

    public Country getCountry()
    {
        return country;
    }

    public void setCountry(Country country)
    {
        this.country = country;
    }

    public County getCounty()
    {
        return county;
    }

    public void setCounty(County county)
    {
        this.county = county;
    }

    public State getState()
    {
        return state;
    }

    public void setState(State state)
    {
        this.state = state;
    }

    @Override
    public String getLink()
    {
        Place[] places = {this, state};

        return toString(places);
    }

    @Override
    public String toString()
    {
        Place[] places = {this, county, state, country};

        return toString(places);
    }
}
