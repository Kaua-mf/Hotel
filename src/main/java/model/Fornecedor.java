package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity 
@Table(name = "fornecedor") 
public class Fornecedor extends Pessoa {
    
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

    public Fornecedor(String datacadastro, int id, String nome, String fone1, String fone2, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String dataCadastro, String cpf, String rg, String razaoSocial, String cnpj, String inscricaoEstadual, String contato, String obs, char status, String sexo) {
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
    
    public String getFone() {
        return getFone1(); 
    }

    public void setFone(String fone1) {
        this.setFone1(fone1); 
    }

    public String getFone2() {
        return super.getFone2(); 
    }

    public void setFone2(String fone2) {
        super.setFone2(fone2); 
    }

    public String getFone1() {
        return super.getFone1();
    }

    public void setFone1(String fone1) {
        super.setFone1(fone1);
    }

    @Override
    public String getDataCadastro() {
        return super.getDataCadastro();
    }

    @Override
    public void setDataCadastro(String dataCadastro) {
        super.setDataCadastro(dataCadastro);
    }

    @Override
    public String getObs() {
        return super.getObs();
    }

    @Override
    public void setObs(String obs) {
        super.setObs(obs);
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return super.toString() + 
                "\nCNPJ: " + this.getCnpj() +
                "\nRazão Social: " + this.getRazaoSocial() +
                "\nInscrição Estadual: " + this.getInscricaoEstadual() +
                "\nContato: " + this.getContato();
    }
}