package edu.wright.hendrix11.familyTree.database.export;

import java.io.FileNotFoundException;
import java.io.PrintStream;


/**
*
* @author Joe Hendrix
*/
public class SqlExporter extends Exporter
{

    /**
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    public SqlExporter(String fileName) throws FileNotFoundException
    {
        super(fileName);
    }
    
    /**
     *
     * @param out
     */
    public SqlExporter(PrintStream out)
    {
        super(out);
    }
    
    /**
     *
     */
    @Override
    public void export()
    {
    
    }
}
