package temp;

@Entity
@Table(name = "PERSON_VIEW")
public class Person
{
  @Id
  private int id;
  
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="FATHER_ID")
  private Person father;
  
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="MOTHER_ID")
  private Person mother;
  
  private String name;
  private String gender;
  
  @Column(name = "DATE_OF_BIRTH")
  private Date dateOfBirth;
  
  @Column(name = "PLACE_OF_BIRTH")
  private String placeOfBirth;
  
  @Column(name = "DATE_OF_DEATH")
  private Date dateOfDeath;
  
  @Column(name = "PLACE_OF_DEATH")
  private String placeOfDeath;
  
  
}
