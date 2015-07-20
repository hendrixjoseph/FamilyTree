
package Database;

import edu.wright.hendrix11.familyTree.database.SpouseChildData;
import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.entity.SpouseChildMap;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class SpouseChildDataTest extends DataTest
{
    public static Table table;
    
    @BeforeClass
    public static void setUpClass()
    {
        DataTest.setUpClass();
        
        table = new Table();
               
        System.out.println("Do not delete the following ids:");        
        
        System.out.println();
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
        outputMap(table);
        
        List<SpouseChildMap> list = table.selectAll();
        
        List<Integer> keys = new ArrayList<Integer>();
        
        for(SpouseChildMap map : list)
        {
            keys.add(map.getPerson().getId());
        }
        
        for(Integer key : keys)
        {
            HashMap<Person, List<SpouseChildMap>> map = table.select(key);
            
            for(List<SpouseChildMap> values : map.values())
            {
                for(SpouseChildMap value : values)
                {
                    System.out.println(value.toString());
                    table.outputInsert(value);
                    table.outputUpdate(value);
                }
            }
        }
    }
    
    public static class Table extends SpouseChildData implements TestData<SpouseChildMap>
    {
        @Override
        public void outputInsert(SpouseChildMap o)
        {
            System.out.println(this.generateInsertQuery(o));
        }

        @Override
        public void outputUpdate(SpouseChildMap o)
        {
            System.out.println(this.generateUpdateQuery(o));
        }   
        
        public List<SpouseChildMap> selectAll()
        {
            List<SpouseChildMap> list = new ArrayList<SpouseChildMap>();
            
            try
            {        
                ResultSet rs = select("SELECT * FROM " + tableName);

                while(rs.next()) 
                {
                    SpouseChildMap map = new SpouseChildMap();
                    this.setFields(map, rs);
                    list.add(map);
                }

                closeConnection(rs);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        
            return list;
        }
    }
}
