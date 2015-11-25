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

import edu.wright.hendrix11.c3.ChartModel;
import edu.wright.hendrix11.c3.axis.XLabel;
import edu.wright.hendrix11.c3.axis.YLabel;
import edu.wright.hendrix11.familyTree.dataBean.mining.AgesDataBean;

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
    @EJB
    private AgesDataBean dataBean;
    private ChartModel ageModel = new ChartModel();
    private ChartModel meanMedianAgeModel = new ChartModel();

    @PostConstruct
    private void initialize()
    {
        ageModel.setData(dataBean.ages());
        ageModel.setxAxis("years");
        ageModel.getxAxis().getLabel().setPosition(XLabel.Position.outer_center);

        ageModel.setyAxis("people");
        ageModel.getyAxis().getLabel().setPosition(YLabel.Position.outer_middle);

        meanMedianAgeModel.setArrayData(dataBean.meanMeadianAgesPerBirthYear());

        meanMedianAgeModel.setxAxis("year");
        meanMedianAgeModel.getxAxis().getLabel().setPosition(XLabel.Position.outer_center);

        meanMedianAgeModel.setyAxis("age");
        meanMedianAgeModel.getyAxis().getLabel().setPosition(YLabel.Position.outer_middle);

        meanMedianAgeModel.setBarLabels(new String[]{"mean age", "median age"});
    }

    public ChartModel getMeanMedianAgeModel()
    {
        return meanMedianAgeModel;
    }

    /**
     * @return
     */
    public ChartModel getAgeModel()
    {
        return ageModel;
    }

    /**
     * @return
     */
    public double getAverageAge()
    {
        return dataBean.averageAge();
    }

    /**
     * @return
     */
    public int getMaxAge()
    {
        return dataBean.maxAge();
    }

    /**
     * @return
     */
    public double getMedianAge()
    {
        return dataBean.medianAge();
    }

    /**
     * @return
     */
    public int getMinAge()
    {
        return dataBean.minAge();
    }

    /**
     * @return
     */
    public int getAgeQ1()
    {
        return dataBean.ageQuartile(1);
    }

    /**
     * @return
     */
    public int getAgeQ3()
    {
        return dataBean.ageQuartile(3);
    }
}
