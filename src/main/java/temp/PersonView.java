/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Entity
@Table(name = "PERSON_VIEW")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "PersonView.findAll", query = "SELECT p FROM PersonView p"),
    @NamedQuery(name = "PersonView.findById", query = "SELECT p FROM PersonView p WHERE p.id = :id"),
    @NamedQuery(name = "PersonView.findByFatherId", query = "SELECT p FROM PersonView p WHERE p.fatherId = :fatherId"),
    @NamedQuery(name = "PersonView.findByFatherName", query = "SELECT p FROM PersonView p WHERE p.fatherName = :fatherName"),
    @NamedQuery(name = "PersonView.findByMotherId", query = "SELECT p FROM PersonView p WHERE p.motherId = :motherId"),
    @NamedQuery(name = "PersonView.findByMotherName", query = "SELECT p FROM PersonView p WHERE p.motherName = :motherName"),
    @NamedQuery(name = "PersonView.findByName", query = "SELECT p FROM PersonView p WHERE p.name = :name"),
    @NamedQuery(name = "PersonView.findByGender", query = "SELECT p FROM PersonView p WHERE p.gender = :gender"),
    @NamedQuery(name = "PersonView.findByPlaceOfBirth", query = "SELECT p FROM PersonView p WHERE p.placeOfBirth = :placeOfBirth"),
    @NamedQuery(name = "PersonView.findByDateOfBirth", query = "SELECT p FROM PersonView p WHERE p.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "PersonView.findByPlaceOfDeath", query = "SELECT p FROM PersonView p WHERE p.placeOfDeath = :placeOfDeath"),
    @NamedQuery(name = "PersonView.findByDateOfDeath", query = "SELECT p FROM PersonView p WHERE p.dateOfDeath = :dateOfDeath")
})
public class PersonView implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @Id
    private int id;
//    @Column(name = "FATHER_ID")
//    private int fatherId;
    @Size(max = 100)
    @Column(name = "FATHER_NAME")
    private String fatherName;
    @Column(name = "MOTHER_ID")
    private int motherId;
    @Size(max = 100)
    @Column(name = "MOTHER_NAME")
    private String motherName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GENDER")
    private String gender;
    @Size(max = 100)
    @Column(name = "PLACE_OF_BIRTH")
    private String placeOfBirth;
    @Column(name = "DATE_OF_BIRTH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    @Size(max = 100)
    @Column(name = "PLACE_OF_DEATH")
    private String placeOfDeath;
    @Column(name = "DATE_OF_DEATH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfDeath;

    public PersonView()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

//    public int getFatherId()
//    {
//        return fatherId;
//    }
//
//    public void setFatherId(int fatherId)
//    {
//        this.fatherId = fatherId;
//    }

    public String getFatherName()
    {
        return fatherName;
    }

    public void setFatherName(String fatherName)
    {
        this.fatherName = fatherName;
    }

    public int getMotherId()
    {
        return motherId;
    }

    public void setMotherId(int motherId)
    {
        this.motherId = motherId;
    }

    public String getMotherName()
    {
        return motherName;
    }

    public void setMotherName(String motherName)
    {
        this.motherName = motherName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getPlaceOfBirth()
    {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth)
    {
        this.placeOfBirth = placeOfBirth;
    }

    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfDeath()
    {
        return placeOfDeath;
    }

    public void setPlaceOfDeath(String placeOfDeath)
    {
        this.placeOfDeath = placeOfDeath;
    }

    public Date getDateOfDeath()
    {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath)
    {
        this.dateOfDeath = dateOfDeath;
    }

}
