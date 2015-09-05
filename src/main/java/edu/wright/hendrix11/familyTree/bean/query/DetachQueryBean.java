package edu.wright.hendrix11.familyTree.bean.query;

import edu.wright.hendrix11.familyTree.entity.Person;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;


/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@ManagedBean
@ViewScoped
public class DetachQueryBean extends QueryBean implements Serializable
{   
    @ManagedProperty(value="#{individualBean.person}")
    private Person person;
    
    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
    
    }
    
    /**
     *
     * @param actionEvent
     */
    @Override
    public void commit(ActionEvent actionEvent)
    {   
    
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getAction()
    {
        return getAction(person);
    }
}
