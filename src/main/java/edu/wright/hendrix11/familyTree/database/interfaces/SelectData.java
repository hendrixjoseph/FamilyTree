
package edu.wright.hendrix11.familyTree.database.interfaces;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 * @param <T>
 * @param <K>
 */
public interface SelectData<T, K>
{

    /**
     *
     * @param k
     * @return
     */
    public T select(K k);
}