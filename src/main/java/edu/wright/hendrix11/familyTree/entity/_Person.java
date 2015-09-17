/*
* The MIT License (MIT)
*
* Copyright (c) 2015 Joseph Hendrix
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*
* Hosted on GitHub at https://github.com/hendrixjoseph/FamilyTree
*
*/
package edu.wright.hendrix11.familyTree.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Joe Hendrix
 */
@Entity
@Table(name = "PERSON")
public class _Person implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FATHER_ID")
    private Person father;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOTHER_ID")
    private Person mother;

    private String name;
    private String gender;

    @Column(name = "DATE_OF_BIRTH")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "PLACE_OF_BIRTH")
    private String placeOfBirth;

    @Column(name = "DATE_OF_DEATH")
    @Temporal(TemporalType.DATE)
    private Date dateOfDeath;

    @Column(name = "PLACE_OF_DEATH")
    private String placeOfDeath;

    @ManyToMany
    @JoinTable(
        name="SPOUSE_VIEW",
        joinColumns={@JoinColumn(name="ID", referencedColumnName="ID")},
        inverseJoinColumns={@JoinColumn(name="SPOUSE_ID", referencedColumnName="ID")})
    private List<Person> spouses;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name="CHILDREN_VIEW", 
        joinColumns={@JoinColumn(name="ID", referencedColumnName="ID")}, 
        inverseJoinColumns={@JoinColumn(name="CHILD_ID", referencedColumnName="ID")})
    private List<Person> children;
}
