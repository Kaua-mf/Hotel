package model;

public class Funcionario extends Pessoa {
    
    private String usuario;
    private String senha;

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