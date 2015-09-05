/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wright.hendrix11.familyTree.bean;

import edu.wright.hendrix11.familyTree.database.table.SpouseChildTable;
import edu.wright.hendrix11.familyTree.entity.SpouseChildMap;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@ManagedBean
@ViewScoped
public class ChildrenBean extends DataBean<SpouseChildMap>
{
    private static final String[] columns = {"ID","NAME","SPOUSE_ID","SPOUSE","CHILD_ID","CHILD"};
    private static final String[] prettyNames = {"Id","Name","Spouse Id","Spouse Name","Child Id","Child Name"};
    private static final String[] linkColumns = {"Name","Spouse Name","Child Name"};
    
    private static final int NAME = 0;
    private static final int SPOUSE_NAME = 1;
    private static final int CHILD_NAME = 2;
    
    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        table = new SpouseChildTable();
        
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
        SpouseChildMap spouseChildMap = helper.getObject();
        
        if(prettyName.equals(linkColumns[NAME]))
            return linkToPerson(spouseChildMap.getPerson().getId());
        else if(prettyName.equals(linkColumns[SPOUSE_NAME]))
            return linkToPerson(spouseChildMap.getSpouse().getId());
        else if(prettyName.equals(linkColumns[CHILD_NAME]))
            return linkToPerson(spouseChildMap.getChild().getId());
        
        return "";
    }
}
