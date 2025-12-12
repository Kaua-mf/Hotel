package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "funcionario") 
public class Funcionario extends Pessoa {
    
    @Column(nullable = false, unique = true) 
    private String usuario;
    
    @Column(nullable = false)
    private String senha;
    
    public Funcionario() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getFone() {
        return this.getFone1(); 
    }
    
    public void setFone(String fone) {
        this.setFone1(fone); 
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nUsuário = " + this.getUsuario();
    }
}