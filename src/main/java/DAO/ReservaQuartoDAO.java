package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.ReservaQuarto;
import utilities.JPAUtil;

public class ReservaQuartoDAO implements InterfaceDAO<ReservaQuarto> {

    @Override
    public void Create(ReservaQuarto objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public ReservaQuarto Retrieve(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(ReservaQuarto.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<ReservaQuarto> Retrieve() {
        return Retrieve(null, null);
    }

    @Override
    public List<ReservaQuarto> Retrieve(String atributo, String valor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT rq FROM ReservaQuarto rq";
            if (atributo != null && !atributo.isEmpty() && valor != null) {
                jpql += " WHERE rq." + atributo + " LIKE :valor";
            }
            TypedQuery<ReservaQuarto> query = em.createQuery(jpql, ReservaQuarto.class);
            if (atributo != null && !atributo.isEmpty() && valor != null) {
                query.setParameter("valor", "%" + valor + "%");
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void Update(ReservaQuarto objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void Delete(ReservaQuarto objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            ReservaQuarto objRemover = em.merge(objeto);
            em.remove(objRemover);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}