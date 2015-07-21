package edu.wright.hendrix11.familyTree.bean;

import edu.wright.hendrix11.familyTree.database.GenderData;
import edu.wright.hendrix11.familyTree.entity.Gender;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
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
        genders = new HashMap<String, String>();
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
