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

import javax.persistence.EntityManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Joe Hendrix
 */
public abstract class Importer
{
    private static final Logger LOG = Logger.getLogger(Importer.class.getName());
    protected static final String[] KNOWN_COUNTRIES = {"Mexico", "Germany", "USA", "Ireland", "Spain"};
    protected FileReader file;
    protected EntityManager em;
    protected String nextLine = "";
    protected int lineNumber;

    /**
     * @param fileName
     *
     * @throws FileNotFoundException
     */
    public Importer(String fileName) throws FileNotFoundException
    {
        this(new FileReader(fileName));
    }

    /**
     * @param file
     */
    public Importer(FileReader file)
    {
        this.file = file;
    }

    /**
     * @param entityManager
     */
    public void processData(EntityManager entityManager)
    {
        this.em = entityManager;
        entityManager.getTransaction().begin();

        try
        {
            processData();
        }
        catch ( Exception e )
        {
            LOG.log(Level.SEVERE, String.format("Failed on line %d: %s", lineNumber, nextLine));
            throw e;
        }

        entityManager.getTransaction().commit();
    }

    protected abstract void processData();

}
