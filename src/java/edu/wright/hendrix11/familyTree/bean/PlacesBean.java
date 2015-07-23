package edu.wright.hendrix11.familyTree.bean;

import edu.wright.hendrix11.familyTree.bean.PlacesBean.Places;
import edu.wright.hendrix11.familyTree.database.table.PlaceTable;
import edu.wright.hendrix11.familyTree.entity.Place;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
*
* @author Joe Hendrix
*/

@ManagedBean
@ViewScoped
public class PlacesBean extends DataBean<Place,Places>
{
    private static final String[] columns = {"ID","NAME"};
    private static final String[] prettyNames = {"Id","Name"};

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        table = new PlaceTable();
        
        super.initialize(table);
    }
    
    @Override
    public String[] getPrettyNames()
    {
      return prettyNames;
    }
    
    @Override
    protected String[] getColumns()
    {
        return columns;
    }
    
    @Override
    public List<Places> getValues()
    {
        return values;
    }
    
    @Override
    protected Places getNew()
    {
        return new Places();
    }
    
    public class Places extends DataBeanHelper
    {
        Place place;

        @Override
        public Place getObject()
        {
            return place;
        }

        @Override
        public void setObject(Place place)
        {
            this.place = place;
        }
        
        @Override
        public String getValue(String prettyName)
        {
            String column = prettyNameToColumnMap.get(prettyName);
            
            String value = table.getColumnMethodMap().get(column, place);
            
            if(value == null)
                value = "";
            else
            {
                int end = value.lastIndexOf("'");;
                
                value = value.substring(1, end);
            }
            
            return value;
        }
    }    
}
