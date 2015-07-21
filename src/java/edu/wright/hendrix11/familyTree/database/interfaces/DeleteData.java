
package edu.wright.hendrix11.familyTree.database.interfaces;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 * @param <T>
 */
public interface DeleteData<T>
{

    /**
     *
     * @param t
     * @return
     */
    public boolean delete(T t);
}