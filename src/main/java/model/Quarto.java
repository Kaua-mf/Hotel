package model;

public class Quarto {
    private int id;
    private String descricao;
    private int capacidadeHospedes;
    private float metragem;
    private String identificacao;
    private int andar;
    private boolean flagAnimais;
    private String obs;
    private char status;
    private float valor;

    public Quarto() {
    }

    public Quarto(int id, String descricao, int capacidadeHospedes, float metragem, String identificacao, int andar, boolean flagAnimais, String obs, char status, float valordiaria) {
        this.id = id;
        this.descricao = descricao;
        this.capacidadeHospedes = capacidadeHospedes;
        this.metragem = metragem;
        this.identificacao = identificacao;
        this.andar = andar;
        this.flagAnimais = flagAnimais;
        this.obs = obs;
        this.status = status;
        this.valor = valordiaria;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCapacidadeHospedes() {
        return this.capacidadeHospedes;
    }

    public void setCapacidadeHospedes(int capacidadeHospedes) {
        this.capacidadeHospedes = capacidadeHospedes;
    }

    public float getMetragem() {
        return this.metragem;
    }

    public void setMetragem(float metragem) {
        this.metragem = metragem;
    }

    public String getIdentificacao() {
        return this.identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public int getAndar() {
        return this.andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public boolean isFlagAnimais() {
        return this.flagAnimais;
    }

    public void setFlagAnimais(boolean flagAnimais) {
        this.flagAnimais = flagAnimais;
    }

    public String getObs() {
        return this.obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public char getStatus() {
        return this.status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
    
     public float getValorDiaria() {
        return valor;
    }

    public void setValorDiaria(float valorDiaria) {
        this.valor = valorDiaria;
    }

}
