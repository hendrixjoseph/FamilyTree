package edu.wright.hendrix11.familyTree.database.interfaces;

/**
*
* @author Joe Hendrix <hendrix.11@wright.edu>
* @param <T>
* @param <K>
*/
public interface SelectAllData<T>
{
    /**
    *
    * @param t
    * @return
    */
    public List<T> selectAll(T t);
}
