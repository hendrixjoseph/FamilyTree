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

import edu.wright.hendrix11.familyTree.entity.Person;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
@Named
@ViewScoped
public class InsertUpdatePersonBean implements Serializable
{

    private static final Logger LOG = Logger.getLogger(InsertUpdatePersonBean.class.getName());
    
    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;
    private Person person;
    private Date birthDate;
    private Date deathDate;
    private String birthPlace;
    private String deathPlace;
    
    @Inject
    private IndividualBean individualBean;
    
    @PostConstruct
    public void initialize()
    {
    }
    
    public void insertPerson()
    {
    }
    
    public void updatePerson()
    {
    }
    
  }
