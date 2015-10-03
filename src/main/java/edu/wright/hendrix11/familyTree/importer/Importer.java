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

import java.io.FileNotFoundException;
import java.io.FileReader;
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
    protected FileReader file;
    protected EntityManager em;

    /**
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    public Importer(String fileName) throws FileNotFoundException
    {
        this(new FileReader(fileName));
    }

    public Importer(FileReader file)
    {
        this.file = file;
    }

    /**
     *
     */
    public abstract void processData();

    public abstract void processData(EntityManager em);
}
