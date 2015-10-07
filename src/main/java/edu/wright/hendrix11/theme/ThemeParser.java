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

package edu.wright.hendrix11.theme;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarFile;

/**
 * @author Joe Hendrix
 */
public class ThemeParser
{

    private JarFile jarFile;
    private String jarName;
    private List<String> themes;

    /**
     *
     */
    public ThemeParser()
    {
        this("all-themes-1.0.10.jar");
    }

    /**
     *
     * @param jarName
     */
    public ThemeParser(String jarName)
    {
        jarFile = openJarFile(jarName);

        init();
    }

    /**
     *
     * @param jarFile
     */
    public ThemeParser(JarFile jarFile)
    {
        this.jarFile = jarFile;

        init();
    }

    private void init()
    {
        init(jarFile);
    }

    private void init(JarFile jarFile)
    {
        if (jarFile != null)
        {
            initializeThemes();

            closeJarFile();
        }
        else
        {
            themes = new ArrayList<>();
        }
    }

    /**
     *
     * @return
     */
    public List<String> getThemes()
    {
        return themes;
    }

    /**
     *
     * @param themes
     */
    public void setThemes(List<String> themes)
    {
        this.themes = themes;
    }

    /**
     *
     * @return
     */
    public String getJarName()
    {
        return jarName;
    }

    /**
     *
     */
    public void outputThemes()
    {
        outputThemes(System.out);
    }

    /**
     *
     * @param out
     */
    public void outputThemes(PrintStream out)
    {
        for (String theme : themes)
        {
            out.println(theme);
        }
    }

    private void initializeThemes()
    {
        initializeThemes(jarFile);
    }

    private void initializeThemes(JarFile jarFile)
    {
        themes = new ArrayList<>();

        Enumeration entries = jarFile.entries();

        while (entries.hasMoreElements())
        {
            String currentEntry = entries.nextElement().toString();

            if (currentEntry.contains("themes"))
            {
                int index = currentEntry.indexOf("themes/") + "themes/".length();
                currentEntry = currentEntry.substring(index);

                index = currentEntry.indexOf("/");

                if (currentEntry.length() > 0)
                {
                    if (index > -1)
                    {
                        currentEntry = currentEntry.substring(0, index);
                    }

                    if (!themes.contains(currentEntry))
                    {
                        themes.add(currentEntry);
                    }
                }
            }
        }
    }

    private JarFile openJarFile(String jarName)
    {
        try
        {
            if (new File(jarName).isFile())
            {
                return new JarFile(jarName);
            }

            jarName = this.getClass().getResource("").getPath() + jarName;

            this.jarName = jarName;

            if (new File(jarName).isFile())
            {
                return new JarFile(jarName);
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

    private void closeJarFile()
    {
        closeJarFile(jarFile);
    }

    private void closeJarFile(JarFile jarFile)
    {
        if (jarFile != null)
        {
            try
            {
                jarFile.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
