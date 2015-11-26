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

import edu.wright.hendrix11.d3.chart.ChartModel;
import edu.wright.hendrix11.d3.chart.axis.XLabel;
import edu.wright.hendrix11.d3.chart.axis.YLabel;
import edu.wright.hendrix11.familyTree.dataBean.mining.AgesDataBean;

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
public class AgesBean implements Serializable
{
    @EJB
    private AgesDataBean dataBean;
    private ChartModel ageModel = new ChartModel();
    private ChartModel meanMedianAgeAtBirthModel;
    private ChartModel meanMedianAgeAtDeathModel;

    @PostConstruct
    private void initialize()
    {
        ageModel.setData(dataBean.ages());
        ageModel.setxAxis("years");
        ageModel.getxAxis().getLabel().setPosition(XLabel.Position.outer_center);

        ageModel.setyAxis("people");
        ageModel.getyAxis().getLabel().setPosition(YLabel.Position.outer_middle);

        meanMedianAgeAtBirthModel = generateMeanMedianAgeModel(dataBean.meanMeadianAgesPerBirthYear(), "birth");
        meanMedianAgeAtDeathModel = generateMeanMedianAgeModel(dataBean.meanMeadianAgesPerDeathYear(), "death");
    }

    public ChartModel getMeanMedianAgeAtBirthModel()
    {
        return meanMedianAgeAtBirthModel;
    }

    public ChartModel getMeanMedianAgeAtDeathModel()
    {
        return meanMedianAgeAtDeathModel;
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

    private ChartModel generateMeanMedianAgeModel(Map<String, Integer[]> map, String x)
    {
        ChartModel meanMedianAgeModel = new ChartModel();

        meanMedianAgeModel.setArrayData(map);

        meanMedianAgeModel.setxAxis(x + " year");
        meanMedianAgeModel.getxAxis().getLabel().setPosition(XLabel.Position.outer_center);

        meanMedianAgeModel.setyAxis("age at death");
        meanMedianAgeModel.getyAxis().getLabel().setPosition(YLabel.Position.outer_middle);

        meanMedianAgeModel.setBarLabels(new String[]{"mean age", "median age"});

        return meanMedianAgeModel;
    }
}
