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

import edu.wright.hendrix11.familyTree.entity.Person;
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
    public Person getPerson();

    /**
     *
     * @param person
     */
    public void setPerson(Person person);

    /**
     *
     * @return
     */
    public Place getPlace();

    /**
     *
     * @param place
     */
    public void setPlace(Place place);

    /**
     *
     * @return
     */
    public Date getDate();

    /**
     *
     * @param date
     */
    public void setDate(Date date);
}
