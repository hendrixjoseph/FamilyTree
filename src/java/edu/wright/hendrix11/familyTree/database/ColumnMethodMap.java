
package edu.wright.hendrix11.familyTree.database;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class ColumnMethodMap extends Database
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

    public HashMap<String, List<Method>> getGetters()
    {
        return getters;
    }

    public HashMap<String, List<Method>> getSetters()
    {
        return setters;
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

            ResultSet rs = select(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            for(int i = 1; i - 1 < rsmd.getColumnCount(); i++)
                columns.add(rsmd.getColumnName(i));

            closeConnection(rs);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return columns;
    }
}