
package edu.wright.hendrix11.familyTree.database.imports;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
*
* @author Joe Hendrix
*/
public class CsvImporter extends Importer
{

    /**
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    public CsvImporter(String fileName) throws FileNotFoundException
    {
        super(fileName);
    }

    /**
     *
     * @param in
     */
    public CsvImporter(Scanner in)
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
