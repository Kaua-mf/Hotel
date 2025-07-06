package service;

import java.util.ArrayList;
import java.util.List;
import model.Veiculo;
// Importe os outros serviços que serão necessários
import service.ServicoModelo;
import service.ServicoMarca;

public class ServicoVeiculo {

    private static List<Veiculo> listaVeiculos = new ArrayList<>();
    private static int proximoId = 1;

    // Para popular os exemplos, precisamos de instâncias de Marca e Modelo
    static {
        ServicoMarca servicoMarca = new ServicoMarca();
        ServicoModelo servicoModelo = new ServicoModelo();
        
        // Adicionando um veículo de exemplo
        if (!servicoMarca.buscarTodos().isEmpty() && !servicoModelo.buscarTodos().isEmpty()) {
            listaVeiculos.add(new Veiculo(proximoId++, "ABC-1234", "Prata", 'A', servicoModelo.buscarTodos().get(0), servicoMarca.buscarTodos().get(0)));
        }
    }

    public void salvar(Veiculo veiculo) {
        veiculo.setId(proximoId++);
        listaVeiculos.add(veiculo);
    }

    public List<Veiculo> buscarTodos() {
        return listaVeiculos;
    }

    public List<Veiculo> buscarPorFiltro(String filtro, int tipoBusca) {
        List<Veiculo> resultados = new ArrayList<>();
        if (filtro.trim().isEmpty()) return listaVeiculos;

        switch (tipoBusca) {
            case 0: // Placa
                for (Veiculo v : listaVeiculos) {
                    if (v.getPlaca().toLowerCase().contains(filtro.toLowerCase())) {
                        resultados.add(v);
                    }
                }
                break;
            case 1: // Cor
                for (Veiculo v : listaVeiculos) {
                    if (v.getCor().toLowerCase().contains(filtro.toLowerCase())) {
                        resultados.add(v);
                    }
                }
                break;
        }
        return resultados;
    }
}