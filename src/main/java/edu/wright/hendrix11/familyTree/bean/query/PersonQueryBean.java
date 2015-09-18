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
package edu.wright.hendrix11.familyTree.bean.query;

import edu.wright.hendrix11.familyTree.database.table.MarriageTable;
import edu.wright.hendrix11.familyTree.database.table.PersonTable;
import edu.wright.hendrix11.familyTree.entity.Marriage;
import edu.wright.hendrix11.familyTree.entity.PersonView;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@ManagedBean
@ViewScoped
public class PersonQueryBean extends QueryBean implements Serializable
{


    private static final int FATHER = 0;
    private static final int MOTHER = 1;
    private static final int SPOUSE = 2;
    private static final int CHILD_WITH_NO_SPOUSE = 3;
    private static final int CHILD_WITH_SPOUSE = 4;
    private static final int NO_INSERT_UPDATE_RELATED_PERSON = 5;
    int personType;

    private PersonView personToInsert;

    @ManagedProperty(value = "#{individualBean.person}")
    private PersonView relatedPerson;

    private PersonView spouseToRelated;

    private PersonTable personTable;

    /**
     *
     */
    @PostConstruct
    public void initialize()
    {
        personToInsert = new PersonView();
        personTable = new PersonTable();
    }

    /**
     *
     * @param actionEvent
     */
    @Override
    public void commit(ActionEvent actionEvent)
    {
        if (personType == NO_INSERT_UPDATE_RELATED_PERSON)
        {
            updateRelatedPerson();
        }
        else if (personType == CHILD_WITH_NO_SPOUSE || personType == CHILD_WITH_SPOUSE)
        {
            insertChild();
        }
        else if (personType == FATHER || personType == MOTHER)
        {
            insertParent();
        }
        else if (personType == SPOUSE)
        {
            insertSpouse();
        }
    }

    private void insertChild()
    {
        if (personType == CHILD_WITH_NO_SPOUSE)
        {
            if (relatedPerson.getGender().equals("Male"))
            {
                personToInsert.setFather(relatedPerson);
            }
            else if (relatedPerson.getGender().equals("Female"))
            {
                personToInsert.setMother(relatedPerson);
            }
        }
        else if (personType == CHILD_WITH_SPOUSE)
        {
            if (relatedPerson.getGender().equals("Male"))
            {
                personToInsert.setFather(relatedPerson);
                personToInsert.setMother(spouseToRelated);
            }
            else if (relatedPerson.getGender().equals("Female"))
            {
                personToInsert.setMother(relatedPerson);
                personToInsert.setFather(spouseToRelated);
            }
        }

        personToInsert = personTable.insert(personToInsert);
    }

    private void insertParent()
    {
        personToInsert = personTable.insert(personToInsert);

        if (personType == FATHER)
        {
            relatedPerson.setFather(personToInsert);
        }
        else if (personType == MOTHER)
        {
            relatedPerson.setMother(personToInsert);
        }

        personTable.update(relatedPerson);
    }

    private void insertSpouse()
    {
        MarriageTable md = new MarriageTable();
        Marriage m = new Marriage();

        personToInsert = personTable.insert(personToInsert);

        m.setCouple(personToInsert, relatedPerson);

        md.insert(m);
    }

    private void updateRelatedPerson()
    {
        personTable.update(relatedPerson);
    }

    private void setGenderNotSelected()
    {
        personToInsert.setGender("notSelected");
    }

    /**
     *
     * @return
     */
    public PersonView getPersonToInsert()
    {
        return personToInsert;
    }

    /**
     *
     * @param personToInsert
     */
    public void setPersonToInsert(PersonView personToInsert)
    {
        this.personToInsert = personToInsert;
    }

    /**
     *
     * @return
     */
    public PersonView getRelatedPerson()
    {
        return relatedPerson;
    }

    /**
     *
     * @param relatedPerson
     */
    public void setRelatedPerson(PersonView relatedPerson)
    {
        this.relatedPerson = relatedPerson;
    }

    /**
     *
     * @param actionEvent
     */
    public void setPersonAsFather(ActionEvent actionEvent)
    {
        personType = FATHER;
        personToInsert.setGender("Male");
    }

    /**
     *
     * @param actionEvent
     */
    public void setPersonAsMother(ActionEvent actionEvent)
    {
        personType = MOTHER;
        personToInsert.setGender("Female");
    }

    /**
     *
     * @param actionEvent
     */
    public void setPersonAsSpouse(ActionEvent actionEvent)
    {
        personType = SPOUSE;

        if (relatedPerson.getGender().equals("Male"))
        {
            personToInsert.setGender("Female");
        }
        else if (relatedPerson.getGender().equals("Female"))
        {
            personToInsert.setGender("Male");
        }
    }

    /**
     *
     * @param actionEvent
     */
    public void setPersonAsChildWithNoSpouse(ActionEvent actionEvent)
    {
        personType = CHILD_WITH_NO_SPOUSE;
        setGenderNotSelected();
    }

    /**
     *
     * @param spouse
     */
    public void setPersonAsChildWithSpouse(PersonView spouse)
    {
        personType = CHILD_WITH_SPOUSE;
        this.spouseToRelated = spouse;
        setGenderNotSelected();
    }

    /**
     *
     * @param actionEvent
     */
    public void setNoInsertJustUpdate(ActionEvent actionEvent)
    {
        personType = NO_INSERT_UPDATE_RELATED_PERSON;
    }

    /**
     *
     * @return
     */
    @Override
    public String getAction()
    {
        return getAction(relatedPerson);
    }

    /**
     *
     * @param actionEvent
     */
    public void buttonNoAction(ActionEvent actionEvent)
    {
        System.err.println("public void buttonNoAction(ActionEvent actionEvent) called");
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        String[] personType =
        {
            "Father",
            "Mother",
            "Spouse",
            "Child w/o spouse",
            "Child w/ spouse",
            "No insert - update related person instead"
        };

        sb.append("\n\tpersonToInsert is\n");
        sb.append("\t\ttype:\t").append(personType[this.personType]).append("\n");
        sb.append(personToInsert != null ? personToInsert.toString("\t\t") : "\t\t(null)");
        sb.append("\n\trelatedPerson is\n");
        sb.append(relatedPerson != null ? relatedPerson.toString("\t\t") : "\t\t(null)");

        return sb.toString();
    }
}
