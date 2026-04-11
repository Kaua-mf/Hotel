package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "funcionario") 
public class Funcionario extends Pessoa implements Serializable {
    
    @Column(nullable = false, unique = true) 
    private String usuario;
    
    @Column(nullable = false)
    private String senha;
    
    public Funcionario() {
    }

    public Funcionario(int id, String nome, String fone1, String fone2, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String dataCadastro, String cpf, String rg, String obs, char status, String sexo, String usuario, String senha) {
        super(id, nome, fone1, fone2, email, cep, logradouro, bairro, cidade, complemento, dataCadastro, cpf, rg, obs, status, sexo);
        this.usuario = usuario;
        this.senha = senha;
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
        return super.getFone1(); 
    }
    
    public void setFone(String fone) {
        super.setFone1(fone); 
    }

    @Override
    public String toString() {
        return super.getNome() + " (User: " + this.usuario + ")";
    }
}