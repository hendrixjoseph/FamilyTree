/*
 *  The MIT License (MIT)
 *
 *  View the full license at:
 *  https://github.com/hendrixjoseph/FamilyTree/blob/master/LICENSE.md
 *
 *  Copyright (c) 2015 Joseph Hendrix
 *
 *  Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
 */

package edu.wright.hendrix11.familyTree.bean;

import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.util.DataGatherer;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.List;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Named
@ViewScoped
public class PersonsBean implements Serializable
{
    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;
    private DataGatherer<Person> dataGatherer;
    private List<Person> persons;

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        dataGatherer = new DataGatherer<>(em, Person.class);
        persons = dataGatherer.getItems(1);
    }

    public int getPage()
    {
        return dataGatherer.getPage();
    }

    public void setPage(int page)
    {
        persons = dataGatherer.getItems(page);
    }

    public List<Person> getPersons()
    {
        return persons;
    }

    public void setPersons(List<Person> persons)
    {
        this.persons = persons;
    }
}
