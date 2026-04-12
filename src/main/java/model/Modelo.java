package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "modelo")
public class Modelo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    // Mudado para String para evitar erro de valor nulo com tipos primitivos (char)
    @Column(length = 1)
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_marca") 
    private Marca marca;

    public Modelo() {
    }

    public Modelo(int id, String nome, String status, Marca marca) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        // Validação simples para manter o padrão A ou C
        if (status != null && !status.isEmpty()) {
            this.status = status.toUpperCase().substring(0, 1);
        } else {
            this.status = "A";
        }
    }

    // Método que você usou na TelaBuscaVeiculo
    public String getDescricao() {
        return this.nome;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}