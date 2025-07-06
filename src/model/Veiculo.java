package model;

public class Veiculo {

    private int id;
    private String placa;
    private String cor;
    private char status;
    
    // Relacionamentos com outras classes
    private Modelo modelo;
    private Marca marca;

    public Veiculo() {
    }

    public Veiculo(int id, String placa, String cor, char status, Modelo modelo, Marca marca) {
        this.id = id;
        this.placa = placa;
        this.cor = cor;
        this.status = status;
        this.modelo = modelo;
        this.marca = marca;
    }

    // Getters e Setters para todos os campos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}