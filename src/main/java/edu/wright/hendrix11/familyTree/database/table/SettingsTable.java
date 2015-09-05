package edu.wright.hendrix11.familyTree.database.table;

import edu.wright.hendrix11.familyTree.database.ColumnMethodMap;
import edu.wright.hendrix11.familyTree.database.Database;
import edu.wright.hendrix11.familyTree.database.DatabaseQuery;
import edu.wright.hendrix11.familyTree.database.interfaces.UpdateData;
import edu.wright.hendrix11.familyTree.entity.Settings;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
*
* @author Joe Hendrix <hendrix.11@wright.edu>
*/
public class SettingsTable extends DatabaseQuery implements UpdateData<Settings>
{

    /**
     *
     */
    public SettingsTable()
  {
    super("SETTINGS_VIEW", Settings.class);
  }
  
    /**
     *
     * @return
     */
    public Settings select()
  {
        Settings settings = new Settings();
     
        try
        {
            ResultSet rs = executeQuery("SELECT * FROM " + tableName);
            
            if(rs.next())
            {
                this.setFields(settings, rs);
            }
            
            closeStatement(rs);
        }
        catch(SQLException ex)
        {
          ex.printStackTrace();
        }
        
        return settings;
  }
  
    /**
     *
     * @return
     */
    @Override
  protected Object getNew()
  {
      return null;
  }

    @Override
    public Settings update(Settings settings)
    {
        ColumnMethodMap columnMethodMap = this.getColumnMethodMap();
        
         List<String> columns = columnMethodMap.getColumns();
        
        StringBuilder query = new StringBuilder();
        
        query.append("UPDATE ").append(tableName).append(" SET ");
        
        for(String column : columns)
        {
            if(!columnMethodMap.getPrimaryKey().contains(column))
            {
                String value = columnMethodMap.get(column,settings);
                
                if(value != null)
                    query.append(column).append("=").append(value).append(",");
            }
        }
        
         query.deleteCharAt(query.length() - 1);
         
      try
      {
          executeUpdate(query.toString());
      }
      catch (SQLException ex)
      {
          System.err.println(query.toString());
          ex.printStackTrace();
      }
        
        return settings;
    }
}
