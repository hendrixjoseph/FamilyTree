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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    /**
     *
     * @return
     */
    public Map<String, Integer> getFirstNameFrequency()
    {
        return firstNameFrequency;
    }

    /**
     *
     * @return
     */
    public Map<String, Integer> getLastNameFrequency()
    {
        return lastNameFrequency;
    }

    /**
     * Returns a list of first names to the number of occurences (frequency) of that name.
     * <p>
     * These values are first put into a {@link LinkedHashMap} then converted into a {@link List} of {@link Map.Entry}.
     * This is to allow use in PrimeFaces' datatable JSF element.
     *
     * @return a list of first names to the number of occurences (frequency) of that name
     *
     * @see NamesDataBean#firstNameFrequency
     */
    public List<Map.Entry<String, Integer>> getFirstNameFrequencyList()
    {
        return new ArrayList(firstNameFrequency.entrySet());
    }

    /**
     * Returns a list of last names to the number of occurences (frequency) of that name.
     * <p>
     * These values are last put into a {@link LinkedHashMap} then converted into a {@link List} of {@link Map.Entry}.
     * This is to allow use in PrimeFaces' datatable JSF element.
     *
     * @return a list of last names to the number of occurences (frequency) of that name
     *
     * @see NamesDataBean#lastNameFrequency
     */
    public List<Map.Entry<String, Integer>> getLastNameFrequencyList()
    {
        return new ArrayList(lastNameFrequency.entrySet());
    }
}
