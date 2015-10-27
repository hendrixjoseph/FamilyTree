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

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Entity
@NamedQuery(name = Birth.FIND_ALL, query = "SELECT b FROM Birth b")
public class Birth extends SinglePersonEvent
{
    public static final String FIND_ALL = "Birth.findAll";
}