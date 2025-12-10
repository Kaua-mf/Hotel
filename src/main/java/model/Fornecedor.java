package model;


public class Fornecedor extends Pessoa{
    
    private String razaoSocial;
    private String cnpj;
    private String inscricaoEstadual;
    private String contato;
    private String sexo;
    public Fornecedor() {
    }

    public Fornecedor( String datacadastro,int id, String nome, String fone1, String fone2, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String dataCadastro, String cpf, String rg,String razaoSocial, String cnpj, String inscricaoEstadual, String contato, String obs, char status, String sexo) {
        super(id, nome, fone1, fone2, email, cep, logradouro, bairro, cidade, complemento, dataCadastro, cpf, rg, obs, status, sexo);
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.contato = contato;
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
        return fone1;
    }

    public void setFone(String fone1) {
        this.fone1 = fone1;
    }
 public String getFone2() {
        return fone1;
    }

    public void setFone2(String fone1) {
        this.fone1 = fone1;
    }
    public String getFone1() {
        return fone1;
    }

    public void setFone1(String fone1) {
        this.fone1 = fone1;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return  super.toString() + 
                "\ncnpj   = " + this.getCnpj()+
                "\nCep    = " + this.getCnpj()+
                "\nCidade = " + this.getCidade()+
                "\nBairro = " + this.getBairro()+
                "\nLogradouro = " + this.getLogradouro()+
                "\nComplemento= " + this.getComplemento()+
                "\nContato    = " + this.getContato()+
                "\nStatus = " + this.getStatus();
    }

}