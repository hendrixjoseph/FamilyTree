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

package edu.wright.hendrix11.svg.jsf.barChart;

import edu.wright.hendrix11.svg.jsf.GenericModel;

import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @param <N>
 *
 * @author Joe Hendrix
 */
public abstract class BarChartModel<N extends Number & Comparable<N>> extends GenericModel
{
    private static final Logger LOG = Logger.getLogger(BarChartModel.class.getName());

    public abstract Map<String, ?> getData();

    /**
     * @param <N>
     * @param label
     *
     * @return
     */
    public abstract <N> N getData(String label);

    /**
     * @return
     */
    public abstract Set<String> getAxisLabels();

    /**
     * @return
     */
    public Integer getNumLabels()
    {
        return getAxisLabels().size();
    }

    /**
     * @return
     */
    public abstract Integer getNumValues();

    /**
     * @return
     */
    public abstract N getMax();
}
