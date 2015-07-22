/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseTests;

import DatabaseTests.NonTestClasses.DataTest;
import edu.wright.hendrix11.familyTree.database.GenderData;
import edu.wright.hendrix11.familyTree.entity.Gender;
import java.util.List;
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
public class GenderDataTest extends DataTest
{
    public static GenderData table;
    
    public GenderDataTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        DataTest.setUpClass();
        
        table = new GenderData();
        
        outputMap(table);
    }
    
    @AfterClass
    public static void tearDownClass()
    {
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
    public void test1()
    {
       super.testNoErrors();
    }
    
    @Override
    public void test()
    {
        List<Gender> genders = table.selectAll();
        
        for(Gender gender : genders)
        {
            System.out.println(gender.getAbbr() + "\t" + gender.getFullWord());
        }
    }
}
