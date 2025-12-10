package model;

public class Modelo {

    private int id;
    private String nome; 
    private char status;
    private Marca marca; 

    public Modelo() {
    }

    public Modelo(int id, String nome, char status, Marca marca) {
        this.id = id;
        this.nome = nome; 
        this.status = status;
        this.marca = marca;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; } 
    public void setNome(String nome) { this.nome = nome; } 
    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }
    public char getStatus() { return status; }
    public void setStatus(char status) {  }

    @Override
    public String toString() {
        return this.getNome(); 
    }
    
}
