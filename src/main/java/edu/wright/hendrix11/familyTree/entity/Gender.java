package edu.wright.hendrix11.familyTree.entity;

/**
*
* @author Joe Hendrix
*/
public class Gender 
{
    private char abbr;
    private String fullWord;

    /**
     *
     * @return
     */
    public char getAbbr()
    {
        return abbr;
    }

    /**
     *
     * @param abbr
     */
    public void setAbbr(char abbr)
    {
        this.abbr = abbr;
    }

    /**
     *
     * @return
     */
    public String getFullWord()
    {
        return fullWord;
    }

    /**
     *
     * @param fullWord
     */
    public void setFullWord(String fullWord)
    {
        this.fullWord = fullWord;
    }      
}
