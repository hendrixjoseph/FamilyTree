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

import edu.wright.hendrix11.familyTree.dataBean.mining.CorrelationDataBean;
import edu.wright.hendrix11.svg.jsf.scatterPlot.ScatterPlotModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Joe Hendrix
 */
@Named
@ViewScoped
public class CorrelationBean implements Serializable
{
    @EJB
    CorrelationDataBean dataBean;

    ScatterPlotModel<Integer,Integer> ageToBirthModel;

    /**
     *
     */
    @PostConstruct
    protected void initialize()
    {
        ageToBirthModel.setTitle("Age / Birth Year Scatter Plot");
        ageToBirthModel.setData(dataBean.ageToBirthYear());
    }

    public ScatterPlotModel<Integer, Integer> getAgeToBirthModel()
    {
        return ageToBirthModel;
    }
}
