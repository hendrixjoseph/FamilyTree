/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import temp.exceptions.NonexistentEntityException;
import temp.exceptions.PreexistingEntityException;
import temp.exceptions.RollbackFailureException;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class PersonViewJpaController implements Serializable
{

    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("edu.wright.hendrix11.familyTree");
        EntityManager em = emf.createEntityManager();
        PersonView pv = em.find(PersonView.class, 9511);
        System.out.println(pv.getName());
        em.close();
        emf.close();
    }

    /*
    public PersonViewJpaController(UserTransaction utx, EntityManagerFactory emf)
    {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create(PersonView personView) throws PreexistingEntityException, RollbackFailureException, Exception
    {
        EntityManager em = null;
        try
        {
            utx.begin();
            em = getEntityManager();
            em.persist(personView);
            utx.commit();
        }
        catch (Exception ex)
        {
            try
            {
                utx.rollback();
            }
            catch (Exception re)
            {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPersonView(personView.getId()) != null)
            {
                throw new PreexistingEntityException("PersonView " + personView + " already exists.", ex);
            }
            throw ex;
        }
        finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public void edit(PersonView personView) throws NonexistentEntityException, RollbackFailureException, Exception
    {
        EntityManager em = null;
        try
        {
            utx.begin();
            em = getEntityManager();
            personView = em.merge(personView);
            utx.commit();
        }
        catch (Exception ex)
        {
            try
            {
                utx.rollback();
            }
            catch (Exception re)
            {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0)
            {
                BigInteger id = personView.getId();
                if (findPersonView(id) == null)
                {
                    throw new NonexistentEntityException("The personView with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }
        finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public void destroy(BigInteger id) throws NonexistentEntityException, RollbackFailureException, Exception
    {
        EntityManager em = null;
        try
        {
            utx.begin();
            em = getEntityManager();
            PersonView personView;
            try
            {
                personView = em.getReference(PersonView.class, id);
                personView.getId();
            }
            catch (EntityNotFoundException enfe)
            {
                throw new NonexistentEntityException("The personView with id " + id + " no longer exists.", enfe);
            }
            em.remove(personView);
            utx.commit();
        }
        catch (Exception ex)
        {
            try
            {
                utx.rollback();
            }
            catch (Exception re)
            {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        }
        finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public List<PersonView> findPersonViewEntities()
    {
        return findPersonViewEntities(true, -1, -1);
    }

    public List<PersonView> findPersonViewEntities(int maxResults, int firstResult)
    {
        return findPersonViewEntities(false, maxResults, firstResult);
    }

    private List<PersonView> findPersonViewEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PersonView.class));
            Query q = em.createQuery(cq);
            if (!all)
            {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        }
        finally
        {
            em.close();
        }
    }

    public PersonView findPersonView(BigInteger id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(PersonView.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public int getPersonViewCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PersonView> rt = cq.from(PersonView.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        }
        finally
        {
            em.close();
        }
    }
    */
}
