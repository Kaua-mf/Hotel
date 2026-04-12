package DAO;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.DadosCheck;

public class DadosCheckDAO {

private EntityManagerFactory emf = Persistence.createEntityManagerFactory("hotel");

@Column(name = "IDquartosUtilizados")
private String IDquartosUtilizados;

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public String getIDquartosUtilizados() {
        return IDquartosUtilizados;
    }

    public void setIDquartosUtilizados(String IDquartosUtilizados) {
        this.IDquartosUtilizados = IDquartosUtilizados;
    }

    public void Create(DadosCheck obj) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}