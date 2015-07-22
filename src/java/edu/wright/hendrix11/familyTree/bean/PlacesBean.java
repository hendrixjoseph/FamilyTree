package edu.wright.hendrix11.familyTree.bean;

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
    String[] columns = {};
    String[] prettyNames = {};
    List<String> values;

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
    
    }
    
    public String[] getPrettyNames()
    {
      return prettyNames;
    }
    
    public List<String> getValues()
    {
        return values;
    }
    
  }