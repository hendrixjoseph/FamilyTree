package edu.wright.hendrix11.familyTree.database.table;

/**
*
* @author Joe Hendrix <hendrix.11@wright.edu>
*/
public class PlaceTable extends DatabaseQuery implements SelectAllData<Place>
{
    public GenderTable()
    {
        super("PLACE", PLACE.class);
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
