/* 
 *  The MIT License (MIT)
 *  
 *  Copyright (c) 2015 Joseph Hendrix
 *  
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *  
 *  Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
 *  
 */
package edu.wright.hendrix11.familyTree.database.table;

import edu.wright.hendrix11.familyTree.database.ColumnMethodMap;
import edu.wright.hendrix11.familyTree.database.DatabaseQuery;
import edu.wright.hendrix11.familyTree.database.interfaces.DeleteData;
import edu.wright.hendrix11.familyTree.entity.Person;

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
    public void detachFather(Person child)
  {
    detachParent = new DetachFather();
    detachParent(child);
  }
  
    /**
     *
     * @param child
     */
    public void detachMother(Person child)
  {
    detachParent = new DetachMother();
    detachParent(child);
  }
  
  private void detachParent(Person child)
  {
    detachParent.delete(child);
  }
  
    /**
     *
     * @param person
     * @param spouse
     */
    public void detachSpouse(Person person, Person spouse)
  {
  
  }
  
    /**
     *
     * @param person
     * @param child
     */
    public void detachChild(Person person, Person child)
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
  
  private abstract class DetachParent extends DatabaseQuery implements DeleteData<Person>
  {
    public DetachParent(String parentType)
    {
        super(parentType + "_OF", DetachEntity.class);

        ColumnMethodMap columnMethodMap = this.getColumnMethodMap();
        
        columnMethodMap.putGetter(parentType + "_ID","getParentId");

        columnMethodMap.setPrimaryKey("CHILD_ID");
    }
  
    @Override
    public boolean delete(Person p)
    {
      if(p != null && p.getId() != null)
      {
        String query = "DELETE FROM " + tableName + " WHERE CHILD_ID=" + p.getId();

        try
        {
          executeUpdate(query);
          return true;
        }
        catch(Exception e)
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
