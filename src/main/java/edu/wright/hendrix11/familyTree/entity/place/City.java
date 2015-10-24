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

    public static final String FIND_ALL = "City.findAll";
    public static final String FIND_BY_NAME = "City.findByName";

    @Override
    public String getLink()
    {
        return null;
    }
}
