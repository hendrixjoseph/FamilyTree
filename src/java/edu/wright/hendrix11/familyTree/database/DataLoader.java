/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wright.hendrix11.familyTree.database;

import edu.wright.hendrix11.familyTree.entity.Person;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class DataLoader
{
    private HashMap<String, Person> entry = new HashMap<String, Person>();
    
    /**
     *
     * @param filename
     */
    public DataLoader(String filename)
    {
        try
        {
            File file = new File(filename);

            Scanner inputStream = new Scanner(file);

            while(inputStream.hasNext())
            {
                //read single line, put in string
                String data = inputStream.nextLine();
                
                if(data.startsWith("0"))
                {
                    if(data.contains("INDI"))
                    {
                        int start = data.indexOf("@");
                        int end = data.indexOf("@", start + 1);
                        data = data.substring(start, end);
                        
                        Person person = new Person();
                        
                        data = inputStream.nextLine();
                        
                        while(inputStream.hasNext() && !data.startsWith("0"))
                        {
                            if(data.startsWith("1 NAME "))
                            {
                                start = "1 NAME ".length();
                                String name = data.substring(start);
                                person.setName(name.replace("/", ""));
                                
                                System.out.println(person.getName() + " loaded!");
                            }
                            
                            data = inputStream.nextLine();
                        }
                        
                        entry.put(data, person);
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }                
    }

    /**
     *
     * @return
     */
    public HashMap<String, Person> getEntry()
    {
        return entry;
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        String path = "C:\\Users\\Joe\\Documents\\";
        String file = "hendrixfamily.fte.GED";
            
        DataLoader loader = new DataLoader(path + file);
        
        HashMap<String, Person> entry = loader.getEntry();
        
        System.out.println(entry.size() + " people loaded.");
    }
}
