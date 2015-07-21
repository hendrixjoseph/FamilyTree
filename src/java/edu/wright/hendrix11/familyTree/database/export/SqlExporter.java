package edu.wright.hendrix11.familyTree.database.export;


/**
*
* @author Joe Hendrix
*/
public class SqlExporter extends Exporter
{
    public SqlExporter(String fileName)
    {
        this(new PrintStream(fileName));
    }
    
    public SqlExporter(PrintStream out)
    {
        super(out);
    }
    
    @Override
    public void export()
    {
    
    }
}
