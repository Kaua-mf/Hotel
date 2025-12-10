package model;

public class Hospede extends Pessoa {
    
    private String razaoSocial;
    private String cnpj;
    private String inscricaoEstadual;   
    private String contato;
    private String usuario; 
    private String senha;  
    private String sexo;

    public Hospede() {
    }
    
    public Hospede( String usuario,String senha,int id, String nome, String fone1, String fone2, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String dataCadastro, String cpf, String rg, String razaoSocial, String cnpj, String inscricaoEstadual, String contato, String obs, char status, String sexo) {
        super(id, nome, fone1, fone2, email, cep, logradouro, bairro, cidade, complemento, dataCadastro, cpf, rg, obs, status, sexo);
        
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.contato = contato;
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
    
    public String getFone1() {
        return super.getFone1();
    }
    public void setFone1(String fone1) {
        super.setFone1(fone1);
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
public String getObs() { return obs; }
public void setObs(String obs) { this.obs = obs; } 

public String getDataCadastro() { return dataCadastro; }
public void setDataCadastro(String dataCadastro) { this.dataCadastro = dataCadastro; }

    @Override
    public String toString() {
        return  super.toString() + 
                "\nCNPJ = " + this.getCnpj() +
                "\nRazão Social = " + this.getRazaoSocial() +
                "\nInsc. Estadual = " + this.getInscricaoEstadual() +
                "\nContato = " + this.getContato() +
                "\nUsuário = " + this.getUsuario() +
                "\nStatus = " + this.getStatus();
    }
}