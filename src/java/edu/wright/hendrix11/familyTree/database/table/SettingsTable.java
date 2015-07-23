package edu.wright.hendrix11.familyTree.database.table;

import edu.wright.hendrix11.familyTree.database.Database;
import edu.wright.hendrix11.familyTree.database.DatabaseQuery;
import edu.wright.hendrix11.familyTree.entity.Settings;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
*
* @author Joe Hendrix <hendrix.11@wright.edu>
*/
public class SettingsTable extends DatabaseQuery
{
  public SettingsTable()
  {
    super("SETTINGS_VIEW", Settings.class);
  }
  
  public Settings select()
  {
        Settings settings = new Settings();
     
        try
        {
            ResultSet rs = select("SELECT * FROM SETTINGS_VIEW");
            
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
}
