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

import edu.wright.hendrix11.d3.Color;
import edu.wright.hendrix11.d3.chart.ChartModel;
import edu.wright.hendrix11.d3.chart.axis.Axis;
import edu.wright.hendrix11.d3.chart.axis.XLabel;
import edu.wright.hendrix11.d3.chart.axis.YLabel;
import edu.wright.hendrix11.familyTree.dataBean.mining.BirthsAndDeathsDataBean;

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
public class BirthsAndDeathsBean implements Serializable
{
    @EJB
    private BirthsAndDeathsDataBean dataBean;
    private ChartModel perDecadeCleanModel;
    private ChartModel perDecadeCombinedModel;
    private ChartModel perDecadeModel;
    private ChartModel perMonthModel = new ChartModel();

    @PostConstruct
    private void initialize()
    {
        perMonthModel = generateModel(dataBean.perMonth(), "month");
        perDecadeModel = generateDecadeModel(dataBean.perDecade());
        perDecadeCleanModel = generateDecadeModel(dataBean.perDecadeClean());
        perDecadeCombinedModel = generateDecadeModel(dataBean.perDecadeCombined());
    }

    /**
     * Returns a chart model containing births and deaths per decade.
     *
     * @return a chart model containing births and deaths per decade
     *
     * @see BirthsAndDeathsDataBean#perDecadeClean
     */
    public ChartModel getPerDecadeCleanModel()
    {
        return perDecadeCleanModel;
    }

    /**
     * Returns a chart model containing births and deaths per decade.
     *
     * @return a chart model containing births and deaths per decade
     *
     * @see BirthsAndDeathsDataBean#perDecadeCombined
     */
    public ChartModel getPerDecadeCombinedModel()
    {
        return perDecadeCombinedModel;
    }

    /**
     * Returns a chart model containing births and deaths per decade.
     *
     * @return a chart model containing births and deaths per decade
     *
     * @see BirthsAndDeathsDataBean#perDecade
     */
    public ChartModel getPerDecadeModel()
    {
        return perDecadeModel;
    }

    /**
     * Returns a chart model containing births and deaths per month.
     *
     * @return a chart model containing births and deaths per month
     *
     * @see BirthsAndDeathsDataBean#perMonth
     */
    public ChartModel getPerMonthModel()
    {
        return perMonthModel;
    }

    private ChartModel generateDecadeModel(Map<String, Integer[]> data)
    {
        return generateModel(data, "decade");
    }

    private ChartModel generateModel(Map<String, Integer[]> data, String xAxisLabel)
    {
        ChartModel model = new ChartModel();
        model.setArrayData(data);
        model.setyAxis("people");
        model.getyAxis().getLabel().setPosition(YLabel.Position.outer_middle);
        model.setxAxis(xAxisLabel);
        model.getxAxis().getLabel().setPosition(XLabel.Position.outer_center);
        model.getxAxis().setType(Axis.Type.category);
        model.setBarLabels(new String[]{"births", "deaths"});
        model.addColor(Color.yellow);
        model.addColor(Color.black);

        return model;
    }
}
