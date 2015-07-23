
package edu.wright.hendrix11.familyTree.bean.query;

import edu.wright.hendrix11.familyTree.bean.PlacesBean.Places;
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

    @PostConstruct
    public void initialize()
    {
        placeTable = new PlaceTable();
    }
    
    @Override
    public void commit(ActionEvent actionEvent)
    {
        placeTable.update(place);
    }
    
    public void delete(ActionEvent actionEvent)
    {
        placeTable.delete(place);
    }

    @Override
    public String getAction()
    {
        return "places?" + getRedirectAction();
    }
    
    public void setPlace(Place place)
    {
        this.place = place;
    }
    
    public void setPlace(Places place)
    {
        this.place = place.getObject();
    }

    public Place getPlace()
    {
        
        return place;
    }
}
