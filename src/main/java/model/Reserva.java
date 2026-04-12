package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "data_hora_reserva")
    private String dataHoraReserva;

    @Column(name = "data_prevista_entrada")
    private String dataPrevistaEntrada;

    @Column(name = "data_prevista_saida")
    private String dataPrevistaSaida;

    private String obs;
    private String status;

    @Column(name = "data_entrada")
    private String dataEntrada;

    @Column(name = "data_saida")
    private String dataSaida;

    @Column(name = "valor_total")
    private float valorTotal;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_hospede")
    private Hospede hospede;

    @OneToOne
    @JoinColumn(name = "check_id")
    private Check check;

    public Reserva() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // --- MÉTODOS CORRIGIDOS PARA O PADRÃO DA TELA ---

    public String getDataHoraReserva() {
        return dataHoraReserva;
    }

    public void setDataHoraReserva(String dataHoraReserva) {
        this.dataHoraReserva = dataHoraReserva;
    }

    public String getDataPrevistaEntrada() {
        return dataPrevistaEntrada;
    }

    public void setDataPrevistaEntrada(String dataPrevistaEntrada) {
        this.dataPrevistaEntrada = dataPrevistaEntrada;
    }

    public String getDataPrevistaSaida() {
        return dataPrevistaSaida;
    }

    public void setDataPrevistaSaida(String dataPrevistaSaida) {
        this.dataPrevistaSaida = dataPrevistaSaida;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ... outros getters e setters (DataEntrada, DataSaida, ValorTotal, etc) permanecem iguais ...

    public float getValorTotal() { return valorTotal; }
    public void setValorTotal(float valorTotal) { this.valorTotal = valorTotal; }
    
    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public Hospede getHospede() { return hospede; }
    public void setHospede(Hospede hospede) { this.hospede = hospede; }

    public Check getCheck() { return check; }
    public void setCheck(Check check) { this.check = check; }

    @Override
    public String toString() {
        return "Reserva: " + this.id + " - " + (this.hospede != null ? this.hospede.getNome() : "Sem Hóspede");
    }
}