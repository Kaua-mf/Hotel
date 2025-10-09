package model;

public class Funcionario extends Pessoa {
    
    // Atributos específicos de Funcionario
    private String usuario;
    private String senha;

    /**
     * Construtor padrão.
     */
    public Funcionario() {
        super(); // Chama o construtor da classe mãe (Pessoa)
    }

    // Getters e Setters apenas para os atributos desta classe

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
    
    // Opcional: um getter mais intuitivo para o fone principal.
    // O DAO já foi ajustado para usar getFone1() e getFone2(), então isso é apenas para conveniência.
    public String getFone() {
        return this.getFone1();
    }
    
    public void setFone(String fone) {
        this.setFone1(fone);
    }

    /**
     * Sobrescrita do toString() para incluir os dados de Funcionario.
     */
    @Override
    public String toString() {
        // Aproveita o toString() da classe Pessoa e adiciona as informações de Funcionário
        return super.toString() +
               "\nUsuário = " + this.getUsuario();
               // Não inclua a senha no toString por segurança!
    }
}