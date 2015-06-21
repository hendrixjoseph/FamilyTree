package FamilyTree;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.*;

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
        //hardWirePerson();        
        databasePerson();
    }
    
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
    
    private void databasePerson()
    {
        try
        {
            Properties prop = new Properties();
            InputStream input = null;         
            input = new FileInputStream("database.properties");
            prop.load(input);
            
            String url = prop.getProperty("database");
            String user = prop.getProperty("dbuser");
            String pass = prop.getProperty("dbpassword");
            
            input.close();
            
            // Load Oracle JDBC Driver
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection con = DriverManager.getConnection(url, user, pass);

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM PERSON WHERE ID=1");

            if(rs.next()) 
            {
                person = new Person();

                person.setId(rs.getInt("ID"));
                person.setFatherName(rs.getString("FATHER_NAME"));
                person.setMotherName(rs.getString("MOTHER_NAME"));
                person.setName(rs.getString("NAME"));
                person.setPlaceOfBirth(rs.getString("PLACE_OF_BIRTH"));
                person.setDateOfBirth(rs.getDate("DATE_OF_BIRTH"));
                person.setPlaceOfDeath(rs.getString("PLACE_OF_DEATH"));
                person.setDateOfDeath(rs.getDate("DATE_OF_DEATH"));
                person.setPlaceOfDeath(rs.getString("PLACE_OF_DEATH"));
            }

            rs.close();
            statement.close();
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            person = null;
        }
    }
    
    public Person getPerson()
    {   
        return person;
    }
    
    public void setPerson()
    {
        // Nothing yet...
    }
    
    public class Person
    {
        private int id;
        private String fatherName;
        private String motherName;
        private String name;
        private Date dateOfBirth;
        private String placeOfBirth;
        private Date dateOfDeath;
        private String placeOfDeath;

        public int getId()
        {
            return id;
        }

        public String getFatherName()
        {
            return fatherName;
        }

        public String getMotherName()
        {
            return motherName;
        }

        public String getName()
        {
            return name;
        }

        public Date getDateOfBirth()
        {
            return dateOfBirth;
        }

        public String getPlaceOfBirth()
        {
            return placeOfBirth;
        }

        public Date getDateOfDeath()
        {
            return dateOfDeath;
        }

        public String getPlaceOfDeath()
        {
            return placeOfDeath;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public void setFatherName(String fatherName)
        {
            this.fatherName = fatherName;
        }

        public void setMotherName(String motherName)
        {
            this.motherName = motherName;
        }

        public void setName(String name)
        {
            this.name = name;
        }
        
        public void setDateOfBirth(Calendar dateOfBirth)
        {
            setDateOfBirth(dateOfBirth.getTime());
        }

        public void setDateOfBirth(Date dateOfBirth)
        {
            this.dateOfBirth = dateOfBirth;
        }

        public void setPlaceOfBirth(String placeOfBirth)
        {
            this.placeOfBirth = placeOfBirth;
        }

        public void setDateOfDeath(Calendar dateOfDeath)
        {
            setDateOfDeath(dateOfDeath.getTime());
        }
        
        public void setDateOfDeath(Date dateOfDeath)
        {
            this.dateOfDeath = dateOfDeath;
        }

        public void setPlaceOfDeath(String placeOfDeath)
        {
            this.placeOfDeath = placeOfDeath;
        }
    }
}
