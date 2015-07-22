
package edu.wright.hendrix11.familyTree.database.imports;

import edu.wright.hendrix11.familyTree.database.Database;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Scanner;

/**
*
* @author Joe Hendrix
*/
public abstract class Importer
{
    private static final String[] tableNames = {"", ""};
    
    protected Scanner in;
    
    public Importer(String fileName) throws FileNotFoundException
    {        
        this(new Scanner(new File(fileName)));
    }
    
    public Importer(Scanner in)
    {
        this.in = in;
        
    }
    
    public void importData() throws SQLException
    {
        Database.openConnection();
        
        initializeTables();
        
        processData();
        
        Database.closeConnection();
    }
    
    protected abstract void processData();    
    protected abstract void initializeTables();
}
