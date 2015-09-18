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
package edu.wright.hendrix11.familyTree.bean;

import edu.wright.hendrix11.familyTree.database.table.PersonTable;
import edu.wright.hendrix11.familyTree.entity.PersonView;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@ManagedBean
@ViewScoped
public class PersonsBean extends DataBean<PersonView>
{
    private static final String[] columns = {"ID","FATHER_NAME","MOTHER_NAME","NAME","GENDER","PLACE_OF_BIRTH","DATE_OF_BIRTH","PLACE_OF_DEATH","DATE_OF_DEATH"};
    private static final String[] prettyNames = {"Id","Father Name","Mother Name","Name","Gender","Place of Birth","Date of Birth","Place of Death","Date of Death"};
    private static final String[] linkColumns = {"Father Name","Mother Name","Name"};
    
    private static final int FATHER_NAME = 0;
    private static final int MOTHER_NAME = 1;
    private static final int NAME = 2;
    
    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        table = new PersonTable();
        
        super.initialize(table);
    }
    
    /**
     *
     * @return
     */
    @Override
    public String[] getPrettyNames()
    {
        return prettyNames;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String[] getColumns()
    {
        return columns;
    }
    
    /**
     *
     * @return
     */
    @Override
    protected String[] getLinkColumns()
    {
        return linkColumns;
    }
        
    /**
     *
     * @param helper
     * @param prettyName
     * @return
     */
    @Override
    protected String processLink(DataBeanHelper helper, String prettyName)
    {
        PersonView person = helper.getObject();
        
        if(prettyName.equals(linkColumns[FATHER_NAME]))
        {
            if(person.hasFather())
                return linkToPerson(person.getFather().getId());
        }
        else if(prettyName.equals(linkColumns[MOTHER_NAME]))
        {
            if(person.hasMother())
                return linkToPerson(person.getMother().getId()); 
        }
        else if(prettyName.equals(linkColumns[NAME]))
            return linkToPerson(person.getId());
            
        return "";
    }
}