package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alocacao_vaga")
public class AlocacaoVaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "data_entrada")
    private String dataEntrada;

    @Column(name = "data_saida")
    private String dataSaida;

    @Column(length = 1)
    private char status;

    private String obs;

    @ManyToOne
    @JoinColumn(name = "id_vaga")
    private VagaEstacionamento vagaEstacionamento;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    public AlocacaoVaga() {
    }

    public AlocacaoVaga(int id, String dataEntrada, String dataSaida, char status, String obs, VagaEstacionamento vagaEstacionamento, Reserva reserva) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.status = status;
        this.obs = obs;
        this.vagaEstacionamento = vagaEstacionamento;
        this.reserva = reserva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
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

    public VagaEstacionamento getVagaEstacionamento() {
        return vagaEstacionamento;
    }

    public void setVagaEstacionamento(VagaEstacionamento vagaEstacionamento) {
        this.vagaEstacionamento = vagaEstacionamento;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}