/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wright.hendrix11.familyTree.bean;

import edu.wright.hendrix11.familyTree.bean.ChildrenBean.ChildrenBeanHelper;
import edu.wright.hendrix11.familyTree.database.table.SpouseChildTable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@ManagedBean
@ViewScoped
public class ChildrenBean extends DataBean<SpouseChildTable,ChildrenBeanHelper>
{
    private static final String[] columns = {"ID","NAME","SPOUSE_ID","SPOUSE","CHILD_ID","CHILD"};
    private static final String[] prettyNames = {"Id","Name","Spouse Id","Spouse Name","Child Id","Child Name"};

    public void initialize()
    {
        table = new SpouseChildTable();
        
        super.initialize(table);
    }
    
    @Override
    public String[] getPrettyNames()
    {
        return prettyNames;
    }
    
    @Override
    public String[] getColumns()
    {
        return columns;
    }

    @Override
    public List<ChildrenBeanHelper> getValues()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected ChildrenBeanHelper getNew()
    {
        return new ChildrenBeanHelper();
    }
    
    public class ChildrenBeanHelper extends DataBeanHelper
    {

        @Override
        public SpouseChildTable getObject()
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setObject(SpouseChildTable o)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getValue(String prettyName)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
