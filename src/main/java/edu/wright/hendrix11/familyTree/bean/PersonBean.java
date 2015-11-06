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

/**
 * @author Joe
 */
@Named
@ViewScoped
public class PersonBean extends AbstractBean<Person> implements Serializable
{

    @EJB
    private PersonDataBean personDataBean;
    private BarChartModel agesChartModel;
    private PieChartModel genderPie;
    private BarChartModel perDecadeChartModel;

    @Override
    @PostConstruct
    protected void initialize()
    {
        super.initialize(personDataBean);

        initializeGenderPie();
        initializeAgeChart();
        initializeBirthPerDecadeChart();
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

    private void initializeBirthPerDecadeChart()
    {
        int max = 0;

        perDecadeChartModel = new BarChartModel();

        perDecadeChartModel.setTitle("Births and deaths per decade");
        perDecadeChartModel.setLegendPosition("ne");

        ChartSeries births = new ChartSeries();

        births.setLabel("births");

        for ( Integer[] i : personDataBean.birthsPerDecade() )
        {
            if ( max < i[0] )
                max = i[0];

            births.set(i[1].toString(), i[0]);
        }

        ChartSeries deaths = new ChartSeries();

        deaths.setLabel("deaths");

        for ( Integer[] i : personDataBean.deathsPerDecade() )
        {
            if ( max < i[0] )
                max = i[0];

            deaths.set(i[1].toString(), i[0]);
        }

        Axis xAxis = perDecadeChartModel.getAxis(AxisType.X);
        xAxis.setLabel("Year");

        Axis yAxis = perDecadeChartModel.getAxis(AxisType.Y);
        yAxis.setLabel("People");
        yAxis.setMin(0);
        yAxis.setMax(max + 2);

        perDecadeChartModel.addSeries(deaths);
        perDecadeChartModel.addSeries(births);
    }
}