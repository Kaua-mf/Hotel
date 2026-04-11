package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "marca") 
public class Marca {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;
    
    @Column(nullable = false) 
    private String descricao;
    
    @Column(nullable = false, length = 1) 
    private char status;

    public Marca() {
    }

    public Marca(int id, String descricao, char status) {
        this.id = id;
        this.descricao = descricao;
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

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        if ((status == 'A') || (status == 'C') || (status == 'a') || (status == 'c')) {
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