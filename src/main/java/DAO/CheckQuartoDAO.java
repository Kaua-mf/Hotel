package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import model.CheckQuarto;
import utilities.JPAUtil;

public class CheckQuartoDAO {

    public void Create(CheckQuarto obj) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<CheckQuarto> Retrieve() {
        EntityManager em = JPAUtil.getEntityManager();
        List<CheckQuarto> lista = null;
        try {
            lista = em.createQuery("from CheckQuarto").getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
        return lista;
    }
}