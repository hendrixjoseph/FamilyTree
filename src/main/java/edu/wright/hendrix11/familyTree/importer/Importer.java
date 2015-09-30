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
package edu.wright.hendrix11.familyTree.importer;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;
import javax.persistence.EntityManager;

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
    protected EntityManager em;

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
        processData();
    }

    /**
     *
     */
    protected abstract void processData();
}
