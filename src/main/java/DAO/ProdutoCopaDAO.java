package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.ProdutoCopa;
import utilities.JPAUtil;

public class ProdutoCopaDAO implements InterfaceDAO<ProdutoCopa> {

    @Override
    public void Create(ProdutoCopa objeto) {
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
    public ProdutoCopa Retrieve(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(ProdutoCopa.class, id);
        } finally {
            em.close();
        }
    }

    public ProdutoCopa buscar(int id) {
        return Retrieve(id);
    }

    @Override
    public List<ProdutoCopa> Retrieve() {
        return Retrieve(null, null);
    }

    @Override
    public List<ProdutoCopa> Retrieve(String atributo, String valor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT p FROM ProdutoCopa p";
            
            if (atributo != null && !atributo.trim().isEmpty() && valor != null) {
                jpql += " WHERE p." + atributo + " LIKE :valor";
            }
            
            TypedQuery<ProdutoCopa> query = em.createQuery(jpql, ProdutoCopa.class);
            
            if (atributo != null && !atributo.trim().isEmpty() && valor != null) {
                query.setParameter("valor", "%" + valor + "%");
            }
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void Update(ProdutoCopa objeto) {
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
    public void Delete(ProdutoCopa objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            ProdutoCopa objParaRemover = em.merge(objeto);
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