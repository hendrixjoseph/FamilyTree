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

    @PostConstruct
    public void initialize()
    {
        table = new SpouseChildTable();
        
        super.initialize(table);
    }
    
    @Override
    public String[] getPrettyNames()
    {
        return prettyNames;
    }
    
    @Override
    public String[] getColumns()
    {
        return columns;
    }
}
