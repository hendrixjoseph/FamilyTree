
package edu.wright.hendrix11.familyTree.database;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Date;
import java.util.HashMap;

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
    
    public static final String DATE_FORMAT = "MM DD YYYY";
    
    public Database()
    {
        this("database.properties");
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
    
    protected ResultSet executeQuery(String query) throws SQLException
    {
        ResultSet rs = null;
        
        openConnection();

        // Convert to try-with-resources
        // try (Statement statement = con.createStatement())
        // But I need the openConnection() to be in the try-catch block too!
        Statement statement = createStatement();
        rs = statement.executeQuery(query);

        statement.close();

        closeConnection();
        
        return rs;
    }
    
    protected String createToDate(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        
        StringBuilder toDate = new StringBuilder();
        
        toDate.append("TO_DATE('").append(dateFormat.format(date));
        toDate.append("', '").append(DATE_FORMAT).append("')");
        
        return toDate.toString();
    }
    
    protected String createInsertQuery(String table, HashMap<String, Object> columnValueMap)
    {
        StringBuilder query = new StringBuilder();
        
        query.append("INSERT INTO ").append(table).append("(");
        
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        
        String[] keys = (String[])columnValueMap.keySet().toArray();
        
        for(int i = 0; i < keys.length; i++)
        {
            columns.append(keys[i]);
            values.append(columnValueMap.get(keys[i]));
            
            if(i + 1 < keys.length)
            {
                columns.append(",");
                values.append(",");
            }
        }
        
        query.append(columns).append(") VALUES (").append(values).append(")");
        
        return query.toString();
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
}
