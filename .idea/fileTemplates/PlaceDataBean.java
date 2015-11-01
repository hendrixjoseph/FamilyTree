#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import edu.wright.hendrix11.familyTree.dataBean.AbstractDataBean;
import edu.wright.hendrix11.familyTree.entity.place.${NAME};

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

#parse("File Header.java")
@Stateless
public class ${NAME}DataBean extends AbstractDataBean<${NAME},Integer>
{
    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    @Override
    @PostConstruct
    protected void initialize()
    {
        initialize(em, ${NAME}.class);
    }
}