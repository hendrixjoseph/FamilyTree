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

import edu.wright.hendrix11.familyTree.database.table.GenderTable;
import edu.wright.hendrix11.familyTree.entity.Gender;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Joe Hendrix
 */
@ManagedBean
@ApplicationScoped
public class GenderBean
{

    private HashMap<String, String> genders;
    private GenderTable genderTable;

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        genders = new HashMap<String, String>();
        genderTable = new GenderTable();
        List<Gender> genderList = genderTable.selectAll();

        for (Gender gender : genderList)
        {
            genders.put(gender.getFullWord(), gender.getFullWord());
        }
    }

    /**
     *
     * @return
     */
    public HashMap<String, String> getGenders()
    {
        return genders;
    }
}
