package edu.wright.hendrix11.familyTree.database.export;


/**
*
* @author Joe Hendrix
*/
public class CsvExporter extends Exporter
{
    public CsvExporter(String fileName)
    {
        this(new PrintStream(fileName));
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
