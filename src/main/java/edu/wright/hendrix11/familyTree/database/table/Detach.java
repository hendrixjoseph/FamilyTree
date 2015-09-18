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
import edu.wright.hendrix11.familyTree.entity.PersonView;

/**
 *
 * @author Joe
 */
public class Detach
{

    private DetachParent detachParent;

    /**
     *
     * @param child
     */
    public void detachFather(PersonView child)
    {
        detachParent = new DetachFather();
        detachParent(child);
    }

    /**
     *
     * @param child
     */
    public void detachMother(PersonView child)
    {
        detachParent = new DetachMother();
        detachParent(child);
    }

    private void detachParent(PersonView child)
    {
        detachParent.delete(child);
    }

    /**
     *
     * @param person
     * @param spouse
     */
    public void detachSpouse(PersonView person, PersonView spouse)
    {

    }

    /**
     *
     * @param person
     * @param child
     */
    public void detachChild(PersonView person, PersonView child)
    {

    }

    private class DetachFather extends DetachParent
    {

        public DetachFather()
        {
            super("FATHER");
        }
    }

    private class DetachMother extends DetachParent
    {

        public DetachMother()
        {
            super("MOTHER");
        }
    }

    private abstract class DetachParent extends DatabaseQuery implements DeleteData<PersonView>
    {

        public DetachParent(String parentType)
        {
            super(parentType + "_OF", DetachEntity.class);

            ColumnMethodMap columnMethodMap = this.getColumnMethodMap();

            columnMethodMap.putGetter(parentType + "_ID", "getParentId");

            columnMethodMap.setPrimaryKey("CHILD_ID");
        }

        @Override
        public boolean delete(PersonView p)
        {
            if (p != null && p.getId() != null)
            {
                String query = "DELETE FROM " + tableName + " WHERE CHILD_ID=" + p.getId();

                try
                {
                    executeUpdate(query);
                    return true;
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            return false;
        }

        @Override
        protected Object getNew()
        {
            return null;
        }
    }

    private class DetachEntity
    {

        private int parentId;
        private int childId;

        public int getParentId()
        {
            return parentId;
        }

        public int getChildId()
        {
            return childId;
        }
    }
}
