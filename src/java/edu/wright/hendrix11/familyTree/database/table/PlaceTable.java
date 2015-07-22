package edu.wright.hendrix11.familyTree.database.table;

import edu.wright.hendrix11.familyTree.database.DatabaseQuery;
import edu.wright.hendrix11.familyTree.database.interfaces.SelectAllData;
import edu.wright.hendrix11.familyTree.entity.Place;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



/**
*
* @author Joe Hendrix <hendrix.11@wright.edu>
*/
public class PlaceTable extends DatabaseQuery implements SelectAllData<Place>
{
    public PlaceTable()
    {
        super("PLACE", Place.class);
    }

    @Override
    public List<Place> selectAll()
    {
        List<Place> places = new ArrayList<Place>();
    
        try
        {
            ResultSet rs = select("SELECT * FROM " + tableName);
            
            while(rs.next())
            {
                Place place = new Place();
                this.setFields(place, rs);
                places.add(place);
            }
            
            closeStatement(rs);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return places;
    }
}
