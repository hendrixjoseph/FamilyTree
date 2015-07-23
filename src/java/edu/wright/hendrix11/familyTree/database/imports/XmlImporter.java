
package edu.wright.hendrix11.familyTree.database.imports;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
*
* @author Joe Hendrix
*/
public class XmlImporter extends Importer
{
    public XmlImporter(String fileName) throws FileNotFoundException
    {
        super(fileName);
    }

    public XmlImporter(Scanner in)
    {
        super(in);
    }
    
    @Override
    public void processData()
    {
    
    }
}
