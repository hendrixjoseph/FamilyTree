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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
    private Map<String, Double> statsMap = new LinkedHashMap<>();

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

        statsMap.put("Minimum age:", (double) dataBean.minAge());
        statsMap.put("Q2:", (double) dataBean.ageQuartile(2));
        statsMap.put("Median age:", dataBean.medianAge());
        statsMap.put("Q3:", (double) dataBean.ageQuartile(3));
        statsMap.put("Maximum age:", (double) dataBean.maxAge());
        statsMap.put("Average age:", dataBean.averageAge());
    }

    /**
     * Returns a list of statistic labels to the statistic value.
     * <p>
     * These values are first put into a {@link LinkedHashMap} then converted into a {@link List} of {@link Map.Entry}.
     * This is to allow use in PrimeFaces' datatable JSF element.
     *
     * @return a list of statistic labels to the statistic value
     */
    public List<Map.Entry<String, Integer>> getStatsMapList()
    {
        return new ArrayList(statsMap.entrySet());
    }

    /**
     * Returns a chart model containing ages at birth data.
     *
     * @return a chart model containing ages at birth data
     *
     * @see AgesDataBean#meanMeadianAgesPerBirthYear
     */
    public ChartModel getMeanMedianAgeAtBirthModel()
    {
        return meanMedianAgeAtBirthModel;
    }

    /**
     * Returns a chart model containing ages at death data.
     *
     * @return a chart model containing ages at death data
     *
     * @see AgesDataBean#meanMeadianAgesPerDeathYear
     */
    public ChartModel getMeanMedianAgeAtDeathModel()
    {
        return meanMedianAgeAtDeathModel;
    }

    /**
     * Returns a chart model containing age data.
     *
     * @return a chart model containing age data
     *
     * @see AgesDataBean#ages
     */
    public ChartModel getAgeModel()
    {
        return ageModel;
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
