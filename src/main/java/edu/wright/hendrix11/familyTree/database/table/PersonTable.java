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
import edu.wright.hendrix11.familyTree.database.interfaces.DeleteData;
import edu.wright.hendrix11.familyTree.database.interfaces.InsertData;
import edu.wright.hendrix11.familyTree.database.interfaces.SelectAllData;
import edu.wright.hendrix11.familyTree.database.interfaces.SelectData;
import edu.wright.hendrix11.familyTree.database.interfaces.UpdateData;
import edu.wright.hendrix11.familyTree.entity.Marriage;
import edu.wright.hendrix11.familyTree.entity.PersonView;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class PersonTable extends DatabaseQuery implements SelectData<PersonView, Integer>,
        SelectAllData<PersonView>,
        InsertData<PersonView>,
        UpdateData<PersonView>,
        DeleteData<PersonView>
{

    /**
     *
     */
    public PersonTable()
    {
        super("PERSON_VIEW", PersonView.class);

        ColumnMethodMap columnMethodMap = this.getColumnMethodMap();

        columnMethodMap.putGetter("FATHER_ID", "getFather().getId()");
        columnMethodMap.putSetter("FATHER_ID", "getFather().setId()");

        columnMethodMap.putGetter("FATHER_NAME", "getFather().getName()");
        columnMethodMap.putSetter("FATHER_NAME", "getFather().setName()");

        columnMethodMap.putGetter("MOTHER_ID", "getMother().getId()");
        columnMethodMap.putSetter("MOTHER_ID", "getMother().setId()");

        columnMethodMap.putGetter("MOTHER_NAME", "getMother().getName()");
        columnMethodMap.putSetter("MOTHER_NAME", "getMother().setName()");
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public PersonView select(Integer id)
    {
        return select(id, true);
    }

    /**
     *
     * @param id
     * @param includeSpouseChildMap
     * @return
     */
    public PersonView select(Integer id, boolean includeSpouseChildMap)
    {
        PersonView person = new PersonView();
        person.setFather(new PersonView());
        person.setMother(new PersonView());

        try
        {
            ResultSet rs = selectWithKey(id);

            if (rs.next())
            {
                this.setFields(person, rs);
            }

            this.closeStatement(rs);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (includeSpouseChildMap)
        {
            setSpouseChildMap(person, id);
        }

        return person;
    }

    private void setSpouseChildMap(PersonView person, Integer id)
    {
        SpouseChildTable mapData = new SpouseChildTable();

        HashMap<PersonView, List<PersonView>> map = mapData.select(id);

        addSpousesWithNoChildren(map, id);

        addChildData(map);

        //person.setSpouseChildMap(map);
    }

    /**
     *
     * @return
     */
    public PersonView selectDefault()
    {
        PersonView person = new PersonView();
        person.setFather(new PersonView());
        person.setMother(new PersonView());

        try
        {
            ResultSet rs = this.executeQuery("SELECT * FROM DEFAULT_PERSON_VIEW");

            if (rs.next())
            {
                this.setFields(person, rs);
            }

            this.closeStatement(rs);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        int id = person.getId();

        SpouseChildTable mapTable = new SpouseChildTable();

        HashMap<PersonView, List<PersonView>> map = mapTable.select(id);

        addSpousesWithNoChildren(map, id);

        addChildData(map);

        //person.setSpouseChildMap(map);
        return person;
    }

    private void addChildData(HashMap<PersonView, List<PersonView>> map)
    {
        for (PersonView spouse : map.keySet())
        {
            List<PersonView> childList = map.get(spouse);

            List<PersonView> newList = new ArrayList<PersonView>();

            for (PersonView child : childList)
            {
                newList.add(select(child.getId(), false));
            }

            childList.removeAll(childList);

            childList.addAll(newList);
        }
    }

    private void addSpousesWithNoChildren(HashMap<PersonView, List<PersonView>> map, Integer id)
    {
        MarriageTable marriageTable = new MarriageTable();

        List<Marriage> marriages = marriageTable.select(id);

        for (Marriage marriage : marriages)
        {
            PersonView spouse = new PersonView();

            if (marriage.getHusband().getId().equals(id))
            {
                spouse = marriage.getWife();
            }
            else if (marriage.getWife().getId().equals(id))
            {
                spouse = marriage.getHusband();
            }

            if (map.get(spouse) == null)
            {
                map.put(spouse, new ArrayList<PersonView>());
            }
        }
    }

    /**
     *
     * @return
     */
    @Override
    public List<PersonView> selectAll()
    {
        List<Object> objects = super.selectAllObjects();

        List<PersonView> persons = new ArrayList<PersonView>();

        for (Object object : objects)
        {
            if (object instanceof PersonView)
            {
                persons.add((PersonView) object);
            }
        }

        return persons;
    }

    /**
     *
     * @return
     */
    @Override
    protected Object getNew()
    {
        return new PersonView();
    }

    private PersonView selectLastInserted()
    {
        PersonView person = null;

        try
        {
            ResultSet rs = executeQuery("SELECT * FROM LAST_PERSON_INSERTED_VIEW");

            person = new PersonView();

            if (rs.next())
            {
                this.setFields(person, rs);
            }

            closeStatement(rs);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return person;
    }

    /**
     *
     * @param p
     * @return
     */
    @Override
    public PersonView update(PersonView p)
    {
        Object o = updateObject(p);

        if (o instanceof PersonView)
        {
            return (PersonView) o;
        }
        else
        {
            return null;
        }
    }

    /**
     *
     * @param p
     * @return
     */
    @Override
    public PersonView insert(PersonView p)
    {
        String query = generateInsertQuery(p);

        try
        {
            executeUpdate(query);
            return selectLastInserted();
        }
        catch (SQLException ex)
        {
            System.err.println(query);
            ex.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param p
     * @param childId
     * @return
     */
    public PersonView insert(PersonView p, int childId)
    {
        return insert(p);
    }

    /**
     *
     * @param p
     * @return
     */
    @Override
    public boolean delete(PersonView p)
    {
        return super.deleteObject(p);
    }
}
