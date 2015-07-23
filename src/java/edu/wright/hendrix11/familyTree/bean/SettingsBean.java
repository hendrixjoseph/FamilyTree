package edu.wright.hendrix11.familyTree.bean;

import edu.wright.hendrix11.familyTree.database.table.SettingsTable;
import edu.wright.hendrix11.familyTree.entity.Settings;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
*
* @author Joe Hendrix
*/
@ManagedBean
@SessionScoped
public class SettingsBean
{
  private Settings settings;
  private SettingsTable settingsTable;
  
      /**
     *
     */
    @PostConstruct
    public void initialize()
    {
      settingsTable = new SettingsTable();
      settings = settingsTable.select();
    }

    public Settings getSettings()
    {
        return settings;
    }

    public void setSettings(Settings settings)
    {
        this.settings = settings;
    }
    
    public void commit()
    {
        settingsTable.update(settings);
    }
    
    
}
