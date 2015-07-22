
package edu.wright.hendrix11.familyTree.bean.query;

import javax.faces.event.ActionEvent;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public abstract class QueryBean
{
    public abstract void commit(ActionEvent actionEvent);
    
    /**
     *
     * @return
     */
    public String getAction()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("index?personId=");
        sb.append(relatedPerson.getId());
        sb.append("&amp;faces-redirect=true");
        
        return sb.toString();
    }
}
