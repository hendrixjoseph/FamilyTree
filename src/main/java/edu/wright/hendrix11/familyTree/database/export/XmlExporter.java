package edu.wright.hendrix11.familyTree.database.export;

import java.io.FileNotFoundException;
import java.io.PrintStream;


/**
*
* @author Joe Hendrix
*/
public class XmlExporter extends Exporter
{

    /**
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    public XmlExporter(String fileName) throws FileNotFoundException
    {
        super(fileName);
    }
    
    /**
     *
     * @param out
     */
    public XmlExporter(PrintStream out)
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
