
package edu.wright.hendrix11.familyTree.database;

import java.io.*;
import java.sql.*;
import java.util.Properties;

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
    public abstract O update(O o);
    public abstract O insert(O o);
    public abstract O delete(O o);
    
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
        url = url;
        user = user;
        pass = pass;
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
