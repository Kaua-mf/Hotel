package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import model.CheckQuarto;
import utilities.JPAUtil;
import static utilities.JPAUtil.getEntityManager;

public class CheckQuartoDAO {

    public void Create(CheckQuarto objeto) {
    EntityManager em = getEntityManager();
    try {
        em.getTransaction().begin();
        em.merge(objeto); 
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