
package edu.wright.hendrix11.familyTree.bean.transaction;

import edu.wright.hendrix11.familyTree.database.PersonData;
import edu.wright.hendrix11.familyTree.entity.Person;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@ManagedBean
//@RequestScoped
@ViewScoped
public class InsertPersonBean implements Serializable
{   
    int personType;
        
    private static final int FATHER = 0;
    private static final int MOTHER = 1;
    private static final int SPOUSE = 2;
    private static final int CHILD_WITH_NO_SPOUSE = 3;
    private static final int CHILD_WITH_SPOUSE = 4;
    
    private Person personToInsert;
    
    @ManagedProperty(value="#{individualBean.person}")
    private Person relatedPerson;
    
    private Person spouseToRelated;
    
    @PostConstruct
    public void initialize()
    {
        personToInsert = new Person();
    }
    
    public void commit(ActionEvent actionEvent)
    {        
        if(personType == CHILD_WITH_NO_SPOUSE)
        {
            if(relatedPerson.getGender().equals("Male"))
            {
                personToInsert.setFather(relatedPerson);
            }
            else if(relatedPerson.getGender().equals("Female"))
            {
                personToInsert.setMother(relatedPerson);
            }
        }
        else if(personType == CHILD_WITH_SPOUSE)
        {
            if(relatedPerson.getGender().equals("Male"))
            {
                personToInsert.setFather(relatedPerson);
                personToInsert.setMother(spouseToRelated);
            }
            else if(relatedPerson.getGender().equals("Female"))
            {
                personToInsert.setMother(relatedPerson);
                personToInsert.setFather(spouseToRelated);
            }
        }
        
        PersonData personData = new PersonData();
        
        personToInsert = personData.insert(personToInsert);
        
        // I think this will work, or it needs to be "OR"
        if(personType != CHILD_WITH_NO_SPOUSE && personType != CHILD_WITH_SPOUSE)
        {
            if(personType == FATHER)
            {
                relatedPerson.setFather(personToInsert);
            }
            else if(personType == MOTHER)
            {
                relatedPerson.setMother(personToInsert);
            }
            else if(personType == SPOUSE)
            {

            }
            
            personData.update(relatedPerson);
        }
    }
    
    public String getAction()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("index?personId=");
        sb.append(relatedPerson.getId());
        sb.append("&amp;faces-redirect=true");
        
        return sb.toString();
    }
    
    public Person getPersonToInsert()
    {
        return personToInsert;
    }

    public void setPersonToInsert(Person personToInsert)
    {
        this.personToInsert = personToInsert;
    }

    public Person getRelatedPerson()
    {
        return relatedPerson;
    }

    public void setRelatedPerson(Person relatedPerson)
    {
        this.relatedPerson = relatedPerson;
    }
    
    public void setPersonAsFather(ActionEvent actionEvent)
    {
        personType = FATHER;
        personToInsert.setGender("Male");
    }
    
    public void setPersonAsMother(ActionEvent actionEvent)
    {
        personType = MOTHER;
        personToInsert.setGender("Female");
    }
    
    public void setPersonAsSpouse(ActionEvent actionEvent)
    {
        personType = SPOUSE;
        System.err.println("public void setPersonAsSpouse(ActionEvent actionEvent)");
        System.err.println("\tpersonType = SPOUSE;");
    }
    
    public void setPersonAsChildWithNoSpouse(ActionEvent actionEvent)
    {
        personType = CHILD_WITH_NO_SPOUSE;
        System.err.println("public void setPersonAsChild(ActionEvent actionEvent)");
        System.err.println("\tpersonType = CHILD;");
    }
    
    public void setPersonAsChildWithSpouse(ActionEvent actionEvent)
    {
        personType = CHILD_WITH_SPOUSE;
    }
    
    public void setPersonAsChildWithSpouse(Person spouse)
    {
        personType = CHILD_WITH_SPOUSE;
        this.spouseToRelated = spouse;
    }
       
    public void buttonNoAction(ActionEvent actionEvent)
    {
        System.err.println("public void buttonNoAction(ActionEvent actionEvent) called");
        outputAction(actionEvent);
    }
    
    public void outputAction(ActionEvent event)
    {
        UIComponent component = event.getComponent();
               
        System.err.println("\tActionEvent event class: " + event.getClass().getName());
        System.err.println("\tComponent class: " + component.getClass().getName());
        System.err.println("\tClient id: " + component.getClientId());
        
        if(component instanceof CommandButton)
        {
            CommandButton button = (CommandButton)component;
            
            System.err.println("\tbutton.getOncomplete(): " + button.getOncomplete());
            System.err.println("\tbutton.isAjax(): " + button.isAjax());
            System.err.println("\tbutton.getConfirmationScript(): " + button.getConfirmationScript());
        }
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        String[] personType = {"Father","Mother","Spouse","Child w/o spouse","Child w/ spouse"};
        
        sb.append("\n\tpersonToInsert is\n");
            sb.append("\t\ttype:\t").append(personType[this.personType]).append("\n");
            sb.append(personToInsert != null ? personToInsert.toString("\t\t") : "\t\t(null)");
        sb.append("\n\trelatedPerson is\n");
            sb.append(relatedPerson != null ? relatedPerson.toString("\t\t") : "\t\t(null)");
        
        return sb.toString();
    }
}