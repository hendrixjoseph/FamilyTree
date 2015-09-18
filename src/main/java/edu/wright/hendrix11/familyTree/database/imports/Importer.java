/*
 *  The MIT License (MIT)
 *
 *  View the full license at:
 *  https://github.com/hendrixjoseph/FamilyTree/blob/master/LICENSE.md
 *
 *  Copyright (c) 2015 Joseph Hendrix
 *
 *  Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
 *
 */
package edu.wright.hendrix11.familyTree.database.imports;

import edu.wright.hendrix11.familyTree.database.Database;
import edu.wright.hendrix11.familyTree.database.table.MarriageTable;
import edu.wright.hendrix11.familyTree.database.table.PersonTable;
import edu.wright.hendrix11.familyTree.database.table.SpouseChildTable;
import edu.wright.hendrix11.familyTree.entity.PersonView;
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
