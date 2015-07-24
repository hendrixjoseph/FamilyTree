/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtest;

import static database.NonTestClasses.TableTest.propertyFile;
import edu.wright.hendrix11.familyTree.database.Database;
import edu.wright.hendrix11.familyTree.database.table.PersonTable;
import edu.wright.hendrix11.familyTree.database.table.SpouseChildTable;
import edu.wright.hendrix11.familyTree.entity.Person;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class PersonHashTest
{
    PersonTable table;
    SpouseChildTable scTable;
    
    HashMap<Person, List<Integer>> testMap;
    
    public static Integer personId = 7853;
    
    public PersonHashTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        try
        {
            Database.setProperties(propertyFile);
            Database.openConnection();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        try
        {
            Database.closeConnection();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
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
    public void test3()
    {
        scTable = new SpouseChildTable();
        
        HashMap<Person, List<Person>> map = scTable.select(personId);
        
        outputMap(map);
    }
    
    public void outputMap(HashMap<Person, List<Person>> map)
    {
        Set<Person> keySet = map.keySet();
        
        Person prev = null;
        
        for(Person p : keySet)
        {
            if(prev != null)
            {
                System.out.println("(p == prev) " + (p == prev));
                System.out.println("p.equals(prev) " + p.equals(prev));                
            }
            
            prev = p;
            
            System.out.println("\t" + p.getId() + " " + p.getName() + " " + p.hashCode());
            
            for(Person c : map.get(p))
            {
                System.out.println("\t\t" + c.getId() + " " + c.getName());
            }
        }
    }
    
    @Test
    @Ignore
    public void test()
    {
        testMap = new HashMap<Person, List<Integer>>();
        

        
        List<Integer> list = new ArrayList<>();
        
        Random random = new Random();
        
        for(int i = 0; i < 5; i++)
        {
            Person person = new Person();
            person.setId(1);
            person.setName("Joe");
            
            List<Integer> integers = testMap.get(person);
            
            if(integers == null)
                integers = new ArrayList<Integer>();
            
            integers.add(random.nextInt());
            
            testMap.put(person, integers);
        }
        
//        list = new ArrayList<>();
//        
//        for(int i = 0; i < 30; i++)
//        {
//            list.add(random.nextInt());
//        }
//        
//        testMap.put(person, list);
        
        for(Person p : testMap.keySet())
        {
            System.out.println(p.getId() + " " + p.getName() + " " + p.hashCode());
            
            List<Integer> get = testMap.get(p);
            
            for(Integer i : get)
            {
                System.out.println("\t" + i);
            }
        }
    }
    
}
