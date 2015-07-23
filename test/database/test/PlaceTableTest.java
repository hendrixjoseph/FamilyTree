/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.test;

import database.NonTestClasses.TableTest;
import database.NonTestClasses.TestData;
import edu.wright.hendrix11.familyTree.database.table.PlaceTable;
import edu.wright.hendrix11.familyTree.entity.Place;
import java.util.List;
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
public class PlaceTableTest extends TableTest
{
    public static Table table;
    
    public PlaceTableTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        TableTest.setUpClass();
        
        table = new Table();
        
        outputMap(table);
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        TableTest.tearDownClass();
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
    @Override
     public void testNoErrors()
     {
         super.testNoErrors();
     }

    @Override
    public void test()
    {
        List<Place> places = table.selectAll();
        
        for(Place place : places)
        {
            table.outputInsert(place);
            table.outputUpdate(place);
        }
    }
    
    public static class Table extends PlaceTable implements TestData<Place>
    {        
        @Override
        public void outputInsert(Place p)
        {
            System.out.println(this.generateInsertQuery(p));
        }
        
        @Override
        public void outputUpdate(Place p)
        {
            System.out.println(this.generateUpdateQuery(p));
        }
    }
}
