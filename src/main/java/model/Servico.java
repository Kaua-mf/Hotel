package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "servico") 
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;

    @Column(nullable = false) 
    private String descricao;

    private String obs; 

    @Column(nullable = false, length = 1)
    private char status;

    public Servico() {
    }

    public Servico(int id, String descricao, String obs, char status) {
        this.id = id;
        this.descricao = descricao;
        this.obs = obs;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        if ((status == 'A') || (status == 'I') || (status == 'a') || (status == 'i')) {
            this.status = status;
        } else {
            this.status = 'A'; 
        }
    }
    
    @Override
    public String toString() {
        return this.getDescricao();
    }
}