package edu.wright.hendrix11.familyTree.database.export;


/**
*
* @author Joe Hendrix
*/
public class GedcomExporter extends Exporter
{
    public GedcomExporter(String fileName)
    {
        this(new PrintStream(fileName));
    }
    
    public Exporter(PrintStream out)
    {
        super(out);
    }
    
    @Override
    public void export()
    {
    
    }
}
