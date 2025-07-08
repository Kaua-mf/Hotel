package service;

import java.util.ArrayList;
import java.util.List;
import model.Marca;
import model.Modelo;

public class ServicoModelo {

    private static List<Modelo> listaModelos = new ArrayList<>();
    private static int proximoId = 1;

    static {
        ServicoMarca servicoMarca = new ServicoMarca();
        List<Marca> marcas = servicoMarca.buscarTodos();

        if (!marcas.isEmpty()) {
            listaModelos.add(new Modelo(proximoId++, "Uno", 'A', marcas.get(0)));
            listaModelos.add(new Modelo(proximoId++, "Onix", 'A', marcas.get(1))); 
            listaModelos.add(new Modelo(proximoId++, "Ka", 'A', marcas.get(2))); 
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