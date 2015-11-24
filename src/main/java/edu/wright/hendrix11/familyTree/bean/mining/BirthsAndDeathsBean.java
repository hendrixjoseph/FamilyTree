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
import edu.wright.hendrix11.c3.Color;
import edu.wright.hendrix11.c3.axis.Axis;
import edu.wright.hendrix11.c3.axis.XLabel;
import edu.wright.hendrix11.c3.axis.YLabel;
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
    BirthsAndDeathsDataBean dataBean;

    ChartModel perMonthModel = new ChartModel();
    ChartModel perDecadeModel;
    ChartModel perDecadeCleanModel;
    ChartModel perDecadeCombinedModel;

    @PostConstruct
    private void initialize()
    {
        perMonthModel.setArrayData(dataBean.perMonth());
        perMonthModel.setyAxis("people");
        perMonthModel.getyAxis().getLabel().setPosition(YLabel.Position.outer_middle);
        perMonthModel.setxAxis("month");
        perMonthModel.getxAxis().getLabel().setPosition(XLabel.Position.outer_center);
        perMonthModel.getxAxis().setType(Axis.Type.category);
        perMonthModel.setBarLabels(new String[]{"births", "deaths"});

        perDecadeModel = generateDecadeModel(dataBean.perDecade());
        perDecadeCleanModel = generateDecadeModel(dataBean.perDecadeClean());
        perDecadeCombinedModel = generateDecadeModel(dataBean.perDecadeCombined());
    }

    /**
     *
     * @return
     */
    public ChartModel getPerDecadeCleanModel()
    {
        return perDecadeCleanModel;
    }

    /**
     *
     * @return
     */
    public ChartModel getPerDecadeCombinedModel()
    {
        return perDecadeCombinedModel;
    }

    /**
     *
     * @return
     */
    public ChartModel getPerDecadeModel()
    {
        return perDecadeModel;
    }

    /**
     *
     * @return
     */
    public ChartModel getPerMonthModel()
    {
        return perMonthModel;
    }

    private ChartModel generateDecadeModel(Map<String, Integer[]> data)
    {
        ChartModel model = new ChartModel();
        model.setArrayData(data);
        model.setyAxis("people");
        model.getyAxis().getLabel().setPosition(YLabel.Position.outer_middle);
        model.setxAxis("decade");
        model.getxAxis().getLabel().setPosition(XLabel.Position.outer_center);
        model.getxAxis().setType(Axis.Type.category);
        model.setBarLabels(new String[]{"births", "deaths"});
        model.addColor(Color.yellow);
        model.addColor(Color.black);

        return model;
    }
}
