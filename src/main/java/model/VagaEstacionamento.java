package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vaga_estacionamento") 
public class VagaEstacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String descricao;

    private String obs;

    @Column(name = "metragem_vaga")
    private float metragemVaga;

    @Column(nullable = false, length = 1)
    private char status;

    public VagaEstacionamento() {
    }

    public VagaEstacionamento(int id, String descricao, String obs, float metragemVaga, char status) {
        this.id = id;
        this.descricao = descricao;
        this.obs = obs;
        this.metragemVaga = metragemVaga;
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

    public float getMetragemVaga() {
        return metragemVaga;
    }

    public void setMetragemVaga(float metragemVaga) {
        this.metragemVaga = metragemVaga;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        if ((status == 'A') || (status == 'O') || (status == 'L') || 
            (status == 'a') || (status == 'o') || (status == 'l')) {
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