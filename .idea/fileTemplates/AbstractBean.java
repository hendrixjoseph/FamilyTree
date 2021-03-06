#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import edu.wright.hendrix11.familyTree.dataBean.${NAME}DataBean;
import edu.wright.hendrix11.familyTree.entity.${NAME};

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class ${NAME}Bean extends AbstractBean<${NAME}> implements Serializable
{
    @EJB
    private ${NAME}DataBean ${NAME.toLowerCase()}DataBean;

    @Override
    @PostConstruct
    protected void initialize()
    {
        ${NAME.toLowerCase()}DataBean.initialize(${NAME}.class);
        super.initialize(${NAME.toLowerCase()}DataBean);
    }
}
