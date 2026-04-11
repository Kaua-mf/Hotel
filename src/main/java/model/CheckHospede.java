package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "check_hospede")
public class CheckHospede implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_check")
    private Check check;

    @ManyToOne
    @JoinColumn(name = "id_hospede")
    private Hospede hospede;

    private String obs;

    public CheckHospede() {
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Check getCheck() { return check; }
    public void setCheck(Check check) { this.check = check; }
    public Hospede getHospede() { return hospede; }
    public void setHospede(Hospede hospede) { this.hospede = hospede; }
    public String getObs() { return obs; }
    public void setObs(String obs) { this.obs = obs; }
}