/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wright.hendrix11.familyTree.entity;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class PersonInfo
{
    private Person person;
    private String placeOfBurial;
    private String ssn;
    private String note;

    public String getPlaceOfBurial()
    {
        return placeOfBurial;
    }

    public void setPlaceOfBurial(String placeOfBurial)
    {
        this.placeOfBurial = placeOfBurial;
    }

    public String getSsn()
    {
        return ssn;
    }

    public void setSsn(String ssn)
    {
        this.ssn = ssn;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }
}
