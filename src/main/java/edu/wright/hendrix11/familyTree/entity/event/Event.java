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

import edu.wright.hendrix11.familyTree.entity.Place;
import java.util.Date;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public interface Event
{

    /**
     *
     * @return
     */
    Place getPlace();

    /**
     *
     * @param place
     */
    void setPlace(Place place);

    /**
     *
     * @return
     */
    Date getDate();

    /**
     *
     * @param date
     */
    void setDate(Date date);
}