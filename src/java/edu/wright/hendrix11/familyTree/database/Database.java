
package edu.wright.hendrix11.familyTree.database;

import java.io.*;
import java.sql.*;
import java.util.Properties;

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
    
    private static Connection con;
    private Statement statement;
    
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
    public Database()
    {
    }
        
    /**
     *
     * @param url
     * @param user
     * @param pass
     */
    public static void setProperities(String url, String user, String pass)
    {
        Database.url = url;
        Database.user = user;
        Database.pass = pass;
    }
    
    /**
     *
     * @param propertiesFile
     */
    public static void setProperties(String propertiesFile)
    {
        Database.propertiesFile = propertiesFile;
        setProperties();
    }
    
    /**
     *
     */
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
    
    /**
     *
     */
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
    
    /**
     *
     * @throws SQLException
     */
    public static void openConnection() throws SQLException
    {        
        if(url == null || user == null || pass == null)
        {
            setProperties("database.properties");
        }
        
        // Load Oracle JDBC Driver
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        con = DriverManager.getConnection(url, user, pass);
    }
    
    private static Connection getConnection() throws SQLException
    {
        if(con == null)
            openConnection();
            
        return con;
    }
    
    /**
     *
     * @param rs
     * @throws SQLException
     */
    protected void closeStatement(ResultSet rs) throws SQLException
    {
        rs.close();
        closeStatement();
    }
    
    protected void closeStatement() throws SQLException
    {
        statement.close();
    }
    
    /**
     *
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException
    {
        con.close();
    }
    
    /**
     *
     * @return
     * @throws SQLException
     */
    protected Statement createStatement() throws SQLException
    {
        return getConnection().createStatement();
    }
    
    protected ResultSet executeQuery(String query) throws SQLException
    {
        statement = createStatement();
        return statement.executeQuery(query);
    }
    
    /**
     *
     * @param query
     * @throws SQLException
     */
    protected void executeUpdate(String query) throws SQLException
    {
        //openConnection();        
        statement = createStatement();
        statement.executeUpdate(query);        
        statement.close();
        //closeConnection();
    }
}
