package edu.wright.hendrix11.familyTree.database.export;


/**
*
* @author Joe Hendrix
*/
public class XmlExporter extends Exporter
{
    public XmlExporter(String fileName)
    {
        this(new PrintStream(fileName));
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
