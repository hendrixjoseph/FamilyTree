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
    private HashMap<String, String> genders;
    private GenderData genderData;
    
    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        genders = HashMap<String, String>();
        genderData = new GenderData();
        List<Gender> genderList = genderData.selectAll();
        
        for(Gender gender : genderList)
            genders.put(gender.getFullWord(), gender.getFullWord());
    }
    
    public HashMap<String, String> getGenders()
    {
        return genders;
    }
}
