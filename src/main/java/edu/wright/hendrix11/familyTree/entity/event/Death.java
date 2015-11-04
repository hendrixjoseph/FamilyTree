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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Entity
@DiscriminatorValue(value = "death")
@NamedQueries({
                      @NamedQuery(name = Death.FIND_ALL, query = "SELECT d FROM Death d"),
                      @NamedQuery(name = Death.COUNT_BY_YEAR,
                                  query = "SELECT COUNT(d.year), d.year FROM Death d WHERE d.year IS NOT NULL GROUP BY d.year ORDER BY d.year")

              })
public class Death extends SinglePersonEvent
{
    public static final String COUNT_BY_YEAR = "Death.countByYear";
    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery} to get all
     * death records.
     * <p>
     * For example: {@code TypedQuery<Death> query = em.createNamedQuery(Death.FIND_ALL, Death.class);}
     */
    public static final String FIND_ALL = "Death.findAll";
}
