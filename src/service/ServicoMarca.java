package service;

import java.util.ArrayList;
import java.util.List;
import model.Marca;

public class ServicoMarca {

    private static List<Marca> listaMarcas = new ArrayList<>();
    private static int proximoId = 1;

    static {
        // Adicionando algumas marcas de exemplo
        listaMarcas.add(new Marca(proximoId++, "Fiat", 'A'));
        listaMarcas.add(new Marca(proximoId++, "Chevrolet", 'A'));
        listaMarcas.add(new Marca(proximoId++, "Ford", 'A'));
        listaMarcas.add(new Marca(proximoId++, "Volkswagen", 'I'));
    }

    public void salvar(Marca marca) {
        marca.setId(proximoId++);
        listaMarcas.add(marca);
    }

    public List<Marca> buscarTodos() {
        return listaMarcas;
    }
}