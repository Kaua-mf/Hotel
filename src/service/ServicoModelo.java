package service;

import java.util.ArrayList;
import java.util.List;
import model.Marca;
import model.Modelo;

public class ServicoModelo {

    private static List<Modelo> listaModelos = new ArrayList<>();
    private static int proximoId = 1;

    static {
        // Para criar modelos, primeiro precisamos de algumas marcas
        ServicoMarca servicoMarca = new ServicoMarca();
        List<Marca> marcas = servicoMarca.buscarTodos();

        // Verificamos se a lista de marcas não está vazia
        if (!marcas.isEmpty()) {
            // Adicionando modelos de exemplo associados às marcas existentes
            listaModelos.add(new Modelo(proximoId++, "Uno", 'A', marcas.get(0))); // Fiat Uno
            listaModelos.add(new Modelo(proximoId++, "Onix", 'A', marcas.get(1))); // Chevrolet Onix
            listaModelos.add(new Modelo(proximoId++, "Ka", 'A', marcas.get(2))); // Ford Ka
        }
    }

    public void salvar(Modelo modelo) {
        modelo.setId(proximoId++);
        listaModelos.add(modelo);
    }

    public List<Modelo> buscarTodos() {
        return listaModelos;
    }
}