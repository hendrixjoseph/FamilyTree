
package edu.wright.hendrix11.familyTree.database.imports;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
*
* @author Joe Hendrix
*/
public class CsvImporter extends Importer
{

    public CsvImporter(String fileName) throws FileNotFoundException
    {
        super(fileName);
    }

    public CsvImporter(Scanner in)
    {
        super(in);
    }
    
    @Override
    protected void initializeTables()
    {
        
    }    
    
    @Override
    public void processData()
    {
    
    }
}
