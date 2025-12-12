package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.Funcionario;
import utilities.JPAUtil;

public class FuncionarioDAO implements InterfaceDAO<Funcionario> {

    @Override
    public void Create(Funcionario objeto) {
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
    public Funcionario Retrieve(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Funcionario.class, id);
        } finally {
            em.close();
        }
    }
    
    public Funcionario buscar(int id) {
        return Retrieve(id);
    }

    @Override
    public List<Funcionario> Retrieve() {
        return Retrieve(null, null);
    }

    @Override
    public List<Funcionario> Retrieve(String atributo, String valor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT f FROM Funcionario f";
            
            if (atributo != null && !atributo.trim().isEmpty() && valor != null) {
                jpql += " WHERE f." + atributo + " LIKE :valor";
            }
            
            TypedQuery<Funcionario> query = em.createQuery(jpql, Funcionario.class);
            
            if (atributo != null && !atributo.trim().isEmpty() && valor != null) {
                query.setParameter("valor", "%" + valor + "%");
            }
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void Update(Funcionario objeto) {
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
    public void Delete(Funcionario objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Funcionario objParaRemover = em.merge(objeto);
            em.remove(objParaRemover);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    public Funcionario buscarPorUsuarioSenha(String usuario, String senha) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT f FROM Funcionario f WHERE f.usuario = :usuario AND f.senha = :senha";
            
            TypedQuery<Funcionario> query = em.createQuery(jpql, Funcionario.class);
            query.setParameter("usuario", usuario);
            query.setParameter("senha", senha);
            
            return query.getSingleResult();
            
        } catch (NoResultException e) {
            return null; 
        } finally {
            em.close();
        }
    }
}