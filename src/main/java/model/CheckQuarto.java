package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "check_quarto")
public class CheckQuarto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "check_id")
    private Check check; // Verifique se o nome é 'check'

    @ManyToOne
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;

    private String dataHoraInicio;
    private String dataHoraFim;
    private String obs;

    public CheckQuarto() {}

    // ESTE MÉDICO PRECISA EXISTIR EXATAMENTE ASSIM
    public void setCheck(Check check) {
        this.check = check;
    }
    
    public Check getCheck() {
        return check;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setDataHoraInicio(String dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public void setDataHoraFim(String dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}