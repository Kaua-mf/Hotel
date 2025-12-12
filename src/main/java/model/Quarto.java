package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quarto") 
public class Quarto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String descricao;
    
    @Column(name = "capacidade_hospedes") 
    private int capacidadeHospedes; 
    
    private float metragem;
    
    @Column(nullable = false)
    private String identificacao; 
    
    private int andar;
    
    @Column(name = "flag_animais")
    private char flagAnimais; 
    
    private String obs;
    
    @Column(nullable = false, length = 1)
    private char status; 
    
    @Column(name = "valor_diaria", nullable = false)
    private float valorDiaria; 

    public Quarto() {
    }

    public Quarto(int id, String descricao, int capacidadeHospedes, float metragem, String identificacao, int andar, char flagAnimais, String obs, char status, float valorDiaria) {
        this.id = id;
        this.descricao = descricao;
        this.capacidadeHospedes = capacidadeHospedes;
        this.metragem = metragem;
        this.identificacao = identificacao;
        this.andar = andar;
        this.flagAnimais = flagAnimais;
        this.obs = obs;
        this.status = status;
        this.valorDiaria = valorDiaria;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getCapacidadeHospedes() { return capacidadeHospedes; }
    public void setCapacidadeHospedes(int capacidadeHospedes) { this.capacidadeHospedes = capacidadeHospedes; }

    public float getMetragem() { return metragem; }
    public void setMetragem(float metragem) { this.metragem = metragem; }

    public String getIdentificacao() { return identificacao; }
    public void setIdentificacao(String identificacao) { this.identificacao = identificacao; }

    public int getAndar() { return andar; }
    public void setAndar(int andar) { this.andar = andar; }

    public char getFlagAnimais() { return flagAnimais; }
    
    public void setFlagAnimais(char flagAnimais) { 
        if (flagAnimais == 'S' || flagAnimais == 's' || flagAnimais == 'N' || flagAnimais == 'n') {
            this.flagAnimais = flagAnimais;
        } else {
            this.flagAnimais = 'N'; 
        }
    }

    public String getObs() { return obs; }
    public void setObs(String obs) { this.obs = obs; } 

    public char getStatus() { return status; }
    
    public void setStatus(char status) { 
        if ((status == 'A') || (status == 'C') || (status == 'O') || (status == 'a') || (status == 'c') || (status == 'o')) {
            this.status = status;
        } else {
            this.status = 'A'; 
        }
    }

    public float getValorDiaria() { return valorDiaria; }
    public void setValorDiaria(float valorDiaria) { this.valorDiaria = valorDiaria; }

    @Override
    public String toString() {
        return this.getIdentificacao() + " - " + this.getDescricao();
    }
}