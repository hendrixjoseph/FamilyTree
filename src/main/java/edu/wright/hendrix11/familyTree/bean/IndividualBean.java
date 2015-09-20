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
package edu.wright.hendrix11.familyTree.bean;

import edu.wright.hendrix11.familyTree.database.table.PersonTable;
import edu.wright.hendrix11.familyTree.entity.PersonView;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Joe Hendrix
 */
@Named
@SessionScoped
public class IndividualBean implements Serializable
{

    private Person person;

EntityManager em;

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        
    }

    /**
     *
     * @return
     */
    public PersonView getPerson()
    {
        return person;
    }

    /**
     *
     * @return
     */
    public Integer getPersonId()
    {
        return person.getId();
    }


    /**
     *
     * @param id
     */
    public void setPersonId(int id)
    {
        person = em;
    }
}
