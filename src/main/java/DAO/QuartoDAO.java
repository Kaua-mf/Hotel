package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Quarto;
import utilities.JPAUtil;

public class QuartoDAO implements InterfaceDAO<Quarto> {

    @Override
    public void Create(Quarto objeto) {
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
    public Quarto Retrieve(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Quarto.class, id);
        } finally {
            em.close();
        }
    }

    // Método auxiliar
    public Quarto buscar(int id) {
        return Retrieve(id);
    }

    @Override
    public List<Quarto> Retrieve() {
        return Retrieve(null, null);
    }

    @Override
    public List<Quarto> Retrieve(String atributo, String valor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT q FROM Quarto q";
            
            if (atributo != null && !atributo.trim().isEmpty() && valor != null) {
                // Se o atributo for "descricao", "identificacao", etc.
                jpql += " WHERE q." + atributo + " LIKE :valor";
            }
            
            TypedQuery<Quarto> query = em.createQuery(jpql, Quarto.class);
            
            if (atributo != null && !atributo.trim().isEmpty() && valor != null) {
                query.setParameter("valor", "%" + valor + "%");
            }
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void Update(Quarto objeto) {
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
    public void Delete(Quarto objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Quarto objParaRemover = em.merge(objeto);
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