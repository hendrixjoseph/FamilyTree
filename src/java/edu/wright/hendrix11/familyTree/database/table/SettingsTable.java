package edu.wright.hendrix11.familyTree.database.table;


/**
*
* @author Joe Hendrix <hendrix.11@wright.edu>
*/
public class SettingsTable extends Database
{
  public SettingsTable()
  {
    super("SETTINGS_VIEW", Settings.class);
  }
  
  public Settings select()
  {
    ResultSet rs = select("SELECT * FROM SETTINGS_VIEW");
    
    Settings settings = new Settings();
    
    try
    {
     if(rs.next())
      {
        this.setFields(settings, rs);
      }
      
      closeStatement(rs);
      }
       catch(Exception e)
      {
        e.printStackTrace();
      }
      
      return settings;
  }
}
