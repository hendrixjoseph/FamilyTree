package edu.wright.hendrix11.familyTree.database.interfaces;

import java.util.List;

/**
*
* @author Joe Hendrix <hendrix.11@wright.edu>
* @param <T>
*/
public interface SelectAllData<T>
{
    /**
    *
    * @return
    */
    public List<T> selectAll();
}
