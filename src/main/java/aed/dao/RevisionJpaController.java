package aed.dao;

import aed.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import aed.map.Revision;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DOMINGO
 */
public class RevisionJpaController implements Serializable {

    public RevisionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public RevisionJpaController() {
        emf = Persistence.createEntityManagerFactory("concesionarioUP");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Revision revision) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(revision);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Revision revision) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            revision = em.merge(revision);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = revision.getIdRevision();
                if (findRevision(id) == null) {
                    throw new NonexistentEntityException("La revision con Id " + id + " no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Revision> findRevisionEntities() {
        return findRevisionEntities(true, -1, -1);
    }

    public List<Revision> findRevisionEntities(int maxResults, int firstResult) {
        return findRevisionEntities(false, maxResults, firstResult);
    }

    private List<Revision> findRevisionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Revision> cq = em.getCriteriaBuilder().createQuery(Revision.class);
            Root<Revision> root = cq.from(Revision.class);
            cq.select(root);
            TypedQuery<Revision> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Revision findRevision(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Revision.class, id);
        } finally {
            em.close();
        }
    }

    public int getRevisionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            Root<Revision> rt = cq.from(Revision.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            TypedQuery<Long> q = em.createQuery(cq);
            return q.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

}
