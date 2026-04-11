package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Veiculo;
import utilities.JPAUtil;

public class VeiculoDAO implements InterfaceDAO<Veiculo> {

    @Override
    public void Create(Veiculo objeto) {
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
    public Veiculo Retrieve(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Veiculo.class, id);
        } finally {
            em.close();
        }
    }

    public Veiculo buscar(int id) {
        return Retrieve(id);
    }

    @Override
    public List<Veiculo> Retrieve() {
        return Retrieve(null, null);
    }

    @Override
    public List<Veiculo> Retrieve(String atributo, String valor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT v FROM Veiculo v";
            
            if (atributo != null && !atributo.trim().isEmpty() && valor != null) {
                jpql += " WHERE v." + atributo + " LIKE :valor";
            }
            
            TypedQuery<Veiculo> query = em.createQuery(jpql, Veiculo.class);
            
            if (atributo != null && !atributo.trim().isEmpty() && valor != null) {
                query.setParameter("valor", "%" + valor + "%");
            }
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void Update(Veiculo objeto) {
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
    public void Delete(Veiculo objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Veiculo objParaRemover = em.merge(objeto);
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