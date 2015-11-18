#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue(value = "${NAME.toLowerCase()}")
@NamedQueries({
                      @NamedQuery(name = ${NAME}.FIND_BY_NAME, query = "SELECT p FROM ${NAME} p WHERE p.name = :name"),
                      @NamedQuery(name = ${NAME}.FIND_ALL, query = "SELECT p FROM ${NAME} p")
              })
public class ${NAME} extends Place
{

    public static final String FIND_ALL = "${NAME}.findAll";
    public static final String FIND_BY_NAME = "${NAME}.findByName";

    @Override
    public String getLink()
    {
        return null;
    }
}