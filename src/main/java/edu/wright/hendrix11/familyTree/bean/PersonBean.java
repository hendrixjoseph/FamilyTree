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

import oracle.sql.CHAR;
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

    private PieChartModel genderPie;
    private BarChartModel birthDeathChartModel;
    private BarChartModel agesChartModel;

    @Override
    @PostConstruct
    protected void initialize()
    {
        super.initialize(personDataBean);

        initializeGenderPie();
        initializeBirthDeathBarChart();
        initializeAgeChart();
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

        for(Object[] age : ages)
        {
            agesChart.set(age[1].toString(), (Number) age[0]);
        }

        agesChartModel.addSeries(agesChart);

        agesChartModel.setTitle("Ages");

        Axis xAxis = agesChartModel.getAxis(AxisType.X);
        xAxis.setLabel("Age");

        Axis yAxis = agesChartModel.getAxis(AxisType.Y);
        yAxis.setLabel("People");
        yAxis.setMin(0);
        yAxis.setMax(25);
    }

    private void initializeBarChart(String label, List<Object[]> data)
    {
        ChartSeries years = new ChartSeries();

        years.setLabel(label);

        for(Object[] o : data)
        {
            years.set(o[1].toString(), (Number) o[0]);
        }

        birthDeathChartModel.addSeries(years);
    }

    private void initializeBirthDeathBarChart()
    {
        birthDeathChartModel = new BarChartModel();

        initializeBarChart("births", personDataBean.birthsPerYear());

        birthDeathChartModel.setTitle("Births and deaths per year");
        birthDeathChartModel.setLegendPosition("ne");

        Axis xAxis = birthDeathChartModel.getAxis(AxisType.X);
        xAxis.setLabel("Year");

        Axis yAxis = birthDeathChartModel.getAxis(AxisType.Y);
        yAxis.setLabel("People");
        yAxis.setMin(0);
        yAxis.setMax(20);

        initializeBarChart("deaths", personDataBean.deathsPerYear());
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

    public BarChartModel getBirthDeathChartModel()
    {
        return birthDeathChartModel;
    }
}