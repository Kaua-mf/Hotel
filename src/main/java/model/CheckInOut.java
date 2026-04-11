package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "check_in_out")
public class CheckInOut implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String data_hora_cadastro;
    private String data_hora_entrada;
    private String data_hora_saida;
    private String obs;
    private String status;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    public CheckInOut() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataHoraCadastro() {
        return data_hora_cadastro;
    }

    public void setDataHoraCadastro(String data_hora_cadastro) {
        this.data_hora_cadastro = data_hora_cadastro;
    }

    public String getDataHoraEntrada() {
        return data_hora_entrada;
    }

    public void setDataHoraEntrada(String data_hora_entrada) {
        this.data_hora_entrada = data_hora_entrada;
    }

    public String getDataHoraSaida() {
        return data_hora_saida;
    }

    public void setDataHoraSaida(String data_hora_saida) {
        this.data_hora_saida = data_hora_saida;
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

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}