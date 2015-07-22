package edu.wright.hendrix11.familyTree.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
*
* @author Joe Hendrix
*/

@ManagedBean
@RequestScoped
public class PlacesBean
{
    private static final String[] columns = {"ID","NAME"};
    private static final String[] prettyNames = {"Id","Name"};
    private List<Place> values;
    private PlacesTable placesTable;

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        placesTable = new PlacesTable();
        
        HashMap<String, String> map = placesTable.getColumnValuesMap(place);
    }
    
    public String[] getPrettyNames()
    {
      return prettyNames;
    }
    
    public List<Place> getValues()
    {
        return values;
    }
    
  }
