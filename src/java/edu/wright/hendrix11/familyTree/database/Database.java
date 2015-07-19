
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
        private List<String> columns;
        private HashMap<String, List<Method>> getters;
        private HashMap<String, List<Method>> setters;
        
        private String primaryKey;
        
        private Class clazz;
        
        public static final int GETTER = 1;
        public static final int SETTER = 2;
        
        public ColumnMethodMap(String tableName, Object object)
        {
            this(tableName, object.getClass());
        }
        
        public ColumnMethodMap(String tableName, Class clazz)
        {
            getters = new HashMap<String, List<Method>>();
            setters = new HashMap<String, List<Method>>();
            
            this.clazz = clazz;
            
            HashMap<String, Method> methods = new HashMap<String, Method>();
            
            for(Method m : clazz.getMethods())
            {
                methods.put(m.getName().toUpperCase(), m);
            }
            
            columns = computeColumns(tableName);
            
            for(String column : columns)
            {
                String name = column.replaceAll("_", "").toUpperCase();
                String getterName = "GET" + name;
                String setterName = "SET" + name;
                
                if(methods.get(getterName) != null)
                    put(column, methods.get(getterName), getters);
                
                if(methods.get(setterName) != null)
                    put(column, methods.get(setterName), setters);
            }
        }
        
        public String getClassName()
        {
            return clazz.getName();
        }

        public String getPrimaryKey()
        {
            return primaryKey;
        }

        public void setPrimaryKey(String primaryKey)
        {
            this.primaryKey = primaryKey;
        }
        
        public String getPrimaryKeyValue(Object object)
        {
            if(object == null)
            {
                throw new NullPointerException("Cannot get primary from object of class " + clazz.getName() + " when object is null!");
            }
            
            if(primaryKey == null)
            {
                throw new NullPointerException("Primary key not set for object of class " 
                        + clazz.getName() + "!" +
                        "\nSet it using method setPrimaryKey(String key) in class ColumnMethodMap!");
            }
            
            return get(primaryKey, object);
        }
        
        public String get(String column, Object object)
        {
            List<Method> getterList = getters.get(column);
            
            Object returnObject = object;
            
            String value = null;
            
            if(getterList != null)
            {                
                for(int i = 0; i < getterList.size(); i++)
                {
                    Method getter = getterList.get(i);
            
                    try
                    {
                        if(i == getterList.size() - 1)
                        {
                            value = generateValue(getter.invoke(returnObject));
                        }
                        else
                        {
                            returnObject = getter.invoke(returnObject);
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    
                    if(returnObject == null)
                        break;
                }
            }
            
            return value;
        }
        
        public void set(String column, Object object, ResultSet rs)
        {
            List<Method> setterList = setters.get(column);
            
            Object returnObject = object;
            
            if(setterList != null)
            {
                for(int i = 0; i < setterList.size(); i++)
                {
                    Method setter = setterList.get(i);            

                    try
                    {  
                        if(i == setterList.size() - 1)
                        {
                            Class c = setter.getParameterTypes()[0];
                            String name = c.getSimpleName();
                  
                            if(name.equals("int") || name.equals("Integer"))
                            {
                                int x = rs.getInt(column);
                                
                                // This is needed because rs.getInt()
                                // returns 0 when null.
                                if(!rs.wasNull())
                                    setter.invoke(returnObject, x);
                            }
                            else if(name.equals("String"))
                                setter.invoke(returnObject, rs.getString(column));
                            else if(name.equals("Date"))
                                setter.invoke(returnObject, rs.getDate(column));                        
                        }
                        else
                        {
                            returnObject = setter.invoke(returnObject);
                        }
                    }
                    catch(SQLException sqle)
                    {
                        if(!sqle.getMessage().contains("Invalid column name"))
                            sqle.printStackTrace();
                    }
                    catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                    {
                        System.err.println("Exception when setting from column "
                            + column + " using setter list " +
                            setterList.toString() + "!");
                        ex.printStackTrace();
                        returnObject = null;
                    }
                    
                    if(returnObject == null)
                        break;
                }
            }
        }

        public List<String> getColumns()
        {
            return columns;
        }
        
        protected void putGetter(String column, String methods)
        {
            put(column, methods, getters);
        }
        
        protected void putSetter(String column, String methods)
        {
            put(column, methods, setters);
        }
        
        private void put(String column, String methods, HashMap<String, List<Method>> getterOrSetter)
        {
            String methodList[] = methods.replaceAll(Pattern.quote("()"), "").split(Pattern.quote("."));            
            
            put(column, methodList, getterOrSetter);
        }
        
        private void put(String column, String[] methodNames, HashMap<String, List<Method>> getterOrSetter)
        {
            List<Method> methodList = new ArrayList<Method>();
            
            Method[] methodArray = clazz.getMethods();
            
            for(String methodName : methodNames)
            {                
                Method method = getMethod(methodArray, methodName);
                
                methodList.add(method);
                
                methodArray = method.getReturnType().getMethods();
            }
            
            getterOrSetter.put(column, methodList);
        }
        
        private Method getMethod(Method[] methods, String methodName)
        {
            for(Method method : methods)
            {
                if(method.getName().equals(methodName))
                    return method;
            }
            
            throw new NullPointerException("Method " + methodName + 
                    " does not exist in class " + methods[0].getDeclaringClass().getName()  +"!");
        }
        
        private void put(String column, Method method, HashMap<String, List<Method>> getterOrSetter)
        {
            List<Method> methods = getterOrSetter.get(column);
            
            if(methods == null)
            {
                methods = new ArrayList<Method>();
                getterOrSetter.put(column, methods);
            }
            
            methods.add(method);
        }
        
        private List<String> computeColumns(String tableName)
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
