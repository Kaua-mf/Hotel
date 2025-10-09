package model;

public class VagaEstacionamento {

    private int id;
    private String descricao;
    private String obs;
    private float metragemvaga;
    private char status;

    public VagaEstacionamento() {
    }

    public VagaEstacionamento(int id, String descricao, String obs, float metragemvaga, char status) {
        this.id = id;
        this.descricao = descricao;
        this.obs = obs;
        this.metragemvaga = metragemvaga;
        this.status = status;
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

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public float getMetragemVaga() {
        return metragemvaga;
    }

    public void setMetragemVaga(float metragemvaga) {
        this.metragemvaga = metragemvaga;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

 
}