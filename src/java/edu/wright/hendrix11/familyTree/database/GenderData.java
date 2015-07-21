package edu.wright.hendrix11.familyTree.database;

import edu.wright.hendrix11.familyTree.database.interfaces.SelectAllData;
import edu.wright.hendrix11.familyTree.entity.Gender;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
*
* @author Joe Hendrix <hendrix.11@wright.edu>
*/
public class GenderData extends Database implements SelectAllData<Gender>
{
    public GenderData()
    {
        super("GENDER", Gender.class);

        ColumnMethodMap columnMethodMap = this.getColumnMethodMap();

        columnMethodMap.setPrimaryKey("ABBR");
    }

    @Override
    public List<Gender> selectAll()
    {
        List<Gender> genders = new ArrayList<Gender>();
    
        try
        {
            ResultSet rs = select("SELECT * FROM " + tableName);
            
            while(rs.next())
            {
                Gender gender = new Gender();
                this.setFields(gender, rs);
                genders.add(gender);
            }
            
            closeConnection(rs);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return genders;
    }
}
