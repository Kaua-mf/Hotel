package model;

public class Funcionario extends Pessoa {
    
    private String usuario;
    private String senha;
    private String sexo;
    public String getUsuario() {
        return usuario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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