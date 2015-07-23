package edu.wright.hendrix11.familyTree.bean;

import edu.wright.hendrix11.familyTree.database.table.MarriageTable;
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
public class MarriagesBean extends DataBean<Place>
{
    private static final String[] columns = {"HUSBAND_ID","HUSBAND_NAME","WIFE_ID","WIFE_NAME","PLACE","ANNIVERSARY"};
    private static final String[] prettyNames = {"Husband Id","Husband Name","Wife Id","Wife Name","Place","Anniversary Date"};

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        table = new MarriageTable();
        
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
    
//    @Override
//    protected String processLink(String column, String value)
//    {
//        return "";
//    }
}
