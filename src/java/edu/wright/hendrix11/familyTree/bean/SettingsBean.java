package edu.wright.hendrix11.familyTree.bean;


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
    
    
}
