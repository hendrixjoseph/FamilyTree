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
import edu.wright.hendrix11.svg.jsf.ChartArrayModel;
import edu.wright.hendrix11.svg.jsf.ChartModel;
import edu.wright.hendrix11.svg.jsf.ChartSingleModel;

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
    ChartArrayModel<Integer> perDecadeModel;
    ChartSingleModel<Number> ageModel;
    @EJB
    private PersonDataBean personDataBean;
    private PieChartModel genderPie;

    @Override
    @PostConstruct
    protected void initialize()
    {
        super.initialize(personDataBean);

        perDecadeModel = new ChartArrayModel<>();
        ageModel = new ChartSingleModel<>();

        perDecadeModel.setData(personDataBean.perDecade());
        String[] barLabels = {"births", "deaths"};
        perDecadeModel.setBarLabels(barLabels);
        perDecadeModel.setxAxisLabel("decade");
        perDecadeModel.setyAxisLabel("people");
        perDecadeModel.setTitle("Births and deaths per decade");

        ageModel.setData(personDataBean.ages());
        ageModel.setTitle("Ages");
        ageModel.setyAxisLabel("people");
        ageModel.setxAxisLabel("years");

        initializeGenderPie();
    }

    public ChartModel getPerDecadeModel()
    {
        return perDecadeModel;
    }

    public ChartSingleModel<Number> getAgeModel()
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
}