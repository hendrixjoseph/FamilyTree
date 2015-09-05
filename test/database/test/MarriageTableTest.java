
package DatabaseTests;

import DatabaseTests.NonTestClasses.TestData;
import DatabaseTests.NonTestClasses.DataTest;
import edu.wright.hendrix11.familyTree.database.MarriageData;
import edu.wright.hendrix11.familyTree.database.PersonData;
import edu.wright.hendrix11.familyTree.entity.Marriage;
import edu.wright.hendrix11.familyTree.entity.Person;
import java.util.Calendar;
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
public class MarriageDataTest extends DataTest
{
    public static Table table;
    
    public static PersonData personData;
    
    @BeforeClass
    public static void setUpClass()
    {
        DataTest.setUpClass();
        
        table = new Table();
        
        personData = new PersonData();
        
        outputMap(table);
    }
    
    @Test
    public void test1()
    {
        super.testNoErrors();
    }
    
    @Override
    public void test()
    {
        List<Person> persons = personData.selectAll();
        
        Person male = getMale(persons);
        Person female = getFemale(persons);
        
        if(male == null || female == null)
            throw new NullPointerException("No males and/or females!");
        
        Marriage m = new Marriage();
        
        m.setHusband(male);
        m.setWife(female);
        
        Calendar cal = Calendar.getInstance();
        
        m.setAnniversary(cal.getTime());
        
        m.setPlace("Alaska");
        
        table.outputInsert(m);
        table.insert(m);
        //table.outputUpdate(m);
    }
    
    private Person getMale(List<Person> persons)
    {
        for(Person person: persons)
        {
            if(person.getGender().equals("Male"))
                return person;
        }

        return null;
    }

    private Person getFemale(List<Person> persons)
    {
        for(Person person: persons)
        {
            if(person.getGender().equals("Female"))
                return person;
        }

        return null;
    }
    
    public static class Table extends MarriageData implements TestData<Marriage>
    {
        @Override
        public void outputInsert(Marriage o)
        {
            System.out.println(this.generateInsertQuery(o));
        }

        @Override
        public void outputUpdate(Marriage o)
        {
            System.out.println(this.generateUpdateQuery(o));
        }        
    }
}
