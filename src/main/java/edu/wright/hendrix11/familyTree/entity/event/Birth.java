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

package edu.wright.hendrix11.familyTree.entity.event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Entity
@DiscriminatorValue(value = "birth")
@NamedQuery(name = Birth.FIND_ALL, query = "SELECT b FROM Birth b")
public class Birth extends SinglePersonEvent
{
    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery} to get all
     * birth records.
     * <p>
     * For example: {@code TypedQuery<Birth> query = em.createNamedQuery(Birth.FIND_ALL, Birth.class);}
     */
    public static final String FIND_ALL = "Birth.findAll";
}
