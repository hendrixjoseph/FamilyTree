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

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author Joe Hendrix
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.INTEGER)
@NamedQueries({
                      @NamedQuery(name = Place.FIND_BY_NAME, query = "SELECT p FROM Place p WHERE p.name = :name"),
                      @NamedQuery(name = Place.FIND_ALL, query = "SELECT p FROM Place p")
              })
public abstract class Place
{

    public static final String FIND_ALL = "Place.findAll";
    public static final String FIND_BY_NAME = "Place.findByName";

    @Id
    @SequenceGenerator(name = "PLACE_SEQUENCE", sequenceName = "PLACE_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "PLACE_SEQUENCE")
    private int id;
    @Column(unique = true, nullable = false)
    private String name;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public abstract String getLink();

    protected String toString(Place[] places)
    {
        boolean first = true;
        StringBuilder sb = new StringBuilder();

        for ( Place place : places )
        {
            if(place != null)
            {
                if ( !first )
                    sb.append(", ");

                sb.append(place.getName());
                first = false;
            }
        }

        return sb.toString();
    }
}
