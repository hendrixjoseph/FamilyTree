
package edu.wright.hendrix11.familyTree.database.table;

import edu.wright.hendrix11.familyTree.database.ColumnMethodMap;
import edu.wright.hendrix11.familyTree.database.Database;
import edu.wright.hendrix11.familyTree.database.interfaces.SelectData;
import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.entity.SpouseChildMap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class SpouseChildTable extends Database implements SelectData<HashMap<Person, List<SpouseChildMap>>, Integer>
{    

    /**
     *
     */
    public SpouseChildTable()
    {
        super("CHILDREN_VIEW",SpouseChildMap.class);
        
        ColumnMethodMap columnMethodMap = this.getColumnMethodMap();
        
        columnMethodMap.putGetter("ID","getPerson().getId()");
        columnMethodMap.putSetter("ID","getPerson().setId()");
        
        columnMethodMap.putGetter("NAME","getPerson().getName()");
        columnMethodMap.putSetter("NAME","getPerson().setName()");
        
        columnMethodMap.putGetter("SPOUSE_ID","getSpouse().getId()");
        columnMethodMap.putSetter("SPOUSE_ID","getSpouse().setId()");
        
        columnMethodMap.putGetter("SPOUSE","getSpouse().getName()");
        columnMethodMap.putSetter("SPOUSE","getSpouse().setName()");
        
        columnMethodMap.putGetter("CHILD_ID","getChild().getId()");
        columnMethodMap.putSetter("CHILD_ID","getChild().setId()");
        
        columnMethodMap.putGetter("CHILD","getChild().getName()");
        columnMethodMap.putSetter("CHILD","getChild().setName()");
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public HashMap<Person, List<SpouseChildMap>> select(Integer id)
    {
        HashMap<Person, List<SpouseChildMap>> map = new HashMap<Person, List<SpouseChildMap>>();
        
        try        
        {
            ResultSet rs = selectWithKey(id);
            
            while(rs.next())
            {
                SpouseChildMap spouseChildMap = new SpouseChildMap();
                
                this.setFields(spouseChildMap, rs);
                
                Person spouse = spouseChildMap.getSpouse();
                
                if(!spouse.exists())
                    spouse = null;
                    
                List<SpouseChildMap> childList = map.get(spouse);                    
                
                if(childList == null)
                {
                    childList = new ArrayList<SpouseChildMap>();
                    map.put(spouse, childList);
                }
                
                childList.add(spouseChildMap);
            }
            
            closeStatement(rs);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return map;
    }    
}
