package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Fornecedor;
import utilities.JPAUtil;

public class FornecedorDAO implements InterfaceDAO<Fornecedor> {

    @Override
    public void Create(Fornecedor objeto) {
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
    public Fornecedor Retrieve(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Fornecedor.class, id);
        } finally {
            em.close();
        }
    }

    public Fornecedor buscar(int id) {
        return Retrieve(id);
    }
    
    public List<Fornecedor> Retrieve() {
        return Retrieve(null, null);
    }

    @Override
    public List<Fornecedor> Retrieve(String atributo, String valor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT f FROM Fornecedor f";
            
            if (atributo != null && !atributo.trim().isEmpty() && valor != null) {
                jpql += " WHERE f." + atributo + " LIKE :valor";
            }
            
            TypedQuery<Fornecedor> query = em.createQuery(jpql, Fornecedor.class);
            
            if (atributo != null && !atributo.trim().isEmpty() && valor != null) {
                query.setParameter("valor", "%" + valor + "%");
            }
            
            return query.getResultList();
            
        } finally {
            em.close();
        }
    }

    @Override
    public void Update(Fornecedor objeto) {
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
    public void Delete(Fornecedor objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Fornecedor fornecedorParaRemover = em.merge(objeto);
            em.remove(fornecedorParaRemover);
            
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}