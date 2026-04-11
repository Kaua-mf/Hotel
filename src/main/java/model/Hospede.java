package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hospede") 
public class Hospede extends Pessoa implements Serializable {
    
    @Column(name = "razao_social")
    private String razaoSocial;
    
    private String cnpj;
    
    @Column(name = "inscricao_estadual")
    private String inscricaoEstadual;   
    private String sexo;
    private String contato;
    private String usuario; 
    private String senha;  
    
    public Hospede() {
    }
    
    public Hospede(int id, String nome, String fone1, String fone2, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String dataCadastro, String cpf, String rg, String obs, char status, String sexo, String razaoSocial, String cnpj, String inscricaoEstadual, String contato, String usuario, String senha) {
        super(id, nome, fone1, fone2, email, cep, logradouro, bairro, cidade, complemento, dataCadastro, cpf, rg, obs, status, sexo);
        
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.contato = contato;
        this.usuario = usuario;
        this.senha = senha;
    }
public String getSexo() {
    return sexo;
}

public void setSexo(String sexo) {
    this.sexo = sexo;
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

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
    
    public String getFone() {
        return super.getFone1();
    }

    public void setFone(String fone) {
        super.setFone1(fone); 
    }
   
}