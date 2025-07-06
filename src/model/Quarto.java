package model;

public class Quarto {

    private int id;
    private String descricao;
    private float valorDiaria;
    private String obs;
    private char status;
    private TipoQuarto tipoQuarto; 

    public Quarto() {
    }

    public Quarto(int id, String descricao, float valorDiaria, String obs, char status, TipoQuarto tipoQuarto) {
        this.id = id;
        this.descricao = descricao;
        this.valorDiaria = valorDiaria;
        this.obs = obs;
        this.status = status;
        this.tipoQuarto = tipoQuarto;
    }
    
    // Getters e Setters
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

    public float getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(float valorDiaria) {
        this.valorDiaria = valorDiaria;
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

    public TipoQuarto getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(TipoQuarto tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }
}