package service;

import java.util.ArrayList;
import java.util.List;
import model.Veiculo;
import service.ServicoModelo;
import service.ServicoMarca;

public class ServicoVeiculo {

    private static List<Veiculo> listaVeiculos = new ArrayList<>();
    private static int proximoId = 1;

    static {
        ServicoMarca servicoMarca = new ServicoMarca();
        ServicoModelo servicoModelo = new ServicoModelo();
        
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
            case 0: 
                for (Veiculo v : listaVeiculos) {
                    if (v.getPlaca().toLowerCase().contains(filtro.toLowerCase())) {
                        resultados.add(v);
                    }
                }
                break;
            case 1:
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