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
package edu.wright.hendrix11.familyTree.database.table;

import edu.wright.hendrix11.familyTree.database.ColumnMethodMap;
import edu.wright.hendrix11.familyTree.database.DatabaseQuery;
import edu.wright.hendrix11.familyTree.database.interfaces.SelectAllData;
import edu.wright.hendrix11.familyTree.database.interfaces.SelectData;
import edu.wright.hendrix11.familyTree.entity.PersonView;
import edu.wright.hendrix11.familyTree.entity.SpouseChildMap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class SpouseChildTable extends DatabaseQuery implements SelectData<HashMap<PersonView, List<PersonView>>, Integer>,
        SelectAllData<SpouseChildMap>
{

    /**
     *
     */
    public SpouseChildTable()
    {
        super("CHILDREN_VIEW", SpouseChildMap.class);

        ColumnMethodMap columnMethodMap = this.getColumnMethodMap();

        columnMethodMap.putGetter("ID", "getPerson().getId()");
        columnMethodMap.putSetter("ID", "getPerson().setId()");

        columnMethodMap.putGetter("NAME", "getPerson().getName()");
        columnMethodMap.putSetter("NAME", "getPerson().setName()");

        columnMethodMap.putGetter("SPOUSE_ID", "getSpouse().getId()");
        columnMethodMap.putSetter("SPOUSE_ID", "getSpouse().setId()");

        columnMethodMap.putGetter("SPOUSE", "getSpouse().getName()");
        columnMethodMap.putSetter("SPOUSE", "getSpouse().setName()");

        columnMethodMap.putGetter("CHILD_ID", "getChild().getId()");
        columnMethodMap.putSetter("CHILD_ID", "getChild().setId()");

        columnMethodMap.putGetter("CHILD", "getChild().getName()");
        columnMethodMap.putSetter("CHILD", "getChild().setName()");
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public HashMap<PersonView, List<PersonView>> select(Integer id)
    {
        HashMap<PersonView, List<PersonView>> map = new HashMap<PersonView, List<PersonView>>();

        try
        {
            ResultSet rs = selectWithKey(id);

            while (rs.next())
            {
                SpouseChildMap spouseChildMap = new SpouseChildMap();

                this.setFields(spouseChildMap, rs);

                PersonView spouse = spouseChildMap.getSpouse();

                if (!spouse.exists())
                {
                    spouse = null;
                }

                List<PersonView> childList = map.get(spouse);

                if (childList == null)
                {
                    childList = new ArrayList<PersonView>();
                }

                childList.add(spouseChildMap.getChild());

                map.put(spouse, childList);
            }

            closeStatement(rs);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return map;
    }

    /**
     *
     * @param id
     * @return
     */
    public HashMap<PersonView, List<SpouseChildMap>> selectOld(Integer id)
    {
        HashMap<PersonView, List<SpouseChildMap>> map = new HashMap<PersonView, List<SpouseChildMap>>();

        try
        {
            ResultSet rs = selectWithKey(id);

            while (rs.next())
            {
                SpouseChildMap spouseChildMap = new SpouseChildMap();

                this.setFields(spouseChildMap, rs);

                PersonView spouse = spouseChildMap.getSpouse();

                if (!spouse.exists())
                {
                    spouse = null;
                }

                List<SpouseChildMap> childList = map.get(spouse);

                if (childList == null)
                {
                    childList = new ArrayList<SpouseChildMap>();
                }

                childList.add(spouseChildMap);

                map.put(spouse, childList);
            }

            closeStatement(rs);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return map;
    }

    @Override
    public List<SpouseChildMap> selectAll()
    {
        List<Object> objects = super.selectAllObjects();

        List<SpouseChildMap> map = new ArrayList<SpouseChildMap>();

        for (Object object : objects)
        {
            if (object instanceof SpouseChildMap)
            {
                map.add((SpouseChildMap) object);
            }
        }

        return map;
    }

    /**
     *
     * @return
     */
    @Override
    protected Object getNew()
    {
        return new SpouseChildMap();
    }
}
