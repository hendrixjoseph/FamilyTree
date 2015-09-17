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
 *  
 *  Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
 *  
 */
package temp;

import java.io.Serializable;
import java.math.BigInteger;
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

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@Entity
@Table(name = "DEFAULT_PERSON_VIEW")
@NamedQueries(
{
    @NamedQuery(name = "DefaultPersonView.findAll", query = "SELECT d FROM DefaultPersonView d")
})
public class DefaultPersonView implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Id // I had to add this
    private BigInteger id;
    @Size(max = 100)
    @Column(name = "FATHER_NAME")
    private String fatherName;
    @Size(max = 100)
    @Column(name = "MOTHER_NAME")
    private String motherName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String name;
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

    public DefaultPersonView()
    {
    }

    public BigInteger getId()
    {
        return id;
    }

    public void setId(BigInteger id)
    {
        this.id = id;
    }

    public String getFatherName()
    {
        return fatherName;
    }

    public void setFatherName(String fatherName)
    {
        this.fatherName = fatherName;
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
