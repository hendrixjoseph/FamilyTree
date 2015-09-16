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
 */
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
