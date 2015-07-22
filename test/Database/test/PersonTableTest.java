
package DatabaseTests;

import DatabaseTests.NonTestClasses.TestData;
import DatabaseTests.NonTestClasses.DataTest;
import edu.wright.hendrix11.familyTree.database.*;
import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.entity.SpouseChildMap;
import java.util.ArrayList;
import java.util.List;

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
public class PersonDataTest extends DataTest
{
    public static Table table;
    
    public static List<Person> personsBefore;
    
    public boolean withSpouseChildData = true;
    
    public PersonDataTest()
    {        

    }
    
    @BeforeClass
    public static void setUpClass()
    {
        DataTest.setUpClass();
        
        table = new Table();
        
        personsBefore = table.selectAll();
        
        System.out.println("Do not delete the following ids:");
        
        for(Person person : personsBefore)
        {
            doNotDelete.add(person.getId());
            System.out.print("\t" + person.getId());
        }
        
        //System.out.println();
        
        //outputMap(table);
        
        System.out.println();
    }
    
    @AfterClass
    public static void tearDownClass()
    { 
        List<Person> persons = table.selectAll();
        
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
    @Ignore
    public void testWithSpouseChildDate()
    {
        withSpouseChildData = true;
        
        super.testNoErrors();
    }
    
    @Test
    public void testWithoutSpouseChildDate()
    {
        withSpouseChildData = false;
        
        super.testNoErrors();
    }
    
    @Override
    public void test()
    {
        List<Integer> keys = new ArrayList<Integer>();
        
//        for(Person person : personsBefore)
//        {
//            keys.add(person.getId());
//        }
        
        keys.add(1);
        
        for(Integer key : keys)
        {
            Person person;
            
            if(withSpouseChildData)
            {
                person = table.select(key);
                
                for(List<SpouseChildMap> list : person.getSpouseChildMap().values())
                {
                    for(SpouseChildMap map : list)
                    {
                        StringBuilder sb = new StringBuilder();
                        
                        sb.append(map.getPerson().getName()).append("\n");
                        sb.append(map.getSpouse().getName()).append("\n");
                        sb.append(map.getChild().getName()).append("\n");
                        
                        System.out.println(sb.toString());
                    }
                }
            }
            else
            {            
                person = table.select(key, false);
                
                System.out.println(person.toString());
                table.outputInsert(person);
                //table.outputUpdate(person);
            } 
        

        }
    }
    
    public static class Table extends PersonData implements TestData<Person>
    {        
        @Override
        public void outputInsert(Person p)
        {
            System.out.println(this.generateInsertQuery(p));
        }
        
        @Override
        public void outputUpdate(Person p)
        {
            System.out.println(this.generateUpdateQuery(p));
        }
    }
}