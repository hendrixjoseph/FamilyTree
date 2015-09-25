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

import edu.wright.hendrix11.familyTree.entity.Gender;
import edu.wright.hendrix11.familyTree.entity.manager.EntityManagerInjector;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Named
@SessionScoped
public class GenderBean implements Serializable
{

    private List<Gender> genders;

    @PersistenceContext(unitName="edu.wright.hendrix11.familyTree")
    private EntityManager entityManager;

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        // This is ugly - we'll use injection later
//        em = Persistence.createEntityManagerFactory("edu.wright.hendrix11.familyTree").createEntityManager();

        Query query = em.createQuery("SELECT e FROM Gender e");
        genders = (List<Gender>) query.getResultList();
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
