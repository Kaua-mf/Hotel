package model;

public class Veiculo {
    private int id;
    private String placa;
    private String cor;
    private char status;
    private Modelo modelo;
    private Marca marca;
    private Funcionario funcionario;
    private Fornecedor fornecedor;
    private Hospede hospede;

    public Veiculo() {
    }

    public Veiculo(int id, String placa, String cor, char status, Modelo modelo, Marca marca, Funcionario funcionario, Fornecedor fornecedor, Hospede hospede) {
        this.id = id;
        this.placa = placa;
        this.cor = cor;
        this.status = status;
        this.modelo = modelo;
        this.marca = marca;
        this.funcionario = funcionario;
        this.fornecedor = fornecedor;
        this.hospede = hospede;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Modelo getModelo() {
        return this.modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Marca getMarca() {
        return this.marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Fornecedor getFornecedor() {
        return this.fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Hospede getHospede() {
        return this.hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public char getStatus() {
        return this.status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
