/* 
 *  The MIT License (MIT)
 * 
 *  View the full license at:
 *  https://github.com/hendrixjoseph/FamilyTree/blob/master/LICENSE.md
 *  
 *  Copyright (c) 2015 Joseph Hendrix
 *  
 *  Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
 *  
 */
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

    /**
     *
     */
    public PlaceTable()
    {
        super("PLACE", Place.class);
    }

    @Override
    public List<Place> selectAll()
    {
        List<Place> places = new ArrayList<Place>();
    
        List<Object> objects = super.selectAllObjects();
        
        for(Object object : objects)
        {
            if(object instanceof Place)
                places.add((Place)object);
        }
        
        return places;
    }
    
    /**
     *
     * @return
     */
    @Override
    protected Object getNew()
    {
        return new Place();
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
