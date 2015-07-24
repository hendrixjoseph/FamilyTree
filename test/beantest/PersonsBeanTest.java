/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beantest;

import static database.NonTestClasses.TableTest.propertyFile;
import edu.wright.hendrix11.familyTree.bean.DataBean;
import edu.wright.hendrix11.familyTree.bean.DataBean.DataBeanHelper;
import edu.wright.hendrix11.familyTree.bean.PersonsBean;
import edu.wright.hendrix11.familyTree.database.Database;
import edu.wright.hendrix11.familyTree.database.table.PersonTable;
import edu.wright.hendrix11.familyTree.entity.Person;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class PersonsBeanTest
{
    PersonsBean personsBean;
    PersonTable personTable;
    
    public PersonsBeanTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        Database.setProperties(propertyFile);
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
    public void personTest()
    {
        personTable = new PersonTable();
        
        List<Person> persons = personTable.selectAll();
        
        for(Person person : persons)
        {
            outputPerson(person);
        }
        
        System.out.println("Number of records: " + persons.size());
    }
    
    @Test
    @Ignore
    public void test()
    {
        personsBean = new PersonsBean();
        
        personsBean.initialize();
        
        List<DataBean<Person>.DataBeanHelper> values = personsBean.getValues();
        
        for(DataBeanHelper helper : values)
        {
            Object object = helper.getObject();
            
            if(object instanceof Person)
            {
                Person person = (Person)object;
                                
                outputPerson(person);
            }
        }
        
        System.out.println("Number of records: " + values.size());
    }
    
    public void outputPerson(Person person)
    {
        if(person.hasParents())
        {
            System.out.println(person.getName());
            System.out.println("\tFather: " + person.getFather().getName());
            System.out.println("\tMother: " + person.getMother().getName());
        }
    }
    
}
