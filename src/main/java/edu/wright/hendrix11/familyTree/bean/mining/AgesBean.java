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

import edu.wright.hendrix11.familyTree.dataBean.mining.AgesDataBean;
import edu.wright.hendrix11.svg.jsf.barChart.BarChartModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;

/**
 * @author Joe Hendrix
 */
@Named
@ViewScoped
public class AgesBean implements Serializable
{
    BarChartModel ageModel;

    @EJB
    AgesDataBean dataBean;

    @PostConstruct
    private void initialize()
    {
        ageModel = new BarChartModel();
        ageModel.setData(dataBean.ages());
        ageModel.setTitle("Ages");
        ageModel.setyAxisLabel("people");
        ageModel.setxAxisLabel("years");
    }

    /**
     * @return
     */
    public BarChartModel getAgeModel()
    {
        return ageModel;
    }

    public double getAverageAge()
    {
        return dataBean.averageAge();
    }

    public int getMaxAge()
    {
        return dataBean.maxAge();
    }

    public double getMedianAge()
    {
        return dataBean.medianAge();
    }

    public int getMinAge()
    {
        return dataBean.minAge();
    }

    public int getAgeQ1()
    {
        return dataBean.ageQuartile(1);
    }

    public int getAgeQ3()
    {
        return dataBean.ageQuartile(3);
    }
}
