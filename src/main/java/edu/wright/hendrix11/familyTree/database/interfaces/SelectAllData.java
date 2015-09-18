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
