/* 
 *  The MIT License (MIT)
 *  
 *  Copyright (c) 2015 Joseph Hendrix
 *  
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *  
 *  Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
 *  
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

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 * @param <O>
 */
public abstract class DataBean<O>
{

    /**
     *
     */
    protected HashMap<String, String> prettyNameToColumnMap;

    /**
     *
     */
    protected List<DataBeanHelper> values;

    /**
     *
     */
    protected DatabaseQuery table;
    
    /**
     *
     * @return
     */
    public abstract String[] getPrettyNames();
    
    /**
     *
     * @return
     */
    protected abstract String[] getColumns();
    
    /**
     *
     * @param table
     */
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
    
    /**
     *
     */
    protected void setPrettyNameToColumnMap()
    {
        prettyNameToColumnMap = new HashMap<String, String>();
        
        for(int i = 0; i < getColumns().length; i++)
        {
            prettyNameToColumnMap.put(getPrettyNames()[i], getColumns()[i]);
        }
    }
    
    /**
     *
     * @return
     */
    public List<DataBeanHelper> getValues()
    {
        return values.subList(0, Math.min(10, values.size()));
    }
    
    /**
     *
     * @return
     */
    protected String[] getLinkColumns()
    {
        return new String[0];
    }
    
    /**
     *
     * @param column
     * @return
     */
    public boolean isLinkColumn(String column)
    {
        for(String linkColumn : getLinkColumns())
        {
            if(linkColumn.equals(column))
                return true;
        }
        
        return false;
    }
    
    private String process(DataBeanHelper helper, String prettyName)
    {
        String link = "";
        
        if(helper != null && prettyName != null)
            link = processLink(helper, prettyName);
        
        return link;
    }
    
    /**
     *
     * @param helper
     * @param prettyName
     * @return
     */
    protected String processLink(DataBeanHelper helper, String prettyName)
    {
        return "";
    }
    
    /**
     *
     * @param id
     * @return
     */
    protected String linkToPerson(Integer id)
    {
        // <a href="index.xhtml" class="ui-link ui-widget">William Zenos Thoroman</a>
        return "index.xhtml?personId=" + id;
    }
    
    /**
     *
     */
    public class DataBeanHelper
    {
        private O o;
        
        /**
         *
         * @return
         */
        public O getObject()
        {
            return o;
        }

        /**
         *
         * @param o
         */
        public void setObject(O o)
        {
            this.o = o;
        }
        
        /**
         *
         * @param prettyName
         * @return
         */
        public String getLink(String prettyName)
        {
            return process(this, prettyName);
        }
        
        /**
         *
         * @param prettyName
         * @return
         */
        public String getValue(String prettyName)
        {
            String column = prettyNameToColumnMap.get(prettyName);
            
            String value = table.getColumnMethodMap().get(column, o);
            
            if(value == null)
                value = "";
            else if(value == "")
                value = prettyName + " " + column + " " + o.getClass().getSimpleName();
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
