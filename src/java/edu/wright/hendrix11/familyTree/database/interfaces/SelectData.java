
package edu.wright.hendrix11.familyTree.database.interfaces;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public interface SelectData<T, K>
{
    public T select(K k);
}