
package edu.wright.hendrix11.familyTree.database;

import edu.wright.hendrix11.familyTree.database.interfaces.InsertData;
import edu.wright.hendrix11.familyTree.database.interfaces.SelectData;
import edu.wright.hendrix11.familyTree.entity.Marriage;
import edu.wright.hendrix11.familyTree.entity.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class MarriageData extends Database implements SelectData<List<Marriage>, Integer>, 
                                                      InsertData<Marriage>
{
    
    public MarriageData()
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
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
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
    
}