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

package edu.wright.hendrix11.familyTree.exporter;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
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
     * @param fileName
     *
     * @throws FileNotFoundException
     */
    public Exporter(String fileName) throws FileNotFoundException
    {
        this(new PrintStream(fileName));
    }

    /**
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
