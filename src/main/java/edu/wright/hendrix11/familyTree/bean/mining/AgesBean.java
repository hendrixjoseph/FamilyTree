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

import edu.wright.hendrix11.c3.Axis.Axis;
import edu.wright.hendrix11.c3.ChartModel;
import edu.wright.hendrix11.familyTree.dataBean.mining.AgesDataBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;

import static edu.wright.hendrix11.c3.Axis.Axis.*;

/**
 * @author Joe Hendrix
 */
@Named
@ViewScoped
public class AgesBean implements Serializable
{
    ChartModel ageModel;

    @EJB
    AgesDataBean dataBean;

    @PostConstruct
    private void initialize()
    {
        ageModel = new ChartModel();
        ageModel.setData(dataBean.ages());
        ageModel.setTitle("Ages");

        Axis xAxis = new Axis(Which.x);
        xAxis.setLabel(new Axis.Label("people"));


        Axis yAxis = new Axis(Which.y);

        ageModel.setyAxisLabel("people");
        ageModel.setxAxisLabel("years");
    }

    /**
     * @return
     */
    public ChartModel getAgeModel()
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
