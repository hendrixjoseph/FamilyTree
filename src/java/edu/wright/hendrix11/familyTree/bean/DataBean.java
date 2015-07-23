/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wright.hendrix11.familyTree.bean;

import edu.wright.hendrix11.familyTree.database.DatabaseQuery;
import edu.wright.hendrix11.familyTree.database.interfaces.SelectAllData;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 * @param <O>
 * @param <P>
 */
public abstract class DataBean<O>
{
    protected HashMap<String, String> prettyNameToColumnMap;
    protected List<DataBeanHelper> values;
    protected DatabaseQuery table;
    protected DataBeanHelper helper;
    
    public abstract String[] getPrettyNames();
    
    protected abstract String[] getColumns();
    
    protected void initialize(DatabaseQuery table)
    {
        List<O> list;
        
        if(table instanceof SelectAllData)
            list = ((SelectAllData)table).selectAll();
        else
            throw new NullPointerException(table.getClass().getName() + " is not an instance of " + SelectAllData.class.getName());
        
        values = new ArrayList<DataBeanHelper>();        
        
        for(O item : list)
        {
            DataBeanHelper value = new DataBeanHelper();
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
    
    public List<DataBeanHelper> getValues()
    {
        return values;
    }
    
    public class DataBeanHelper
    {
        private O o;
        
        public O getObject()
        {
            return o;
        }

        public void setObject(O o)
        {
            this.o = o;
        }
        
        public String getValue(String prettyName)
        {
            String column = prettyNameToColumnMap.get(prettyName);
            
            String value = table.getColumnMethodMap().get(column, o);
            
            if(value == null)
                value = "";
            else if(value.startsWith("TO_DATE('"))
            {
                DateFormat inFormat = new SimpleDateFormat("MM dd YYYY");
                DateFormat outFormat = new SimpleDateFormat("MMM dd YYYY");
                
                // Example:
                // TO_DATE('07 20 2015', 'MM dd YYYY')
                
                int start = "TO_DATE('".length() + 1;
                int end = value.indexOf("', 'MM dd YYYY')");
                
                value = value.substring(start, end);
                
                try
                {
                    value = outFormat.format(inFormat.parse(value));
                }
                catch (ParseException ex)
                {
                    value = "Unparsable date";
                }
            }
            else
            {
                int end = value.lastIndexOf("'");
                
                value = value.substring(1, end);
            }
            
            return value;
        }
    }
}
