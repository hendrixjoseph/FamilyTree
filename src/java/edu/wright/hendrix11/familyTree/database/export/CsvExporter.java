package edu.wright.hendrix11.familyTree.database.export;

import java.io.FileNotFoundException;
import java.io.PrintStream;


/**
*
* @author Joe Hendrix
*/
public class CsvExporter extends Exporter
{
    public CsvExporter(String fileName) throws FileNotFoundException
    {
        super(fileName);
    }
    
    public CsvExporter(PrintStream out)
    {
        super(out);
    }
    
    @Override
    public void export()
    {
    
    }
}
