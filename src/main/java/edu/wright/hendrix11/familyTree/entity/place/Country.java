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

@Entity
@DiscriminatorValue(value = "1")
@NamedQueries({
                      @NamedQuery(name = Country.FIND_BY_NAME, query = "SELECT p FROM Country p WHERE p.name = :name"),
                      @NamedQuery(name = Country.FIND_ALL, query = "SELECT p FROM Country p")
              })
public class Country extends Place
{

    public static final String FIND_ALL = "Country.findAll";
    public static final String FIND_BY_NAME = "Country.findByName";

    @Override
    public String getLink()
    {
        return null;
    }
}