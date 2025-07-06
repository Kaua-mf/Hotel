package model;

public class Veiculo {

    private int id;
    private String placa;
    private String cor;
    private String obs;
    private char status;
    private Modelo modelo;

    public Veiculo() {
    }

    public Veiculo(int id, String placa, String cor, String obs, char status, Modelo modelo) {
        this.id = id;
        this.placa = placa;
        this.cor = cor;
        this.obs = obs;
        this.status = status;
        this.modelo = modelo;
    }

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

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
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
}