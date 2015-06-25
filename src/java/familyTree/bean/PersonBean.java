package familyTree.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import familyTree.entity.Person;
import familyTree.database.Database;
import javax.annotation.PostConstruct;

/**
 *
 * @author Joe
 */
@ManagedBean
@SessionScoped
public class PersonBean 
{
    private Person person;
       
    public PersonBean()
    {
    }
    
    @PostConstruct
    public void initialize()
    {
        Database.setProperties();
        //hardWirePerson();        
        databasePerson();
    }
    
    /*
    private void hardWirePerson()
    {
        Calendar cal = Calendar.getInstance();
        
        person = new Person();
        
        person.setId(1);
        person.setFatherName("Samuel Thoroman");
        person.setMotherName("Cynthiann McDonald Reynolds");
        person.setName("William Zenos Thoroman");
        person.setPlaceOfBirth("Ohio");
        
        cal.set(1827,Calendar.MARCH,4);
        person.setDateOfBirth(cal);
        person.setPlaceOfDeath("Jacksonville, Ohio");
        cal.set(1900,Calendar.JANUARY,29);
        person.setDateOfDeath(cal);
    }
    */
    
    private void databasePerson()
    {
        person = Database.getPerson(1);
    }
    
    public Person getPerson()
    {   
        return person;
    }
    
    public void setPerson()
    {
        // Nothing yet...
    }
}
