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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author Joe Hendrix
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@NamedQueries({
                      @NamedQuery(name = Place.FIND_BY_NAME, query = "SELECT p FROM Place p WHERE p.name = :name"),
                      @NamedQuery(name = Place.FIND_ALL, query = "SELECT p FROM Place p")
              })
public abstract class Place implements Iterable<Place>
{
    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery} to get all
     * places.
     * <p>
     * For example: {@code TypedQuery<Place> query = em.createNamedQuery(Place.FIND_ALL, Place.class);}
     */
    public static final String FIND_ALL = "Place.findAll";

    /**
     * Specifies the {@link String} that represents the {@link NamedQuery} to create a {@link TypedQuery} to get all
     * places by name.
     * <p>
     * For example: {@code TypedQuery<Place> query = em.createNamedQuery(Place.FIND_BY_NAME, Place.class);} {@code
     * query..setParameter("name", name);
     */
    public static final String FIND_BY_NAME = "Place.findByName";

    @Id
    @SequenceGenerator(name = "PLACE_SEQUENCE", sequenceName = "PLACE_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "PLACE_SEQUENCE")
    private int id;
    @Column(unique = true, nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "REGION",
               joinColumns = @JoinColumn(name = "PLACE_ID"),
               inverseJoinColumns = @JoinColumn(name = "ID"))
    private Place region;

    /**
     * Return the unique identifier or primary key of the place. This value is autogenerated by the database.
     *
     * @return the unique identifier
     */
    public int getId()
    {
        return id;
    }

    /**
     * Sets the unique identifier or primary key of the place. This value is autogenerated by the database.
     *
     * @param id the unique identifier
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Returns the name of the place. For instance, "Dayton", "Ohio", or "Germany".
     *
     * @return the name of the place
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the place. For instance, "Dayton", "Ohio", or "Germany".
     *
     * @param name the name of the place
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Return the region of the place. The region is defined as a more general place. The chain of regions goes from
     * {@link City} &#8594; {@link County} &#8594; {@link State} &#8594; {@link Country}. A {@link City}'s region does
     * not have to be a {@link County}, it could also be a {@link State} or {@link Country}.
     *
     * @return the region of the place
     */
    public Place getRegion()
    {
        return region;
    }

    /**
     * Sets the region of the place. The region is defined as a more general place. The chain of regions goes from
     * {@link City} &#8594; {@link County} &#8594; {@link State} &#8594; {@link Country}. A {@link City}'s region does
     * not have to be a {@link County}, it could also be a {@link State} or {@link Country}.
     *
     * @param region the region of the place
     */
    public void setRegion(Place region)
    {
        this.region = region;
    }

    /**
     * Returns a list containing this place and all regions it is in.
     *
     * @return a list of place
     */
    public List<Place> getPlaces()
    {
        List<Place> places = new ArrayList<>();

        Place place = this;

        while ( place != null )
        {
            places.add(place);
            place = place.getRegion();
        }

        return Collections.unmodifiableList(places);
    }

    /**
     * Returns an iterator containing this place and all regions it is in. This is just the iterator returned by {@link
     * #getPlaces}.
     *
     * @return a list of place
     */
    @Override
    public Iterator<Place> iterator()
    {
        return getPlaces().iterator();
    }

    /**
     * Retruns  URL String, typically either a Google query or Google map link. A Google query follows the format {@code
     * "https://www.google.com/search?q="} while a Google map link follows the format {@code
     * "https://www.google.com/maps/place/"}.
     *
     * @return a URL String, typically either a Google query or Google map link.
     */
    public abstract String getLink();

    /**
     * @param clazz
     *
     * @return
     */
    protected Place getRegionByClass(Class<? extends Place> clazz)
    {
        for ( Place region : this )
        {
            if ( clazz.equals(region.getClass()) )
                return region;
        }

        return null;
    }

    /**
     * @param string
     *
     * @return
     */
    protected String mapLink(String string)
    {
        return "https://www.google.com/maps/place/" + string;
    }

    /**
     * @param string
     *
     * @return
     */
    protected String queryLink(String string)
    {
        return "https://www.google.com/search?q=" + string;
    }

    /**
     * Returns a string representation of the place. This is the "fully qualified" name, which is a concatination of
     * this place's name and the name of all regions this place is in, seperated by commas. For example, "Dayton, Ohio,
     * USA".
     *
     * @return A string representation of the person, which is just the person's name.
     *
     * @see #getName()
     * @see #getRegion
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for ( Place region : this )
        {
            sb.append(region.getName()).append(", ");
        }

        sb.setLength(sb.length() - 2);

        return sb.toString();
    }
}
