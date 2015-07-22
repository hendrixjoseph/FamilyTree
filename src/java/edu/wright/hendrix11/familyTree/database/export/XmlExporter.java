package edu.wright.hendrix11.familyTree.database.export;

import java.io.FileNotFoundException;
import java.io.PrintStream;


/**
*
* @author Joe Hendrix
*/
public class XmlExporter extends Exporter
{
    public XmlExporter(String fileName) throws FileNotFoundException
    {
        super(fileName);
    }
    
    public XmlExporter(PrintStream out)
    {
        super(out);
    }
    
    @Override
    public void export()
    {
    
    }
}
