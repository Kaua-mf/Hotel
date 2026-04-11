package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import model.CheckHospede;
import utilities.JPAUtil;

public class CheckHospedeDAO implements InterfaceDAO<CheckHospede> {

    @Override
    public void Create(CheckHospede objeto) {
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
    public CheckHospede Retrieve(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(CheckHospede.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<CheckHospede> Retrieve() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT ch FROM CheckHospede ch", CheckHospede.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<CheckHospede> Retrieve(String atributo, String valor) {
        return null;
    }

    @Override
    public void Update(CheckHospede objeto) {
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
    public void Delete(CheckHospede objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            CheckHospede objRemover = em.merge(objeto);
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