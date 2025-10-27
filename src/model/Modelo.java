package model;

public class Modelo {

    private int id;
    private String descricao;
    private char status;
    private String nome;
    private Marca marca;

    public Modelo() {
    }

    public Modelo(int id,String nome, String descricao, char status, Marca marca) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
        this.marca = marca;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        if ((status == 'A') || (status == 'C') || (status == 'a') || (status == 'c')) {
            this.status = status;
        } else {
            this.status = 'A';
        }
    }

@Override
public String toString() {
    return this.getDescricao();
}

}