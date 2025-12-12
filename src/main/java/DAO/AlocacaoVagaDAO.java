package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.AlocacaoVaga;
import utilities.JPAUtil;

public class AlocacaoVagaDAO implements InterfaceDAO<AlocacaoVaga> {

    @Override
    public void Create(AlocacaoVaga objeto) {
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
    public AlocacaoVaga Retrieve(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(AlocacaoVaga.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<AlocacaoVaga> Retrieve() {
        return Retrieve(null, null);
    }

    @Override
    public List<AlocacaoVaga> Retrieve(String atributo, String valor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT av FROM AlocacaoVaga av";
            if (atributo != null && !atributo.isEmpty() && valor != null) {
                jpql += " WHERE av." + atributo + " LIKE :valor";
            }
            TypedQuery<AlocacaoVaga> query = em.createQuery(jpql, AlocacaoVaga.class);
            if (atributo != null && !atributo.isEmpty() && valor != null) {
                query.setParameter("valor", "%" + valor + "%");
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void Update(AlocacaoVaga objeto) {
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
    public void Delete(AlocacaoVaga objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            AlocacaoVaga objRemover = em.merge(objeto);
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