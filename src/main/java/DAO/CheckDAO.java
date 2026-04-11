package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import model.Check;
import utilities.JPAUtil;

public class CheckDAO implements InterfaceDAO<Check> {

    @Override
    public void Create(Check objeto) {
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
    public Check Retrieve(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Check.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Check> Retrieve() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Check c", Check.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Check> Retrieve(String atributo, String valor) {
        return null;
    }

    @Override
    public void Update(Check objeto) {
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
    public void Delete(Check objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Check objRemover = em.merge(objeto);
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