package service;

import java.util.ArrayList;
import java.util.List;
import model.ProdutoCopa;

public class ServicoProdutoCopa {

    private static List<ProdutoCopa> listaProdutos = new ArrayList<>();
    private static int proximoId = 1;

    static {
        listaProdutos.add(new ProdutoCopa(proximoId++, "Refrigerante Lata", 5.0f, 'A'));
        listaProdutos.add(new ProdutoCopa(proximoId++, "Água Mineral 500ml", 3.0f, 'A'));
        listaProdutos.add(new ProdutoCopa(proximoId++, "Chocolate Barra", 7.0f, 'I'));
    }
    
    public void salvar(ProdutoCopa produto) {
        produto.setId(proximoId++);
        listaProdutos.add(produto);
    }

    public List<ProdutoCopa> buscarTodos() {
        return listaProdutos;
    }
    
    public List<ProdutoCopa> buscarPorFiltro(String filtro, int tipoBusca) {
        List<ProdutoCopa> resultados = new ArrayList<>();
        if (filtro.trim().isEmpty()) return listaProdutos;

        switch (tipoBusca) {
            case 0: // ID
                try {
                    int id = Integer.parseInt(filtro);
                    for (ProdutoCopa p : listaProdutos) if (p.getId() == id) resultados.add(p);
                } catch (NumberFormatException e) {}
                break;
            case 1: // Descrição
                for (ProdutoCopa p : listaProdutos) if (p.getDescricao().toLowerCase().contains(filtro.toLowerCase())) resultados.add(p);
                break;
        }
        return resultados;
    }
}