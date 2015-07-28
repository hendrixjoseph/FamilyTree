package edu.wright.hendrix11.familyTree.database.export;

import java.io.FileNotFoundException;
import java.io.PrintStream;


/**
*
* @author Joe Hendrix
*/
public class GedcomExporter extends Exporter
{

    /**
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    public GedcomExporter(String fileName) throws FileNotFoundException
    {
        super(fileName);
    }
    
    /**
     *
     * @param out
     */
    public GedcomExporter(PrintStream out)
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
