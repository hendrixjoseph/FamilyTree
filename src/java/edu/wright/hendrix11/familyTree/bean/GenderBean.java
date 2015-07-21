package edu.wright.hendrix11.familyTree.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
*
* @author Joe Hendrix
*/
@ManagedBean
@RequestScoped
public class GenderBean
{
    private List<Gender> genders;
    private GenderData genderData;
    
    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        genders = new ArrayList<Gender>();
        genderData = new GenderData();
        genders = genderData.selectAll();
    }
    
    public List<Gender> getGenders()
    {
        return genders;
    }
}
