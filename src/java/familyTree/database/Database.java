/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familyTree.database;

import java.io.*;
import java.sql.*;
import java.util.Properties;

import familyTree.entity.Person;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Joe
 */
public class Database
{
    private static String url;
    private static String user;
    private static String pass;
    
    private static final String propertiesFile = "database.properties";
    
    private static Connection con;
    
    private static void openConnection() throws SQLException
    {
            // Load Oracle JDBC Driver
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection(url, user, pass);
    }
    
    private static void closeConnection() throws SQLException
    {
        con.close();
    }
    
    public static Person getPerson(int id)
    {
        Person person = null;
        
        try
        {
            openConnection();

            // Convert to try-with-resources
            // try (Statement statement = con.createStatement())
            // But I need the openConnection() to be in the try-catch block too!
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM PERSON_VIEW WHERE ID=" + id);
            
            if(rs.next()) 
            {
                person = new Person(rs);
            }

            rs.close();
            statement.close();
            
            closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return person;
    }
    
    public static HashMap<Person, ArrayList<Person>> getChildren(int id)
    {
        HashMap<Person, ArrayList<Person>> spouseChildTable = new HashMap<Person, ArrayList<Person>>();  
        ArrayList<Person> children;
        
        try
        {
            openConnection();

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM CHILDREN_VIEW WHERE ID=" + id);
            
            Person child;
            Person spouse;
            
            while(rs.next())
            {
                spouse = new Person(rs.getString("SPOUSE"));
                child = new Person(rs.getString("CHILD"));
                
                if(spouseChildTable.get(spouse) == null)
                    spouseChildTable.put(spouse, new ArrayList<Person>());
                
                children = spouseChildTable.get(spouse);
                
                children.add(child);
            }
            
            rs.close();
            statement.close();
            
            closeConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return spouseChildTable;
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
    
    public static void setProperities(String url, String user, String pass)
    {
        Database.url = url;
        Database.user = user;
        Database.pass = pass;
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
}
