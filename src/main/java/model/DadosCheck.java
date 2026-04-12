package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "dados_check")
public class DadosCheck implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int checkId;
    private String hospedePrincipal;
    private String quartosUtilizados;
    private String vagasUtilizadas;
    private Double valorTotal;
    private String IDquartosUtilizados;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFinalizacao;
    private String observacaoGeral;
public void setIDquartosUtilizados(String IDquartosUtilizados) {
    this.IDquartosUtilizados = IDquartosUtilizados;
}
    public DadosCheck() {}

    // Getters e Setters (Gerar automaticamente no NetBeans: Alt+Insert)
    public void setCheckId(int checkId) { this.checkId = checkId; }
    public void setHospedePrincipal(String hospedePrincipal) { this.hospedePrincipal = hospedePrincipal; }
    public void setQuartosUtilizados(String quartosUtilizados) { this.quartosUtilizados = quartosUtilizados; }
    public void setVagasUtilizadas(String vagasUtilizadas) { this.vagasUtilizadas = vagasUtilizadas; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }
    public void setDataFinalizacao(Date dataFinalizacao) { this.dataFinalizacao = dataFinalizacao; }
    public void setObservacaoGeral(String observacaoGeral) { this.observacaoGeral = observacaoGeral; }
}