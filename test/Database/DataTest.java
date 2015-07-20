
package Database;

import static Database.PersonDataTest.propertyFile;
import edu.wright.hendrix11.familyTree.database.Database;
import edu.wright.hendrix11.familyTree.entity.Person;
import java.lang.reflect.Method;
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
public abstract class DataTest
{
    public static List<Integer> doNotDelete;
    
    public static final String propertyFile = "C:\\Program Files\\Apache Software Foundation\\Apache Tomcat 8.0.15\\bin\\database.properties";
    
    public static void setUpClass()
    {
        Database.setProperties(propertyFile);
        
        doNotDelete = new ArrayList<Integer>();
    }
    
    public void testNoErrors()
    {
        try
        {
            test();
        }
        catch(Exception e)
        {
            Assert.fail("Exception " + e.getClass().getName() + " occurred!");
            e.printStackTrace();
        }
    }
    
    public abstract void test();
    
    public static void outputMap(Database table)
    {
        List<String> columns = table.getColumnMethodMap().getColumns();
        HashMap<String, List<Method>> getters = table.getColumnMethodMap().getGetters();
        HashMap<String, List<Method>> setters = table.getColumnMethodMap().getSetters();

        System.out.println("Number columns:\t" + columns.size());
        System.out.println("Number getters:\t" + getters.size());
        System.out.println("Number setters:\t" + setters.size());

        for(String column : columns)
        {
            List<Method> getter = getters.get(column);
            List<Method> setter = setters.get(column);

            StringBuilder getterName = new StringBuilder("(null)");
            StringBuilder setterName = new StringBuilder("(null)");

            if(getter != null)
            {
                getterName = new StringBuilder();

                for(Method g : getter)
                {
                    getterName.append(g.getName()).append("().");
                }

                getterName.deleteCharAt(getterName.length() - 1);
            }

            if(setter != null)
            {
                setterName = new StringBuilder();

                for(Method s : setter)
                {
                    setterName.append(s.getName()).append("().");
                }

                setterName.deleteCharAt(setterName.length() - 1);
            }

            StringBuilder output = new StringBuilder();
            output.append(column);
            output.append("\nGetter:\t").append(getterName);
            output.append("\nSetter:\t").append(setterName);

            System.out.println(output.toString());
        }
    }
}
