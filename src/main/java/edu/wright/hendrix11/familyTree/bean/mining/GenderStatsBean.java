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
import edu.wright.hendrix11.familyTree.dataBean.mining.GenderDataBean;
import edu.wright.hendrix11.familyTree.entity.Gender;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Joe Hendrix
 */
@Named
@ViewScoped
public class GenderStatsBean implements Serializable
{
    @EJB
    private GenderDataBean dataBean;
    private ChartModel genderModel = new ChartModel();

    @PostConstruct
    private void initialize()
    {
        Map<String, Integer> genderMap = new LinkedHashMap<>();
        genderMap.put(Gender.MALE.toString(), dataBean.countGender(Gender.MALE));
        genderMap.put(Gender.FEMALE.toString(), dataBean.countGender(Gender.FEMALE));

        genderModel.setData(genderMap);
        genderModel.addColor(Color.lightblue);
        genderModel.addColor(Color.lightpink);
    }

    public ChartModel getGenderModel()
    {
        return genderModel;
    }
}
