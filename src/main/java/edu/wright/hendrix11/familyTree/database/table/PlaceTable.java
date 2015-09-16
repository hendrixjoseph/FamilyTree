/* 
 *  The MIT License (MIT)
 *  
 *  Copyright (c) 2015 Joseph Hendrix
 *  
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
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
