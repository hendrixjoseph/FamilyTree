#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import edu.wright.hendrix11.familyTree.entity.${NAME};

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

#parse("File Header.java")
@Stateless
public class ${NAME}DataBean extends AbstractDataBean<${NAME},${ENTITY_KEY}>
{
    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    @Override
    @PostConstruct
    public void initialize()
    {
        initialize(em, ${NAME}.class);
    }
}