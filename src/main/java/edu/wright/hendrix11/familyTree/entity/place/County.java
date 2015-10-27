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

@Entity
@DiscriminatorValue(value = "3")
@NamedQueries({
                      @NamedQuery(name = County.FIND_BY_NAME, query = "SELECT p FROM County p WHERE p.name = :name"),
                      @NamedQuery(name = County.FIND_ALL, query = "SELECT p FROM County p")
              })
public class County extends Place
{

    public static final String FIND_ALL = "County.findAll";
    public static final String FIND_BY_NAME = "County.findByName";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "REGION_VIEW",
               joinColumns = @JoinColumn(name = "PLACE_ID"),
               inverseJoinColumns = @JoinColumn(name = "REGION_ID"))
    private State state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "REGION_VIEW",
               joinColumns = @JoinColumn(name = "PLACE_ID"),
               inverseJoinColumns = @JoinColumn(name = "REGION_ID"))
    private Country country;

    public Country getCountry()
    {
        return country;
    }

    public void setCountry(Country country)
    {
        this.country = country;
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
        Place[] places = {this, state, country};

        return toString(places);
    }
}