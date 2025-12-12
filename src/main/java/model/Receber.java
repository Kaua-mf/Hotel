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
@Table(name = "receber")
public class Receber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "valor_total")
    private float valorTotal;

    @Column(name = "data_lancamento")
    private String dataLancamento;

    @Column(name = "data_vencimento")
    private String dataVencimento;

    @Column(name = "data_pagamento")
    private String dataPagamento;

    @Column(length = 1)
    private char status;

    private String obs;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "id_hospede")
    private Hospede hospede;

    public Receber() {
    }

    public Receber(int id, float valorTotal, String dataLancamento, String dataVencimento, String dataPagamento, char status, String obs, Reserva reserva, Hospede hospede) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.dataLancamento = dataLancamento;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.status = status;
        this.obs = obs;
        this.reserva = reserva;
        this.hospede = hospede;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
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

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}