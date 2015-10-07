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

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import edu.wright.hendrix11.familyTree.entity.Gender;

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

    public List<Gender> getGenders()
    {
        return genders;
    }

    public void setGenders(List<Gender> genders)
    {
        this.genders = genders;
    }

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        TypedQuery<Gender> query = em.createNamedQuery(Gender.FIND_ALL, Gender.class);
        genders = query.getResultList();
    }
}
