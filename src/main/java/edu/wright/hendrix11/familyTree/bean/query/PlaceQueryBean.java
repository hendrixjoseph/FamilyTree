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
