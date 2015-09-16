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
package edu.wright.hendrix11.familyTree.bean.query;

import edu.wright.hendrix11.familyTree.bean.DataBean.DataBeanHelper;
import edu.wright.hendrix11.familyTree.database.table.PlaceTable;
import edu.wright.hendrix11.familyTree.entity.Place;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@ManagedBean
@ViewScoped
public class PlaceQueryBean extends QueryBean
{
    private Place place;
    private PlaceTable placeTable;

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        placeTable = new PlaceTable();
    }
    
    /**
     *
     * @param actionEvent
     */
    @Override
    public void commit(ActionEvent actionEvent)
    {
        placeTable.update(place);
    }
    
    /**
     *
     * @param actionEvent
     */
    public void delete(ActionEvent actionEvent)
    {
        placeTable.delete(place);
    }

    /**
     *
     * @return
     */
    @Override
    public String getAction()
    {
        return "places?" + getRedirectAction();
    }
    
    /**
     *
     * @param place
     */
    public void setPlace(Place place)
    {
        this.place = place;
    }
    
    /**
     *
     * @param helper
     */
    public void setPlace(DataBeanHelper helper)
    {        
        Object object = helper.getObject();
        
        if(object instanceof Place)
            this.place = (Place)object;
    }

    /**
     *
     * @return
     */
    public Place getPlace()
    {
        
        return place;
    }
}
