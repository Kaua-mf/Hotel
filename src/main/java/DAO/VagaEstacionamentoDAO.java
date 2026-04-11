package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.VagaEstacionamento;
import utilities.JPAUtil;

public class VagaEstacionamentoDAO implements InterfaceDAO<VagaEstacionamento> {

    @Override
    public void Create(VagaEstacionamento objeto) {
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
    public VagaEstacionamento Retrieve(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(VagaEstacionamento.class, id);
        } finally {
            em.close();
        }
    }

    public VagaEstacionamento buscar(int id) {
        return Retrieve(id);
    }

    @Override
    public List<VagaEstacionamento> Retrieve() {
        return Retrieve(null, null);
    }

    @Override
    public List<VagaEstacionamento> Retrieve(String atributo, String valor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT v FROM VagaEstacionamento v";
            
            if (atributo != null && !atributo.trim().isEmpty() && valor != null) {
                jpql += " WHERE v." + atributo + " LIKE :valor";
            }
            
            TypedQuery<VagaEstacionamento> query = em.createQuery(jpql, VagaEstacionamento.class);
            
            if (atributo != null && !atributo.trim().isEmpty() && valor != null) {
                query.setParameter("valor", "%" + valor + "%");
            }
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void Update(VagaEstacionamento objeto) {
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
    public void Delete(VagaEstacionamento objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            VagaEstacionamento objParaRemover = em.merge(objeto);
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