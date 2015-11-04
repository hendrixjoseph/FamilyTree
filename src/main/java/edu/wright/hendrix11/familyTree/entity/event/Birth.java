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
@DiscriminatorValue(value = "birth")
@NamedQueries({
                      @NamedQuery(name = Birth.FIND_ALL, query = "SELECT b FROM Birth b"),
                      @NamedQuery(name = Birth.COUNT_BY_YEAR, query="SELECT COUNT(b.year), b.year FROM Birth b WHERE b.year IS NOT NULL GROUP BY b.year ORDER BY b.year"),
                      @NamedQuery(name = Birth.COUNT_BY_YEAR2, query="SELECT COUNT(b.year), b.year FROM Death b WHERE b.year IS NOT NULL GROUP BY b.year ORDER BY b.year")
              })
public class Birth extends SinglePersonEvent
{
    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery} to get all
     * birth records.
     * <p>
     * For example: {@code TypedQuery<Birth> query = em.createNamedQuery(Birth.FIND_ALL, Birth.class);}
     */
    public static final String FIND_ALL = "Birth.findAll";

    public static final String COUNT_BY_YEAR = "Birth.countByYear";

    public static final String COUNT_BY_YEAR2 = "Birth.countByYear2";
}
