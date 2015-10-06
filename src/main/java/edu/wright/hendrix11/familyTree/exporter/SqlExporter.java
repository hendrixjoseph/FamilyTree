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

import java.io.*;

/**
 * @author Joe Hendrix
 */
public class SqlExporter extends Exporter
{

    /**
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    public SqlExporter(String fileName) throws FileNotFoundException
    {
        super(fileName);
    }

    /**
     *
     * @param out
     */
    public SqlExporter(PrintStream out)
    {
        super(out);
    }

    /**
     *
     */
    @Override
    public void export()
    {

    }
}
