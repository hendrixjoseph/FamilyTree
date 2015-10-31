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
@DiscriminatorValue(value = "burial")
@NamedQuery(name = Burial.FIND_ALL, query = "SELECT b FROM Burial b")
public class Burial extends SinglePersonEvent
{
    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery}
     * to get all burial records.
     */
    public static final String FIND_ALL = "Burial.findAll";
}