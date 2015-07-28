package edu.wright.hendrix11.familyTree.database.imports;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
*
* @author Joe Hendrix
*/
public class SqlImporter extends Importer
{

    /**
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    public SqlImporter(String fileName) throws FileNotFoundException
    {
        super(fileName);
    }

    /**
     *
     * @param in
     */
    public SqlImporter(Scanner in)
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
