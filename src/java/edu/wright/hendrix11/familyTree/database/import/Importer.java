
package edu.wright.hendrix11.familyTree.database.import;

/**
*
* @author Joe Hendrix
*/
public abstract class Importer extends Database
{
    private static final String[] tableNames = {"", ""};
    
    protected Scanner in;
    
    public Importer(String fileName)
    {
        try
        {
            File file = new File(filename);

            this(new Scanner(file));
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
            return;
        }
    }
    
    public Importer(PrintStream in)
    {
        this.in = in;
    }
    
    public abstract void import();
}
