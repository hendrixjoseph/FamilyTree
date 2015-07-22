
package edu.wright.hendrix11.familyTree.database;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Joe Hendrix
 */
public abstract class DatabaseQuery extends Database
{    
    private ColumnMethodMap columnMethodMap;
    
    /**
     *
     */
    protected String tableName;
    
    /**
     *
     */
    public static final String DATE_FORMAT = "MM dd YYYY";    
    
    /**
     *
     */
    public DatabaseQuery()
    {
    }
    
    /**
     *
     * @param tableName
     * @param clazz
     */
    public DatabaseQuery(String tableName, Class clazz)
    {
        this();
        this.tableName = tableName;
        columnMethodMap = new ColumnMethodMap(tableName, clazz);
    }

    /**
     *
     * @return
     */
    public ColumnMethodMap getColumnMethodMap()
    {
        return columnMethodMap;
    }
    
    /**
     *
     * @param key
     * @return
     * @throws SQLException
     */
    protected ResultSet selectWithKey(Object key) throws SQLException
    {
        String primaryKey = columnMethodMap.getPrimaryKey().get(0);
        
        return selectWithKey(primaryKey, key);
    }
    
    protected ResultSet selectWithKey(String primaryKey, Object key) throws SQLException
    {        
        String query = "SELECT * FROM " + tableName + " WHERE " + primaryKey + "=" + key;
        
        if(primaryKey == null || key == null)
        {
            System.err.println(query);
            throw new NullPointerException("Either primaryKey is not set or key value given is null!");
        }

        return select(query);
    }
    
    protected ResultSet selectWithKeys(List<Object> keys) throws SQLException
    {
        List<String> primaryKey = columnMethodMap.getPrimaryKey();
        
        if(primaryKey == null || keys == null)
        {
            throw new NullPointerException("Either primaryKey is not set or key value given is null!");
        }                      
        
        StringBuilder query = new StringBuilder();
        
        query.append("SELECT * FROM ").append(tableName).append(" WHERE ");
        
        int max = Math.max(keys.size(), primaryKey.size());
        
        for(int i = 0; i < max; i++)
        {
            String pk = primaryKey.get(i);
            String k = keys.get(i).toString();
            
            query.append(pk).append("=").append(k);
            
            if(i < max - 1)
                query.append(" AND ");
        }

        return select(query.toString());
    }
    
    /**
     *
     * @param query
     * @return
     * @throws SQLException
     */
    protected ResultSet select(String query) throws SQLException
    {
        //openConnection();
        statement = createStatement();
        return statement.executeQuery(query);
    }
    
    /**
     *
     * @param object
     * @return
     */
    protected String generateUpdateQuery(Object object)
    {
        if(object == null)
        {
            throw new NullPointerException("Cannot generate update query for null object!");
        }
        
        List<String> columns = columnMethodMap.getColumns();
        
        StringBuilder query = new StringBuilder();
        
        query.append("UPDATE ").append(tableName).append(" SET ");
        
        for(String column : columns)
        {
            if(!columnMethodMap.getPrimaryKey().contains(column))
            {
                String value = columnMethodMap.get(column,object);
                
                if(value != null)
                    query.append(column).append("=").append(value).append(",");
            }
        }
        
        query.deleteCharAt(query.length() - 1);
        
        query.append(" WHERE ").append(columnMethodMap.getPrimaryKey().get(0));
        query.append("=").append(columnMethodMap.getPrimaryKeyValue(object));
        
        return query.toString();
    }
      
    /**
     *
     * @param object
     * @return
     */
    protected String generateInsertQuery(Object object)
    {
        if(object == null)
        {
            throw new NullPointerException("Cannot generate insert query for null object!");
        }
        
        StringBuilder query = new StringBuilder();
        List<String> columns = columnMethodMap.getColumns();
        
        query.append("INSERT INTO ").append(tableName).append(" (");
        
        StringBuilder columnPart = new StringBuilder();
        StringBuilder valuePart = new StringBuilder();
        
        for(String column : columns)
        {
            String value = columnMethodMap.get(column,object);
            
            if(value != null)
            {
                columnPart.append(column).append(",");
                valuePart.append(value).append(",");
            }
        }
        
        columnPart.deleteCharAt(columnPart.length() - 1);
        valuePart.deleteCharAt(valuePart.length() - 1);
        
        query.append(columnPart).append(") VALUES (").append(valuePart).append(")");
        
        return query.toString();
    }
    
    /**
     *
     * @param object
     * @param rs
     */
    protected void setFields(Object object, ResultSet rs)
    {
        if(object == null)
        {
            StringBuilder message = new StringBuilder();
            
            message.append("Object cannot be null!\n");
            message.append("Object should be of class ");
            message.append(columnMethodMap.getClassName()).append("!\n");
            
            throw new NullPointerException(message.toString());
        }
        
        List<String> columns = columnMethodMap.getColumns();
        
        for(String column : columns)
        {   
            columnMethodMap.set(column, object, rs);
        }
    }
    
    /**
     *
     * @param string
     * @return
     */
    protected String generateValue(String string)
    {
        string = string.replaceAll("'", "''");
        
        return "'" + string + "'";
    }
    
    /**
     *
     * @param i
     * @return
     */
    protected String generateValue(Integer i)
    {
        return generateValue(Integer.toString(i));
    }
    
    /**
     *
     * @param date
     * @return
     */
    protected String generateValue(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        
        StringBuilder toDate = new StringBuilder();
        
        toDate.append("TO_DATE('").append(dateFormat.format(date));
        toDate.append("', '").append(DATE_FORMAT).append("')");
        
        return toDate.toString();
    }
    
    /**
     *
     * @param object
     * @return
     */
    protected String generateValue(Object object)
    {
        if(object instanceof String)
            return generateValue((String)object);
        else if(object instanceof Date)
            return generateValue((Date)object);
        else if(object instanceof Integer)
            return generateValue((Integer)object);
        else if(object == null)
            return null;
        
        return "'Object'";
    }
}
