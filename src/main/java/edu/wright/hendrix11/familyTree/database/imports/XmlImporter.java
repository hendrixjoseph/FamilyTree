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
package edu.wright.hendrix11.familyTree.database.imports;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
*
* @author Joe Hendrix
*/
public class XmlImporter extends Importer
{

    /**
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    public XmlImporter(String fileName) throws FileNotFoundException
    {
        super(fileName);
    }

    /**
     *
     * @param in
     */
    public XmlImporter(Scanner in)
    {
        super(in);
    }
    
    /**
     *
     */
    @Override
    public void processData()
    {
    
    }
}
