package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Reserva;
import utilities.JPAUtil;

public class ReservaDAO implements InterfaceDAO<Reserva> {

    @Override
    public void Create(Reserva objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Reserva Retrieve(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Reserva.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Reserva> Retrieve() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT r FROM Reserva r", Reserva.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Reserva> Retrieve(String atributo, String valor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT r FROM Reserva r";
            if (atributo != null && !atributo.isEmpty() && valor != null) {
                if (atributo.equalsIgnoreCase("id")) {
                    jpql += " WHERE r." + atributo + " = :valor";
                } else {
                    jpql += " WHERE r." + atributo + " LIKE :valor";
                }
            }
            TypedQuery<Reserva> query = em.createQuery(jpql, Reserva.class);
            if (atributo != null && !atributo.isEmpty() && valor != null) {
                if (atributo.equalsIgnoreCase("id")) {
                    query.setParameter("valor", Integer.parseInt(valor));
                } else {
                    query.setParameter("valor", "%" + valor + "%");
                }
            }
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public void Update(Reserva objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void Delete(Reserva objeto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Reserva objRemover = em.merge(objeto);
            em.remove(objRemover);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}