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

import org.primefaces.model.chart.PieChartModel;

import edu.wright.hendrix11.familyTree.dataBean.mining.StatisticsDataBean;
import edu.wright.hendrix11.familyTree.entity.Gender;
import edu.wright.hendrix11.svg.jsf.barChart.BarChartArrayModel;
import edu.wright.hendrix11.svg.jsf.barChart.BarChartModel;
import edu.wright.hendrix11.svg.jsf.barChart.BarChartSingleModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 * @author Joe
 */
@Named
@ViewScoped
public class StatisticsBean implements Serializable
{
    private static final Logger LOG = Logger.getLogger(StatisticsBean.class.getName());
    BarChartArrayModel<Integer> perDecadeModel;
    BarChartArrayModel<Integer> perDecadeCleanModel;
    BarChartArrayModel<Integer> perDecadeCombinedModel;
    BarChartSingleModel<Integer> ageModel;

    @EJB
    private StatisticsDataBean statisticsDataBean;
    private PieChartModel genderPie;

    /**
     *
     */
    @PostConstruct
    protected void initialize()
    {
        perDecadeModel = new BarChartArrayModel<>();
        perDecadeModel.setData(statisticsDataBean.perDecade());
        setupPerDecadeModel(perDecadeModel);
        perDecadeModel.setTitle("Births and deaths per decade");

        perDecadeCleanModel = new BarChartArrayModel<>();
        perDecadeCleanModel.setData(statisticsDataBean.perDecadeClean());
        setupPerDecadeModel(perDecadeCleanModel);
        perDecadeCleanModel.setTitle("Births and deaths per decade (estimated assuming average age)");

        perDecadeCombinedModel = new BarChartArrayModel<>();
        perDecadeCombinedModel.setData(statisticsDataBean.perDecadeCombined());
        setupPerDecadeModel(perDecadeCombinedModel);
        perDecadeCombinedModel.setTitle("Births and deaths per decade (estimated and known)");

        ageModel = new BarChartSingleModel<>();
        ageModel.setData(statisticsDataBean.ages());
        ageModel.setTitle("Ages");
        ageModel.setyAxisLabel("people");
        ageModel.setxAxisLabel("years");

        initializeGenderPie();
    }

    /**
     * @return
     */
    public BarChartArrayModel<Integer> getPerDecadeCombinedModel()
    {
        return perDecadeCombinedModel;
    }

    /**
     * @return
     */
    public BarChartArrayModel<Integer> getPerDecadeCleanModel()
    {
        return perDecadeCleanModel;
    }

    /**
     * @return
     */
    public BarChartModel<?> getPerDecadeModel()
    {
        return perDecadeModel;
    }

    /**
     * @return
     */
    public BarChartSingleModel<Integer> getAgeModel()
    {
        return ageModel;
    }

    /**
     * @return
     */
    public PieChartModel getGenderPie()
    {
        return genderPie;
    }

    private void setupPerDecadeModel(BarChartArrayModel<Integer> perDecadeModel)
    {
        String[] barLabels = {"births", "deaths"};
        perDecadeModel.setBarLabels(barLabels);
        perDecadeModel.setxAxisLabel("decade");
        perDecadeModel.setyAxisLabel("people");
    }

    private void initializeGenderPie()
    {
        genderPie = new PieChartModel();

        Number numMales = statisticsDataBean.countGender(Gender.MALE);
        Number numFemales = statisticsDataBean.countGender(Gender.FEMALE);

        genderPie.set("Males", numMales);
        genderPie.set("Females", numFemales);

        genderPie.setLegendPosition("w");
        genderPie.setShowDataLabels(true);
    }
}