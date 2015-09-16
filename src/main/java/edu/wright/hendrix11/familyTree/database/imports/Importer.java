/* 
 *  The MIT License (MIT)
 *  
 *  Copyright (c) 2015 Joseph Hendrix
 *  
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package edu.wright.hendrix11.familyTree.database.imports;

import edu.wright.hendrix11.familyTree.database.Database;
import edu.wright.hendrix11.familyTree.database.table.MarriageTable;
import edu.wright.hendrix11.familyTree.database.table.PersonTable;
import edu.wright.hendrix11.familyTree.database.table.SpouseChildTable;
import edu.wright.hendrix11.familyTree.entity.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

/**
*
* @author Joe Hendrix
*/
public abstract class Importer
{    

    /**
     *
     */
    protected Scanner in;
    
    /**
     *
     */
    protected PersonTable personTable;

    /**
     *
     */
    protected MarriageTable marriageTable;

    /**
     *
     */
    protected SpouseChildTable spouseChildTable;
    
    /**
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    public Importer(String fileName) throws FileNotFoundException
    {        
        this(new Scanner(new File(fileName)));
    }
    
    /**
     *
     * @param in
     */
    public Importer(Scanner in)
    {
        this.in = in;     
    }
    
    /**
     *
     * @throws SQLException
     */
    public void importData() throws SQLException
    {
        Database.openConnection();
        
        personTable = new PersonTable();
        marriageTable = new MarriageTable();
        spouseChildTable = new SpouseChildTable();
        
        processData();
        
        Database.closeConnection();
    }
    
    /**
     *
     */
    protected abstract void processData();
}
