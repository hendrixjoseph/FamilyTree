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
package edu.wright.hendrix11.theme;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarFile;

/**
 *
 * @author Joe Hendrix
 */
public class ThemeParser
{
    private JarFile jarFile;
    private String jarName;
    private List<String> themes;
    
    public ThemeParser()
    {
        this("all-themes-1.0.10.jar");
    }
    
    public ThemeParser(String jarName)
    {
        jarFile = openJarFile(jarName);
        
        init();
    }
    
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
        if(jarFile != null)
        {
            initializeThemes();
        
            closeJarFile();
        }
        else
        {
            themes = new ArrayList<String>();
        }
    }

    public List<String> getThemes()
    {
        return themes;
    }

    public void setThemes(List<String> themes)
    {
        this.themes = themes;
    }

    public String getJarName()
    {
        return jarName;
    }
    
    public void outputThemes()
    {
        outputThemes(System.out);
    }
    
    public void outputThemes(PrintStream out)
    {
        for(String theme : themes)
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
        themes = new ArrayList<String>();
        
        Enumeration entries = jarFile.entries();
            
        while(entries.hasMoreElements())
        {
            String currentEntry = entries.nextElement().toString();

            if(currentEntry.contains("themes"))
            {
                int index = currentEntry.indexOf("themes/") + "themes/".length();
                currentEntry = currentEntry.substring(index);
                
                index = currentEntry.indexOf("/");
                
                if(currentEntry.length() > 0)
                {
                    if(index > -1)
                        currentEntry = currentEntry.substring(0, index);

                    if(!themes.contains(currentEntry))
                    {
                        themes.add(currentEntry);
                    }        
                }
            }
        };
    }
    
    private JarFile openJarFile(String jarName)
    {
        try
        {
            if(new File(jarName).isFile())            
                return new JarFile(jarName);
            
            jarName = this.getClass().getResource("").getPath() + jarName;
            
            this.jarName = jarName;
            
            if(new File(jarName).isFile())            
                return new JarFile(jarName);
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
        if(jarFile != null)
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
