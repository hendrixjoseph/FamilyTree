/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wright.hendrix11.familyTree.database;

import edu.wright.hendrix11.familyTree.database.interfaces.SelectData;
import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.entity.SpouseChildMap;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class SpouseChildData extends Database implements SelectData<SpouseChildMap, Integer>
{
    public SpouseChildData()
    {
        super();
        this.tableName = "CHILDREN_VIEW";
        setColumnMethodMap(tableName, new SpouseChildMap());
        //setOtherMethods();
    }

    @Override
    public SpouseChildMap select(Integer k)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
