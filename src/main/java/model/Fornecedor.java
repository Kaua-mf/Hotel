package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity 
@Table(name = "fornecedor") 
public class Fornecedor extends Pessoa implements Serializable {
    
    @Column(name = "razao_social") 
    private String razaoSocial;
    
    @Column(nullable = false)
    private String cnpj;
    
    @Column(name = "inscricao_estadual")
    private String inscricaoEstadual;
    
    private String contato;
    
    private String sexo; 

    public Fornecedor() {
    }

    public Fornecedor(int id, String nome, String fone1, String fone2, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String dataCadastro, String cpf, String rg, String razaoSocial, String cnpj, String inscricaoEstadual, String contato, String obs, char status, String sexo) {
        super(id, nome, fone1, fone2, email, cep, logradouro, bairro, cidade, complemento, dataCadastro, cpf, rg, obs, status, sexo);
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.contato = contato;
        this.sexo = sexo;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return super.getNome() + " (CNPJ: " + this.cnpj + ")";
    }
}