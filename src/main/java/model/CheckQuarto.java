package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "check_quarto")
public class CheckQuarto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "data_hora_inicio")
    private String dataHoraInicio;
    @Column(name = "data_hora_fim")
    private String dataHoraFim;
    @Column
    private String obs;
    @Column
    private char status;
    @JoinColumn(name = "quarto_id")
    @ManyToOne
    private Quarto quarto;
    @JoinColumn(name = "check_id")
    @ManyToOne
    private CheckInOut checkInOut;

    public CheckQuarto(int id, String dataHoraInicio, String dataHoraFim, String obs, char status, Quarto quarto, CheckInOut checkInOut) {
        this.id = id;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.obs = obs;
        this.status = status;
        this.quarto = quarto;
        this.checkInOut = checkInOut;
    }

    public CheckQuarto() {
    }
    
    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(String dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public String getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(String dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
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

    public CheckInOut getCheckInOut() {
        return checkInOut;
    }

    public void setCheckInOut(CheckInOut checkInOut) {
        this.checkInOut = checkInOut;
    }
    
    @Override
    public String toString() {
        return "Id = " + id + "/nData Hora Inicio = " + dataHoraInicio + 
                "/nData Hora Fim = " + dataHoraFim + "/nObs = " + obs + 
                "/nStatus = " + status + "/nQuarto = " + quarto + "\nCheck In Out" + checkInOut;
    }
    
}
