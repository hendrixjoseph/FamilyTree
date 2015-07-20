
package edu.wright.hendrix11.familyTree.database;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Joe Hendrix
 */
public abstract class Database
{
    private static String url = null;
    private static String user = null;
    private static String pass = null;
    
    private static String propertiesFile;    
    
    private Connection con;
    private Statement statement;
    
    private ColumnMethodMap columnMethodMap;
    
    protected String tableName;
    
    public static final String DATE_FORMAT = "MM dd YYYY";    
    
    public Database()
    {
        if(url == null || user == null || pass == null)
        {
            setProperties("database.properties");
        }
    }
    
    public Database(String tableName, Class clazz)
    {
        this();
        this.tableName = tableName;
        columnMethodMap = new ColumnMethodMap(tableName, clazz);
    }
    
    public static void setProperities(String url, String user, String pass)
    {
        Database.url = url;
        Database.user = user;
        Database.pass = pass;
    }
    
    public static void setProperties(String propertiesFile)
    {
        Database.propertiesFile = propertiesFile;
        setProperties();
    }
    
    public static void setProperties()
    {
        try
        {
            Properties prop = new Properties();
            InputStream input = new FileInputStream(propertiesFile);
            prop.load(input);
            
            url = prop.getProperty("database");
            user = prop.getProperty("dbuser");
            pass = prop.getProperty("dbpassword");
            
            input.close();
        }
        catch(Exception e)
        {
            System.out.println("Failed to load database properties.");
            System.out.println("Does file " + propertiesFile + " exist?");
            e.printStackTrace();
        }
    }
    
    public static void writeProperties()
    {
        if(url == null || user == null || pass == null)
        {
            System.out.println("Either url, user, or pass is null.");
            throw new NullPointerException();
        }
        
        try
        {
            Properties prop = new Properties();
            OutputStream output = new FileOutputStream(propertiesFile);
            
            prop.setProperty("database", url);
            prop.setProperty("dbuser", user);
            prop.setProperty("dbpassword", pass);
        
            prop.store(output, null);
            
            output.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public ColumnMethodMap getColumnMethodMap()
    {
        return columnMethodMap;
    }
    
    protected void openConnection() throws SQLException
    {
            // Load Oracle JDBC Driver
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection(url, user, pass);
    }
    
    protected void closeConnection(ResultSet rs) throws SQLException
    {
        rs.close();
        closeConnection();
    }
    
    protected void closeConnection() throws SQLException
    {
        statement.close();
        con.close();
    }
    
    protected Statement createStatement() throws SQLException
    {
        return con.createStatement();
    }
    
    protected ResultSet selectWithKey(Object key) throws SQLException
    {
        String primaryKey = columnMethodMap.getPrimaryKey();
        
        String query = "SELECT * FROM " + tableName + " WHERE " + primaryKey + "=" + key;
        
        if(primaryKey == null || key == null)
        {
            System.err.println(query);
            throw new NullPointerException("Either primaryKey is not set or key value given is null!");
        }

        return select(query);
    }
    
    protected ResultSet select(String query) throws SQLException
    {
        openConnection();
        statement = createStatement();
        return statement.executeQuery(query);
    }
    
    protected void executeUpdate(String query) throws SQLException
    {
        openConnection();
        statement = createStatement();
        statement.executeUpdate(query);
        statement.close();
        closeConnection();
    }
    
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
            if(!column.equals(columnMethodMap.getPrimaryKey()))
            {
                String value = columnMethodMap.get(column,object);
                
                if(value != null)
                    query.append(column).append("=").append(value).append(",");
            }
        }
        
        query.deleteCharAt(query.length() - 1);
        
        query.append(" WHERE ").append(columnMethodMap.getPrimaryKey());
        query.append("=").append(columnMethodMap.getPrimaryKeyValue(object));
        
        return query.toString();
    }
      
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
    
    protected String generateValue(String string)
    {
        return "'" + string + "'";
    }
    
    protected String generateValue(Integer i)
    {
        return generateValue(Integer.toString(i));
    }
    
    protected String generateValue(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        
        StringBuilder toDate = new StringBuilder();
        
        toDate.append("TO_DATE('").append(dateFormat.format(date));
        toDate.append("', '").append(DATE_FORMAT).append("')");
        
        return toDate.toString();
    }
    
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
