#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import edu.wright.hendrix11.familyTree.entity.${ENTITY_CLASS};
import edu.wright.hendrix11.util.DataGatherer;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.io.Serializable;

#parse("File Header.java")
@Named
@ViewScoped
public class ${NAME} extends AbstractDataBean<${ENTITY_CLASS}> implements Serializable
{
    @PersistenceContext(unitName = "edu.wright.hendrix11.familyTree")
    private EntityManager em;

    @Override
    @PostConstruct
    public void initialize()
    {
        dataGatherer = new DataGatherer<>(em, ${ENTITY_CLASS}.class);
        setPage(1);
    }
}