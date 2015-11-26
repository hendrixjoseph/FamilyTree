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

package edu.wright.hendrix11.familyTree.bean.mining;

import edu.wright.hendrix11.familyTree.dataBean.mining.NamesDataBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
@Named
@ViewScoped
public class NamesBean implements Serializable
{
    private static Logger LOG = Logger.getLogger(NamesBean.class.getName());

    @EJB
    NamesDataBean dataBean;
    Map<String, Integer> firstNameFrequency;
    Map<String, Integer> lastNameFrequency;

    @PostConstruct
    private void initialize()
    {
        firstNameFrequency = dataBean.firstNameFrequency();
        lastNameFrequency = dataBean.lastNameFrequency();
    }

    public Map<String, Integer> getFirstNameFrequency()
    {
        return firstNameFrequency;
    }

    public Map<String, Integer> getLastNameFrequency()
    {
        return lastNameFrequency;
    }
}
