package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Receber; 
import utilities.JPAUtil;

public class ReceberDAO implements InterfaceDAO<Receber> {

    @Override
    public void Create(Receber objeto) {
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
    public Receber Retrieve(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Receber.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Receber> Retrieve() {
        return Retrieve(null, null);
    }

    @Override
    public List<Receber> Retrieve(String atributo, String valor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT r FROM Receber r";
            if (atributo != null && !atributo.isEmpty() && valor != null) {
                jpql += " WHERE r." + atributo + " LIKE :valor";
            }
            TypedQuery<Receber> query = em.createQuery(jpql, Receber.class);
            if (atributo != null && !atributo.isEmpty() && valor != null) {
                query.setParameter("valor", "%" + valor + "%");
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void Update(Receber objeto) {
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
    public void Delete(Receber objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Receber objRemover = em.merge(objeto);
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