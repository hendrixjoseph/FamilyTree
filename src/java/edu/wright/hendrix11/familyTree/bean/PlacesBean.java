package edu.wright.hendrix11.familyTree.bean;

import edu.wright.hendrix11.familyTree.database.table.PlaceTable;
import edu.wright.hendrix11.familyTree.entity.Place;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
*
* @author Joe Hendrix
*/
@ManagedBean
@ViewScoped
public class PlacesBean extends DataBean<Place>
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
    
    /**
     *
     * @return
     */
    @Override
    public String[] getPrettyNames()
    {
      return prettyNames;
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String[] getColumns()
    {
        return columns;
    }
    
//    @Override
//    protected String processLink(String column, String value)
//    {
//        return "";
//    }
}