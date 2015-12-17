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
import edu.wright.hendrix11.familyTree.dataBean.mining.AgesDataBean;
import edu.wright.hendrix11.familyTree.dataBean.mining.GenderDataBean;
import edu.wright.hendrix11.familyTree.entity.Gender;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
@Named
@ViewScoped
public class GenderStatsBean implements Serializable
{
    private static final Logger LOG = Logger.getLogger(GenderStatsBean.class.getName());
    List<GenderAges> genderAges = new ArrayList<>();
    @EJB
    private AgesDataBean agesDataBean;
    @EJB
    private GenderDataBean dataBean;
    private ChartModel genderModel = new ChartModel();
    private double overallAge;

    @PostConstruct
    private void initialize()
    {
        Map<String, Integer> genderMap = new LinkedHashMap<>();
        genderMap.put(Gender.MALE.toString(), dataBean.countGender(Gender.MALE));
        genderMap.put(Gender.FEMALE.toString(), dataBean.countGender(Gender.FEMALE));

        genderModel.setData(genderMap);
        genderModel.addColor(Color.lightblue);
        genderModel.addColor(Color.lightpink);

        overallAge = agesDataBean.averageAge();

        genderAges.add(new GenderAges(Gender.MALE, dataBean.averageAge(Gender.MALE)));
        genderAges.add(new GenderAges(Gender.FEMALE, dataBean.averageAge(Gender.FEMALE)));
        genderAges.add(new GenderAges("overall", overallAge));
    }

    /**
     * @return
     */
    public ChartModel getGenderModel()
    {
        return genderModel;
    }

    /**
     * @return
     */
    public List<GenderAges> getGenderAges()
    {
        return genderAges;
    }

    /**
     *
     */
    public class GenderAges
    {
        private double age;
        private String gender;

        /**
         * @param gender
         * @param age
         */
        public GenderAges(String gender, Double age)
        {
            this.age = age;
            this.gender = gender;
        }

        /**
         * @param gender
         * @param age
         */
        public GenderAges(Gender gender, Double age)
        {
            this.age = age;
            this.gender = gender.toString();
        }

        /**
         * @return
         */
        public String getGender()
        {
            return gender;
        }

        /**
         * @return
         */
        public double getAge()
        {
            return age;
        }

        /**
         * @return
         */
        public double getDifference()
        {
            return Math.abs(age - overallAge);
        }

        /**
         * @return
         */
        public double getPercentDifference()
        {
            return getDifference() / overallAge;
        }

        public double getPercent()
        {
            return age / overallAge;
        }
    }
}
