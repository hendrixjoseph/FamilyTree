
package Database;

import edu.wright.hendrix11.familyTree.database.Database;
import edu.wright.hendrix11.familyTree.database.PersonData;
import edu.wright.hendrix11.familyTree.entity.Person;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class PersonDataTest
{
    private static List<Integer> doNotDelete;
    
    private static Table table;
    
    public static final String propertyFile = "C:\\Program Files\\Apache Software Foundation\\Apache Tomcat 8.0.15\\bin\\database.properties";
    
    public PersonDataTest()
    {        

    }
    
    @BeforeClass
    public static void setUpClass()
    {
        doNotDelete = new ArrayList<Integer>();
        
        table = new Table();
        
        List<Person> persons = table.select();
        
        System.out.println("Do not delete the following ids:");
        
        for(Person person : persons)
        {
            doNotDelete.add(person.getId());
            System.out.print("\t" + person.getId());
        }
        
        System.out.println();
    }
    
    @AfterClass
    public static void tearDownClass()
    { 
        List<Person> persons = table.select();
        
        for(Person person : persons)
        {
            if(!doNotDelete.contains(person.getId()))
            {
                System.out.println("Deleting entry with id=" + person.getId());
                table.delete(person);                
            }
        }
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void test()
    {
        Table table = new Table();
        
        Person p = new Person("John");
        p.setGender("Male");
        
        table.outputInsert(p);
        int id = table.insert(p);
        
        p = new Person();
        p = table.select(id);
        table.outputInsert(p);
    }
    
    public static class Table extends PersonData
    {
        public Table()
        {
            super(propertyFile);
        }
        
        public void outputMap()
        {
            List<String> columns = this.getColumnMethodMap().getColumns();
            HashMap<String, Method> getters = this.getColumnMethodMap().getGetters();
            HashMap<String, Method> setters = this.getColumnMethodMap().getSetters();
            
            System.out.println("Number columns:\t" + columns.size());
            System.out.println("Number getters:\t" + getters.size());
            System.out.println("Number setters:\t" + setters.size());
            
            for(String column : columns)
            {
                String getterName = getters.get(column) != null ? getters.get(column).getName() : "(null)";
                String setterName = setters.get(column) != null ? setters.get(column).getName() : "(null)";
                
                System.out.println(column + "\n\t" + getterName + "\t" + setterName);
            }
        }
        
        public void outputInsert(Person p)
        {
            System.out.println(this.generateInsertQuery(p));
        }
        
    }
}