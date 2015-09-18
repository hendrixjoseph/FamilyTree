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

import edu.wright.hendrix11.familyTree.database.table.MarriageTable;
import edu.wright.hendrix11.familyTree.entity.Place;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Joe Hendrix
 */
@ManagedBean
@ViewScoped
public class MarriagesBean extends DataBean<Place>
{

    private static final String[] columns =
    {
        "HUSBAND_ID", "HUSBAND_NAME", "WIFE_ID", "WIFE_NAME", "PLACE", "ANNIVERSARY"
    };
    private static final String[] prettyNames =
    {
        "Husband Id", "Husband Name", "Wife Id", "Wife Name", "Place", "Anniversary Date"
    };

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        table = new MarriageTable();

        super.initialize(table);
    }

    /**
     *
     * @return
     */
    @Override
    public String[] getPrettyNames()
    {
        return prettyNames;
    }

    /**
     *
     * @return
     */
    @Override
    protected String[] getColumns()
    {
        return columns;
    }

//    @Override
//    protected String processLink(String column, String value)
//    {
//        return "";
//    }
}
