
package edu.wright.hendrix11.familyTree.database;

import java.io.*;
import java.lang.reflect.Method;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Joe Hendrix
 */
public abstract class Database<O>
{
    private String url;
    private String user;
    private String pass;
    
    private String propertiesFile;
    
    private Connection con;
    
    private ColumnMethodMap columnMethodMap;
    
    protected String tableName;
    
    public static final String DATE_FORMAT = "MM dd YYYY";
    
    public Database()
    {
        this("database.properties");
    }
    
    public Database(String tableName, String propertiesFile)
    {
        this(propertiesFile);
        this.tableName = tableName;
    }
    
    public Database(String propertiesFile)
    {
        this.propertiesFile = propertiesFile;
        
        this.setProperties();
    }
    
    public abstract O select(int id);
    public abstract boolean update(O o);
    public abstract int insert(O o);
    public abstract boolean delete(O o);

    public ColumnMethodMap getColumnMethodMap()
    {
        return columnMethodMap;
    }

    protected void setColumnMethodMap(String tableName, Object object)
    {
        columnMethodMap = new ColumnMethodMap(tableName, object);
    }
    
    protected void openConnection() throws SQLException
    {
            // Load Oracle JDBC Driver
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection(url, user, pass);
    }
    
    protected void closeConnection() throws SQLException
    {
        con.close();
    }
    
    protected Statement createStatement() throws SQLException
    {
        return con.createStatement();
    }
    
//    protected ResultSet executeQuery(String query) throws SQLException
//    {
//        ResultSet rs = null;
//        
//        openConnection();
//
//        // Convert to try-with-resources
//        // try (Statement statement = con.createStatement())
//        // But I need the openConnection() to be in the try-catch block too!
//        Statement statement = createStatement();
//        rs = statement.executeQuery(query);
//
//        statement.close();
//
//        closeConnection();
//        
//        return rs;
//    }
      
    protected String generateInsertQuery(Object object)
    {
        List<String> columns = columnMethodMap.getColumns();
        HashMap<String, Method> getters = columnMethodMap.getGetters();
        
        StringBuilder query = new StringBuilder();
        
        query.append("INSERT INTO ").append(tableName).append(" (");
        
        StringBuilder columnPart = new StringBuilder();
        StringBuilder valuePart = new StringBuilder();
        
        for(String column : columns)
        {
            Method getter = getters.get(column);
            
            try
            {                
                if(getter != null)
                {
                    String value = generateValue(getter.invoke(object));
                    
                    if(value != null)
                    {
                        columnPart.append(column).append(",");
                        valuePart.append(value).append(",");
                    }
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        columnPart.deleteCharAt(columnPart.length() - 1);
        valuePart.deleteCharAt(valuePart.length() - 1);
        
        query.append(columnPart).append(") VALUES (").append(valuePart).append(")");
        
        return query.toString();
    }
    
    protected void setFields(Object object, ResultSet rs)
    {
        List<String> columns = columnMethodMap.getColumns();
        HashMap<String, Method> setters = columnMethodMap.getSetters();
        
        for(String column : columns)
        {   
            Method setter = setters.get(column);
            
            if(setter != null)
            { 
                Class c = setter.getParameterTypes()[0];
                String name = c.getSimpleName();
                
                try
                {                    
                    if(name.equals("int"))
                        setter.invoke(object, rs.getInt(column));
                    else if(name.equals("String"))
                        setter.invoke(object, rs.getString(column));
                    else if(name.equals("Date"))
                        setter.invoke(object, rs.getDate(column));
                }
                catch(SQLException sqle)
                {
                    if(!sqle.getMessage().contains("Invalid column name"))
                        sqle.printStackTrace();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }
    
    private String generateValue(String string)
    {
        return "'" + string + "'";
    }
    
    private String generateValue(Integer i)
    {
        return generateValue(Integer.toString(i));
    }
    
    private String generateValue(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        
        StringBuilder toDate = new StringBuilder();
        
        toDate.append("TO_DATE('").append(dateFormat.format(date));
        toDate.append("', '").append(DATE_FORMAT).append("')");
        
        return toDate.toString();
    }
    
    private String generateValue(Object object)
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
        
    public void setProperties()
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
    
    public void setProperities(String url, String user, String pass)
    {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    
    public void writeProperties()
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
    
    protected class ColumnMethodMap
    {
        List<String> columns;
        HashMap<String, Method> getters;
        HashMap<String, Method> setters;
        
        public ColumnMethodMap(String tableName, Object object)
        {
            getters = new HashMap<String, Method>();
            setters = new HashMap<String, Method>();
            
            HashMap<String, Method> methods = new HashMap<String, Method>();
            
            for(Method m : object.getClass().getMethods())
            {
                methods.put(m.getName().toUpperCase(), m);
            }
            
            columns = getColumns(tableName);
            
            for(String column : columns)
            {
                String name = column.replaceAll("_", "").toUpperCase();
                String getterName = "GET" + name;
                String setterName = "SET" + name;
                
                if(methods.get(getterName) != null)
                    getters.put(column, methods.get(getterName));
                
                if(methods.get(setterName) != null)
                    setters.put(column, methods.get(setterName));
            }
        }

        public List<String> getColumns()
        {
            return columns;
        }

        public HashMap<String, Method> getGetters()
        {
            return getters;
        }

        public HashMap<String, Method> getSetters()
        {
            return setters;
        }
        
        private List<String> getColumns(String tableName)
        {
            List<String> columns = new ArrayList<String>();
            
            try
            {
                String query = "SELECT * FROM " + tableName;
                
                openConnection();
                Statement statement = createStatement();
                ResultSet rs = statement.executeQuery(query);
                ResultSetMetaData rsmd = rs.getMetaData();
                
                for(int i = 1; i - 1 < rsmd.getColumnCount(); i++)
                    columns.add(rsmd.getColumnName(i));
                
                rs.close();
                statement.close();
                closeConnection();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
            
            return columns;
        }
    }
}
