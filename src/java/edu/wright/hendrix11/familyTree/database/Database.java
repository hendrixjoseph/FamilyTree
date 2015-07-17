
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
        if(object == null)
        {
            throw new NullPointerException("Cannot generate insert query for null object!");
        }
        
        List<String> columns = columnMethodMap.getColumns();
        HashMap<String, List<Method>> getters = columnMethodMap.getGetters();
        
        StringBuilder query = new StringBuilder();
        
        query.append("INSERT INTO ").append(tableName).append(" (");
        
        StringBuilder columnPart = new StringBuilder();
        StringBuilder valuePart = new StringBuilder();
        
        for(String column : columns)
        {
            List<Method> getterList = getters.get(column);
            
            Object returnObject = object;
            
            if(getterList != null)
            {                
                for(int i = 0; i < getterList.size(); i++)
                {
                    Method getter = getterList.get(i);
            
                    try
                    {
                        if(i == getterList.size() - 1)
                        {
                            String value = generateValue(getter.invoke(returnObject));

                            if(value != null)
                            {
                                columnPart.append(column).append(",");
                                valuePart.append(value).append(",");
                            }
                        }
                        else
                        {
                            returnObject = getter.invoke(returnObject);
                        }
                    }
                    catch(NullPointerException npe)
                    {
                        System.err.println("Object " + getter.getDeclaringClass().getName() +
                                " is null!");
                        System.err.println("Method " + getter.getName() + 
                                "() cannot access value for " + column + "! ");
                        npe.printStackTrace();
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    
                    if(returnObject == null)
                        break;
                }
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
        HashMap<String, List<Method>> setters = columnMethodMap.getSetters();
        
        for(String column : columns)
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
                                setter.invoke(returnObject, rs.getInt(column));
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
                    catch(NullPointerException npe)
                    {
                        System.err.println("Object " + setter.getDeclaringClass().getName() +
                            " is null!");
                        System.err.println("Method " + setter.getName() + 
                            "() cannot insert value for column " + column + "! ");
                        npe.printStackTrace();
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        return;
                    }
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
        HashMap<String, List<Method>> getters;
        HashMap<String, List<Method>> setters;
        
        Object object;
        
        public static final int GETTER = 1;
        public static final int SETTER = 2;
        
        public ColumnMethodMap(String tableName, Object object)
        {
            getters = new HashMap<String, List<Method>>();
            setters = new HashMap<String, List<Method>>();
            
            this.object = object;
            
            HashMap<String, Method> methods = new HashMap<String, Method>();
            
            for(Method m : object.getClass().getMethods())
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

        public List<String> getColumns()
        {
            return columns;
        }

        public HashMap<String, List<Method>> getGetters()
        {
            return getters;
        }

        public HashMap<String, List<Method>> getSetters()
        {
            return setters;
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
            
            Method[] methodArray = this.object.getClass().getMethods();
            
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
