package edu.wright.hendrix11.familyTree.database.table;

public class Detach
{
  public void detachFather(Person child)
  {
  
  }
  
  public void detachMother(Person child)
  {
  
  }
  
  public void detachSpouse(Person person, Person spouse)
  {
  
  }
  
  public void detachChild(Person person, Person child)
  {
  
  }
  
  private class DetachFather extends DetachParent
  {
    public DetachFather
    {
      super("FATHER");
    }
  }
  
  private class DetachMother extends DetachParent
  {
    public DetachMother
    {
      super("MOTHER");
    }
  }
  
  private abstract class DetachParent extends DatabaseQuery implements DeleteData<Person>
  {
    public DetachFather(String parentType)
    {
        super(parentType + "_OF", DetachEntity.class);

        ColumnMethodMap columnMethodMap = this.getColumnMethodMap();
        
        columnMethodMap.putGetter(parentType + "_ID","getParentId");

        columnMethodMap.setPrimaryKey("CHILD_ID");
    }
  
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
