
package edu.wright.hendrix11.familyTree.bean;

import edu.wright.hendrix11.familyTree.database.table.PersonTable;
import edu.wright.hendrix11.familyTree.entity.Person;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
@ManagedBean
@ViewScoped
public class PersonsBean extends DataBean<Person>
{
    private static final String[] columns = {"ID","FATHER_NAME","MOTHER_NAME","NAME","GENDER","PLACE_OF_BIRTH","DATE_OF_BIRTH","PLACE_OF_DEATH","DATE_OF_DEATH"};
    private static final String[] prettyNames = {"Id","Father Name","Mother Name","Name","Gender","Place of Birth","Date of Birth","Place of Death","Date of Death"};

    @PostConstruct
    public void initialize()
    {
        table = new PersonTable();
        
        super.initialize(table);
    }
    
    @Override
    public String[] getPrettyNames()
    {
        return prettyNames;
    }
    
    @Override
    public String[] getColumns()
    {
        return columns;
    }
}