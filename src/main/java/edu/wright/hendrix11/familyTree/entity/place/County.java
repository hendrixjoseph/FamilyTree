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

import javax.persistence.CascadeType;
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

    public Country getCountry()
    {
        return getRegionByClass(Country.class);
    }

    public State getState()
    {
        return getRegionByClass(State.class);
    }

    @Override
    public String getLink()
    {
      StringBuilder sb = new StringBuilder(name);
      
      State state = getState();
      
      if(state != null)
      {
        sb.append(", ").append(state.getName());
      }

      return sb.toString();
    }
}
