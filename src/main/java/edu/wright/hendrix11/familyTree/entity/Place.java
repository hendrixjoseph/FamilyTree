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

package edu.wright.hendrix11.familyTree.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import static javax.persistence.GenerationType.*;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Entity
@NamedQuery(name = Place.FIND_BY_NAME, query = "SELECT p FROM Place p WHERE p.name = :name")
public class Place
{
    public static final String FIND_BY_NAME = "Place.findByName";

    @Id
    @SequenceGenerator(name = "PLACE_SEQUENCE", sequenceName = "PLACE_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "PLACE_SEQUENCE")
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    /**
     *
     * @return
     */
    public int getId()
    {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
