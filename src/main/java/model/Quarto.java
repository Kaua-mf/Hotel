package model;

public class Quarto {
    
    private int id;
    private String descricao;
    private int capacidadeHospedes; 
    private float metragem;
    private String identificacao;
    private int andar;
    private char flagAnimais;
    private String obs;
    private char status; 
    private float valorDiaria; 

    public Quarto() {}

    
    public int getId() { return id; }
    public String getDescricao() { return descricao; }
    public int getCapacidadeHospedes() { return capacidadeHospedes; }
    public float getMetragem() { return metragem; }
    public String getIdentificacao() { return identificacao; }
    public int getAndar() { return andar; }
    public char getFlagAnimais() { return flagAnimais; }
    public String getObs() { return obs; }
    public char getStatus() { return status; }
    public float getValorDiaria() { return valorDiaria; }

    public void setId(int id) { this.id = id; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setCapacidadeHospedes(int capacidadeHospedes) { this.capacidadeHospedes = capacidadeHospedes; }
    public void setMetragem(float metragem) { this.metragem = metragem; }
    public void setIdentificacao(String identificacao) { this.identificacao = identificacao; }
    public void setAndar(int andar) { this.andar = andar; }
    public void setFlagAnimais(char flagAnimais) { this.flagAnimais = flagAnimais; }
    public void setObs(String obs) { this.obs = obs; } 
    public void setStatus(char status) { this.status = status; }
    public void setValorDiaria(float valorDiaria) { this.valorDiaria = valorDiaria; }

    @Override
    public String toString() {
        return "Quarto{" + "id=" + id + ", identificacao=" + identificacao + ", numero=" + ", status=" + status + ", valorDiaria=" + valorDiaria + '}';
    }
    
    
}