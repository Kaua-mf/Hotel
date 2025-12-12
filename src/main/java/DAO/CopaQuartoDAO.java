package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.CopaQuarto;
import utilities.JPAUtil;

public class CopaQuartoDAO implements InterfaceDAO<CopaQuarto> {

    @Override
    public void Create(CopaQuarto objeto) {
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
    public CopaQuarto Retrieve(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(CopaQuarto.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<CopaQuarto> Retrieve() {
        return Retrieve(null, null);
    }

    @Override
    public List<CopaQuarto> Retrieve(String atributo, String valor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT cq FROM CopaQuarto cq";
            if (atributo != null && !atributo.isEmpty() && valor != null) {
                jpql += " WHERE cq." + atributo + " LIKE :valor";
            }
            TypedQuery<CopaQuarto> query = em.createQuery(jpql, CopaQuarto.class);
            if (atributo != null && !atributo.isEmpty() && valor != null) {
                query.setParameter("valor", "%" + valor + "%");
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void Update(CopaQuarto objeto) {
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
    public void Delete(CopaQuarto objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            CopaQuarto objRemover = em.merge(objeto);
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