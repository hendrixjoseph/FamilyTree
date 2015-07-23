/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wright.hendrix11.familyTree.bean;

import edu.wright.hendrix11.familyTree.bean.DataBean.DataBeanHelper;
import edu.wright.hendrix11.familyTree.database.DatabaseQuery;
import edu.wright.hendrix11.familyTree.database.interfaces.SelectAllData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 * @param <O>
 * @param <P>
 */
public abstract class DataBean<O,P extends DataBeanHelper>
{
    protected HashMap<String, String> prettyNameToColumnMap;
    protected List<P> values;
    protected DatabaseQuery table;
    
    public abstract String[] getPrettyNames();
    
    public abstract List<P> getValues();
    
    protected abstract String[] getColumns();
    
    protected abstract P getNew();
    
    protected void initialize(DatabaseQuery table)
    {
        List<O> list;
        
        if(table instanceof SelectAllData)
            list = ((SelectAllData)table).selectAll();
        else
            throw new NullPointerException(table.getClass().getName() + " is not an instance of " + SelectAllData.class.getName());
        
        values = new ArrayList<P>();
        
        
        for(O item : list)
        {
            P value = getNew();
            value.setObject(item);
            values.add(value);
        }
        
        setPrettyNameToColumnMap();
    }
    
    protected void setPrettyNameToColumnMap()
    {
        prettyNameToColumnMap = new HashMap<String, String>();
        
        for(int i = 0; i < getColumns().length; i++)
        {
            prettyNameToColumnMap.put(getPrettyNames()[i], getColumns()[i]);
        }
    }
    
    public abstract class DataBeanHelper
    {
        public abstract O getObject();

        public abstract void setObject(O o);
        
        public abstract String getValue(String prettyName);
    }
}
