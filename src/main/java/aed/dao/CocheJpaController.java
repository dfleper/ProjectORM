package aed.dao;

import aed.dao.exceptions.IllegalOrphanException;
import aed.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import aed.map.Coche;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DOMINGO
 */
public class CocheJpaController implements Serializable {

    public CocheJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public CocheJpaController() {
        emf = Persistence.createEntityManagerFactory("concesionarioUP");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Coche coche) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(coche);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Coche coche) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            coche = em.merge(coche);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = coche.getIdCoche();
                if (findCoche(id) == null) {
                    throw new NonexistentEntityException("El Coche con la Id " + id + " no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Coche> findCocheEntities() {
        return findCocheEntities(true, -1, -1);
    }

    public List<Coche> findCocheEntities(int maxResults, int firstResult) {
        return findCocheEntities(false, maxResults, firstResult);
    }

    private List<Coche> findCocheEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
	        CriteriaQuery<Coche> cq = em.getCriteriaBuilder().createQuery(Coche.class);
	        Root<Coche> root = cq.from(Coche.class);
	        cq.select(root);
	        TypedQuery<Coche> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Coche findCoche(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Coche.class, id);
        } finally {
            em.close();
        }
    }

    public int getCocheCount() {
        EntityManager em = getEntityManager();
        try {
	        CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
	        Root<Coche> rt = cq.from(Coche.class);
	        cq.select(em.getCriteriaBuilder().count(rt));
	        TypedQuery<Long> q = em.createQuery(cq);
	        return q.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

}
