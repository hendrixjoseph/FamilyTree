
package edu.wright.hendrix11.familyTree.database.export;


/**
*
* @author Joe Hendrix
*/
public abstract class Exporter extends Database
{
    private static final String[] tableNames = {"", ""};
    
    PrintStream out;
    
    public Exporter(String fileName)
    {
        this(new PrintStream(fileName));
    }
    
    public Exporter(PrintStream out)
    {
        this.out = out;
    }
    
    public abstract void export();
}
