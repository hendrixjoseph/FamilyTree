
package edu.wright.hendrix11.familyTree.database.export;

import edu.wright.hendrix11.familyTree.database.Database;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
*
* @author Joe Hendrix
*/
public abstract class Exporter
{
    private static final String[] tableNames = {"", ""};
    
    /**
     *
     */
    protected PrintStream out;
    
    /**
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    public Exporter(String fileName) throws FileNotFoundException
    {
        this(new PrintStream(fileName));
    }
    
    /**
     *
     * @param out
     */
    public Exporter(PrintStream out)
    {
        this.out = out;
    }
    
    /**
     *
     */
    public abstract void export();
}
