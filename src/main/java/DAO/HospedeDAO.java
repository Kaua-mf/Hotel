package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Hospede;
import utilities.JPAUtil;

public class HospedeDAO implements InterfaceDAO<Hospede> {

    @Override
    public void Create(Hospede objeto) {
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
    public Hospede Retrieve(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Hospede.class, id);
        } finally {
            em.close();
        }
    }
    
    public Hospede buscar(int id) {
        return Retrieve(id);
    }

    @Override
    public List<Hospede> Retrieve() {
        return Retrieve(null, null);
    }

    @Override
    public List<Hospede> Retrieve(String atributo, String valor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT h FROM Hospede h";
            
            if (atributo != null && !atributo.trim().isEmpty() && valor != null) {
                jpql += " WHERE h." + atributo + " LIKE :valor";
            }
            
            TypedQuery<Hospede> query = em.createQuery(jpql, Hospede.class);
            
            if (atributo != null && !atributo.trim().isEmpty() && valor != null) {
                query.setParameter("valor", "%" + valor + "%");
            }
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void Update(Hospede objeto) {
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
    public void Delete(Hospede objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Hospede objParaRemover = em.merge(objeto);
            em.remove(objParaRemover);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}