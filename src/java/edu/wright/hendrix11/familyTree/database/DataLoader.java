/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wright.hendrix11.familyTree.database;

import edu.wright.hendrix11.familyTree.entity.Person;
import edu.wright.hendrix11.familyTree.entity.PersonInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class DataLoader
{
    public static PrintStream out;
    
    public static DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
    
    public static final String propertyFile = "C:\\Program Files\\Apache Software Foundation\\Apache Tomcat 8.0.15\\bin\\database.properties";
    
    private HashMap<String, Person> entry = new HashMap<String, Person>();
    
    private PersonData personData;
    
    /**
     *
     * @param filename
     */
    public DataLoader(String filename)
    {
        Scanner inputStream;
        
        Database.setProperties(propertyFile);
        personData = new PersonData();
        
        try
        {
            File file = new File(filename);

            inputStream = new Scanner(file);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
            return;
        }  

        while(inputStream.hasNext())
        {
            //read single line, put in string
            String nextLine = inputStream.nextLine();
            
            int start;
                
            if(nextLine.startsWith("0 @I"))//nextLine.contains("INDI") && nextLine.contains("@"))
            {                
                String gedcomid = getId(nextLine);

                Person person = new Person();
                PersonInfo personInfo = new PersonInfo();

                nextLine = inputStream.nextLine();
                out.println(nextLine);

                while(inputStream.hasNext() && !nextLine.startsWith("0 @I"))//!nextLine.contains("INDI") && !nextLine.endsWith("FAM"))
                {  
                    String change = nextLine;
                    
                    nextLine = processPerson(inputStream, person, nextLine);
                    nextLine = processPersonInfo(inputStream, personInfo, nextLine);
                    
                    if(change.equals(nextLine))
                    {
                        out.println(nextLine);
                        nextLine = inputStream.nextLine();                        
                    }
                }
                
                entry.put(gedcomid, person);
                //personData.insert(person);
            }
        }
            
               
    }
    
    private String processPersonInfo(Scanner inputStream, PersonInfo personInfo, String nextLine)
    {
        int start;
        
        if(nextLine.contains("BURI"))
        {
            nextLine = inputStream.nextLine();
            
            while(nextLine.contains("PLAC"))
            {
                if(nextLine.contains("PLAC"))
                {
                    start = "2 PLAC ".length();
                    nextLine = nextLine.substring(start);

                    personInfo.setPlaceOfBurial(nextLine);
                }
                
                nextLine = inputStream.nextLine();
            }
        }
        else if(nextLine.contains("SSN"))
        {
            nextLine = inputStream.nextLine();
            
            while(nextLine.contains("PLAC"))
            {
                if(nextLine.contains("PLAC"))
                {
                    start = "2 PLAC ".length();
                    nextLine = nextLine.substring(start);

                    personInfo.setSsn(nextLine);
                }
                
                nextLine = inputStream.nextLine();
            }
        }
        
        return nextLine;
    }
    
    private String processPerson(Scanner inputStream, Person person, String nextLine)
    {        
        int start;
        
        if(nextLine.contains("NAME"))
        {
            start = "1 NAME ".length();
            String name = nextLine.substring(start);
            person.setName(name.replace("/", ""));
            nextLine = inputStream.nextLine();
        }
        else if(nextLine.contains("SEX"))
        {
            if(nextLine.endsWith("M"))
                person.setGender("Male");
            else if(nextLine.endsWith("F"))
                person.setGender("Female");
            
            nextLine = inputStream.nextLine();
        }
        else if(nextLine.contains("BIRT"))
        {
            nextLine = inputStream.nextLine();

            while(nextLine.contains("DATE") || nextLine.contains("PLAC"))
            {
                if(nextLine.contains("DATE"))
                {
                    start = "2 DATE ".length();
                    nextLine = nextLine.substring(start);

                    try
                    {
                        person.setDateOfBirth(dateFormat.parse(nextLine));
                    }
                    catch(ParseException e)
                    {
                        System.out.println("Unparsable date: " + nextLine);
                    }
                }

                if(nextLine.contains("PLAC"))
                {
                    start = "2 PLAC ".length();
                    nextLine = nextLine.substring(start);

                    person.setPlaceOfBirth(nextLine); 
                }
                
                nextLine = inputStream.nextLine();
            }
        }
        else if(nextLine.contains("DEAT"))
        {
            nextLine = inputStream.nextLine();

            while(nextLine.contains("DATE") || nextLine.contains("PLAC"))
            {
                if(nextLine.contains("DATE"))
                {
                    start = "2 DATE ".length();
                    nextLine = nextLine.substring(start);

                    try
                    {
                        person.setDateOfDeath(dateFormat.parse(nextLine));
                    }
                    catch(ParseException e)
                    {
                        System.out.println("Unparsable date: " + nextLine);
                    }
                }

                if(nextLine.contains("PLAC"))
                {
                    start = "2 PLAC ".length();
                    nextLine = nextLine.substring(start);

                    person.setPlaceOfDeath(nextLine); 
                }
                
                nextLine = inputStream.nextLine();
            }
        }
        else
        {
            //nextLine = inputStream.nextLine();
        }
        
        return nextLine;
    }
    
    private String getId(String line)
    {
        int start = line.indexOf("@") + 1;
        int end = line.indexOf("@", start + 1);
        return line.substring(start, end);
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
        
        String outFile = "outFile.txt";
        
        try
        {
            out = new PrintStream(path + outFile);
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
            out = System.out;
        }
            
        DataLoader loader = new DataLoader(path + file);
        
        HashMap<String, Person> entry = loader.getEntry();
        
        System.out.println(entry.size() + " people loaded.");
        
        out.close();
    }
}
