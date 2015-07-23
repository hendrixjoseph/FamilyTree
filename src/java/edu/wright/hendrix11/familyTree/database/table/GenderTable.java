package edu.wright.hendrix11.familyTree.database.table;

import edu.wright.hendrix11.familyTree.database.ColumnMethodMap;
import edu.wright.hendrix11.familyTree.database.DatabaseQuery;
import edu.wright.hendrix11.familyTree.database.interfaces.SelectAllData;
import edu.wright.hendrix11.familyTree.entity.Gender;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
*
* @author Joe Hendrix <hendrix.11@wright.edu>
*/
public class GenderTable extends DatabaseQuery implements SelectAllData<Gender>
{
    public GenderTable()
    {
        super("GENDER", Gender.class);

        ColumnMethodMap columnMethodMap = this.getColumnMethodMap();

        columnMethodMap.setPrimaryKey("ABBR");
    }

    @Override
    public List<Gender> selectAll()
    {
        List<Gender> genders = new ArrayList<Gender>();
    
        List<Object> objects = super.selectAllObjects();
        
        for(Object object : objects)
        {
            if(object instanceof Gender)
                genders.add((Gender)object);
        }
        
        return genders;
    }
    
    @Override
    protected Object getNew()
    {
        return new Gender();
    }
}
