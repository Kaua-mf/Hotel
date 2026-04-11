package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "check_in_out")
public class Check implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "data_hora_entrada")
    private String dataHoraEntrada;

    @Column(name = "data_hora_saida")
    private String dataHoraSaida;

    private String obs;
    private String status;

    @Column(name = "data_hora_cadastro")
    private String dataHoraCadastro;

    @OneToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    public Check() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(String dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public String getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(String dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
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

    public String getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(String dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}