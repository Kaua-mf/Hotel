package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Modelo;
import utilities.JPAUtil;

public class ModeloDAO implements InterfaceDAO<Modelo> {

    @Override
    public void Create(Modelo objeto) {
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
    public Modelo Retrieve(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Modelo.class, id);
        } finally {
            em.close();
        }
    }

    public Modelo buscar(int id) {
        return Retrieve(id);
    }

    @Override
    public List<Modelo> Retrieve() {
        return Retrieve(null, null);
    }

    @Override
    public List<Modelo> Retrieve(String atributo, String valor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT m FROM Modelo m";
            
            if (atributo != null && !atributo.trim().isEmpty() && valor != null) {
                if (atributo.equalsIgnoreCase("descricao")) {
                    jpql += " WHERE m.nome LIKE :valor";
                } else {
                    jpql += " WHERE m." + atributo + " LIKE :valor";
                }
            }
            
            TypedQuery<Modelo> query = em.createQuery(jpql, Modelo.class);
            
            if (atributo != null && !atributo.trim().isEmpty() && valor != null) {
                query.setParameter("valor", "%" + valor + "%");
            }
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void Update(Modelo objeto) {
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
    public void Delete(Modelo objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Modelo objParaRemover = em.merge(objeto);
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