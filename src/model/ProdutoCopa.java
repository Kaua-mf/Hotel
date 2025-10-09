package model;

public class ProdutoCopa {

    private int id;
    private String descricao;
    private float valor;
    private char status;
    private String obs;
    
    public ProdutoCopa() {
    }

    public ProdutoCopa(String obs,int id, String descricao, float valor, char status) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.status = status;
        this.obs = obs;
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

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
    
     public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}