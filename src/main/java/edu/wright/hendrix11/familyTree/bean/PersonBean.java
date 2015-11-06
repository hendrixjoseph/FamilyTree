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

package edu.wright.hendrix11.familyTree.bean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import edu.wright.hendrix11.familyTree.dataBean.PersonDataBean;
import edu.wright.hendrix11.familyTree.entity.Gender;
import edu.wright.hendrix11.familyTree.entity.Person;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static edu.wright.hendrix11.familyTree.dataBean.PersonDataBean.PerDecadeType.BIRTHS;
import static edu.wright.hendrix11.familyTree.dataBean.PersonDataBean.PerDecadeType.DEATHS;

/**
 * @author Joe
 */
@Named
@ViewScoped
public class PersonBean extends AbstractBean<Person> implements Serializable
{
    private static final Logger LOG = Logger.getLogger(PersonBean.class.getName());

    @EJB
    private PersonDataBean personDataBean;
    private BarChartModel agesChartModel;
    private PieChartModel genderPie;
    private BarChartModel perDecadeChartModel;
    private BarChartModel perDecadeCleanChartModel;

    @Override
    @PostConstruct
    protected void initialize()
    {
        super.initialize(personDataBean);

        initializeGenderPie();
        initializeAgeChart();
        initializePerDecadeChart();
        initializePerDecadeCleanChart();
    }

    public BarChartModel getPerDecadeChartModel()
    {
        return perDecadeChartModel;
    }

    public BarChartModel getAgesChartModel()
    {
        return agesChartModel;
    }

    /**
     * @return
     */
    public PieChartModel getGenderPie()
    {
        return genderPie;
    }

    public BarChartModel getPerDecadeCleanChartModel()
    {
        return perDecadeCleanChartModel;
    }

    private void initializeGenderPie()
    {
        genderPie = new PieChartModel();

        Number numMales = personDataBean.countGender(Gender.MALE);
        Number numFemales = personDataBean.countGender(Gender.FEMALE);

        genderPie.set("Males", numMales);
        genderPie.set("Females", numFemales);

        genderPie.setLegendPosition("w");
        genderPie.setShowDataLabels(true);
    }

    private void initializeAgeChart()
    {
        agesChartModel = new BarChartModel();

        ChartSeries agesChart = new ChartSeries();

        List<Object[]> ages = personDataBean.ages();

        for ( Object[] age : ages )
        {
            agesChart.set(age[1].toString(), (Number) age[0]);
        }

        agesChartModel.addSeries(agesChart);

        agesChartModel.setTitle(String.format("Average age: %.2f", personDataBean.averageAge()));

        Axis xAxis = agesChartModel.getAxis(AxisType.X);
        xAxis.setLabel("Age");

        Axis yAxis = agesChartModel.getAxis(AxisType.Y);
        yAxis.setLabel("People");
        yAxis.setMin(0);
        yAxis.setMax(25);
    }

    private int initializeChart(BarChartModel model, Map<Object,Number> data, String label)
    {
        ChartSeries years = new BarChartSeries();

        years.setLabel(label);

        int max = 0;

        for(Number value : data.values())
        {
            if(max < value.intValue())
                max = value.intValue();
        }

        years.setData(data);

        model.addSeries(years);
        model.setDatatipFormat("%2$d people");

        return max;
    }

    private void initializePerDecadeCleanChart()
    {
        perDecadeCleanChartModel = new BarChartModel();

        perDecadeCleanChartModel.setTitle("Births and deaths per decade (estimated)");
        perDecadeCleanChartModel.setLegendPosition("ne");

        int max = initializeChart(perDecadeCleanChartModel, personDataBean.perDecadeClean(BIRTHS), "births");
        int max2 = initializeChart(perDecadeCleanChartModel, personDataBean.perDecadeClean(DEATHS), "deaths");

        Axis xAxis = perDecadeCleanChartModel.getAxis(AxisType.X);
        xAxis.setLabel("Year");

        Axis yAxis = perDecadeCleanChartModel.getAxis(AxisType.Y);
        yAxis.setLabel("People");
        yAxis.setMin(0);
        yAxis.setMax(Math.max(max, max2) + 2);
    }

    private void initializePerDecadeChart()
    {
        perDecadeChartModel = new BarChartModel();

        perDecadeChartModel.setTitle("Births and deaths per decade");
        perDecadeChartModel.setLegendPosition("ne");

        Map<Object, Number>[] perDecade = personDataBean.perDecade();

        int max = initializeChart(perDecadeChartModel, perDecade[0], "births");
        int max2 = initializeChart(perDecadeChartModel, perDecade[1], "deaths");

        Axis xAxis = perDecadeChartModel.getAxis(AxisType.X);
        xAxis.setLabel("Year");

        Axis yAxis = perDecadeChartModel.getAxis(AxisType.Y);
        yAxis.setLabel("People");
        yAxis.setMin(0);
        yAxis.setMax(Math.max(0, max2) + 2);
    }
}