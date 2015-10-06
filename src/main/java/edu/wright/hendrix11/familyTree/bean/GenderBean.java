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

import edu.wright.hendrix11.familyTree.entity.*;

import javax.annotation.*;
import javax.enterprise.context.*;
import javax.inject.*;
import javax.persistence.*;
import java.io.*;
import java.util.*;

/**
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Named
@SessionScoped
public class GenderBean implements Serializable
{

    private List<Gender> genders;

    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        Query query = em.createNamedQuery(Gender.FIND_ALL);
        genders = query.getResultList();
    }

    public List<Gender> getGenders()
    {
        return genders;
    }

    public void setGenders(List<Gender> genders)
    {
        this.genders = genders;
    }
}
