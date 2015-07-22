package edu.wright.hendrix11.familyTree.database.imports;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
*
* @author Joe Hendrix
*/
public class SqlImporter extends Importer
{
    public SqlImporter(String fileName) throws FileNotFoundException
    {
        super(fileName);
    }

    public SqlImporter(Scanner in)
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
