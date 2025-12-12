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
@Table(name = "copa_quarto")
public class CopaQuarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private float quantidade;

    @Column(name = "valor_total")
    private float valorTotal;

    @Column(length = 1)
    private char status;

    private String obs;

    @ManyToOne
    @JoinColumn(name = "id_quarto")
    private Quarto quarto;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private ProdutoCopa produto;

    public CopaQuarto() {
    }

    public CopaQuarto(int id, float quantidade, float valorTotal, char status, String obs, Quarto quarto, ProdutoCopa produto) {
        this.id = id;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.status = status;
        this.obs = obs;
        this.quarto = quarto;
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
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

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public ProdutoCopa getProduto() {
        return produto;
    }

    public void setProduto(ProdutoCopa produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}