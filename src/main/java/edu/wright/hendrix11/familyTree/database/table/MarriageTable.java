
package edu.wright.hendrix11.familyTree.database.table;

import edu.wright.hendrix11.familyTree.database.ColumnMethodMap;
import edu.wright.hendrix11.familyTree.database.DatabaseQuery;
import edu.wright.hendrix11.familyTree.database.interfaces.InsertData;
import edu.wright.hendrix11.familyTree.database.interfaces.SelectAllData;
import edu.wright.hendrix11.familyTree.database.interfaces.SelectData;
import edu.wright.hendrix11.familyTree.entity.Marriage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class MarriageTable extends DatabaseQuery implements SelectData<List<Marriage>, Integer>, 
                                                            SelectAllData<Marriage>,
                                                            InsertData<Marriage>
{
    
    /**
     *
     */
    public MarriageTable()
    {
        super("MARRIAGE_VIEW",Marriage.class);
        
        ColumnMethodMap columnMethodMap = this.getColumnMethodMap();
        
        columnMethodMap.putGetter("HUSBAND_ID","getHusband().getId()");
        columnMethodMap.putSetter("HUSBAND_ID","getHusband().setId()");
        
        columnMethodMap.putGetter("HUSBAND_NAME","getHusband().getName()");
        columnMethodMap.putSetter("HUSBAND_NAME","getHusband().setName()");
        
        columnMethodMap.putGetter("WIFE_ID","getWife().getId()");
        columnMethodMap.putSetter("WIFE_ID","getWife().setId()");
        
        columnMethodMap.putGetter("WIFE_NAME","getWife().getName()");
        columnMethodMap.putSetter("WIFE_NAME","getWife().setName()");
        
        List<String> primaryKey = columnMethodMap.getPrimaryKey();
        
        primaryKey.add("HUSBAND_ID");
        primaryKey.add("WIFE_ID");
    }

    @Override
    public List<Marriage> select(Integer id)
    {
        List<Marriage> marriages = new ArrayList<Marriage>();
        
        try
        {
            ResultSet rs = selectWithKey("HUSBAND_ID",id);
            
            Marriage marriage = new Marriage();
            
            while(rs.next())
            {
                this.setFields(marriage, rs);
                
                marriages.add(marriage);
            }
            
            rs = selectWithKey("WIFE_ID",id);
            
            while(rs.next())
            {
                this.setFields(marriage, rs);
                
                marriages.add(marriage);
            }
            
            closeStatement(rs);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return marriages;
    }
    
    @Override
    public List<Marriage> selectAll()
    {
        List<Object> objects = super.selectAllObjects();
        
        List<Marriage> marriages = new ArrayList<Marriage>();
        
        for(Object object : objects)
        {
            if(object instanceof Marriage)
                marriages.add((Marriage)object);
        }
        
        return marriages;
    }

    @Override
    public Marriage insert(Marriage marriage)
    {
        String query = generateInsertQuery(marriage);
        
        try
        {        
            executeUpdate(query);
            
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     *
     * @return
     */
    @Override
    protected Object getNew()
    {
        return new Marriage();
    }    
}