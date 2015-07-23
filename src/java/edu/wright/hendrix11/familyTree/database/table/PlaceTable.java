package edu.wright.hendrix11.familyTree.database.table;

import edu.wright.hendrix11.familyTree.database.DatabaseQuery;
import edu.wright.hendrix11.familyTree.database.interfaces.DeleteData;
import edu.wright.hendrix11.familyTree.database.interfaces.SelectAllData;
import edu.wright.hendrix11.familyTree.database.interfaces.UpdateData;
import edu.wright.hendrix11.familyTree.entity.Place;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



/**
*
* @author Joe Hendrix <hendrix.11@wright.edu>
*/
public class PlaceTable extends DatabaseQuery implements SelectAllData<Place>,
                                                         UpdateData<Place>,
                                                         DeleteData<Place>
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

    @Override
    public Place update(Place p)
    {
        String query = generateUpdateQuery(p);
        
        try
        {
            int id = p.getId();
            
            executeUpdate(query);
            
            return p;
        }
        catch(Exception e)
        {
            System.err.println(query);
            e.printStackTrace();
        }
        
        return null;
    }
    
    @Override
    public boolean delete(Place p)
    {
        return super.deleteObject(p);
    }
}
