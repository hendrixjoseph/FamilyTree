package temp;

@Entity
public class Person
{

  @Id
  private int id;
  
  private Person father;
  private Person mother;
  private String name;
  private String gender;
  private Date dateOfBirth;
  private String placeOfBirth;
  private Date dateOfDeath;
  private String placeOfDeath;
  
  
}
