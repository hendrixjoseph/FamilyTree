
package edu.wright.hendrix11.familyTree.bean.query;

import edu.wright.hendrix11.familyTree.entity.Person;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public abstract class QueryBean
{

    /**
     *
     * @param actionEvent
     */
    public abstract void commit(ActionEvent actionEvent);
    
    /**
     *
     * @param person
     * @return
     */
    protected String getAction(Person person)
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("index?personId=");
        sb.append(person.getId());
        sb.append("&amp;").append(getRedirectAction());
        
        return sb.toString();
    }
    
    /**
     *
     * @return
     */
    protected String getRedirectAction()
    {
        return "faces-redirect=true";
    }
    
    /**
     *
     * @return
     */
    public abstract String getAction();
}
