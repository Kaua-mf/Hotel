package service;

import DAO.ProdutoCopaDAO;
import java.util.List;
import model.ProdutoCopa;
import java.util.ArrayList;
import java.util.Objects;

public class ServicoProdutoCopa {

    private ProdutoCopaDAO produtoCopaDAO = new ProdutoCopaDAO();

    public void salvar(ProdutoCopa produto) {
        if (produto.getId() == 0) {
            produtoCopaDAO.Create(produto);
        } else {
            throw new UnsupportedOperationException("Atualização não implementada.");
        }
    }

    public List<ProdutoCopa> buscarTodos() {
        return produtoCopaDAO.Retrieve(null, null);
    }
    
    public List<ProdutoCopa> buscarPorFiltro(String filtro, int tipoBusca) {
        if (Objects.isNull(filtro) || filtro.trim().isEmpty()) {
            return produtoCopaDAO.Retrieve(null, null);
        }

        String nomeColuna;

        switch (tipoBusca) {
            case 0:
                try {
                    int id = Integer.parseInt(filtro);
                    ProdutoCopa p = produtoCopaDAO.Retrieve(id);
                    List<ProdutoCopa> resultados = new ArrayList<>();
                    if (p != null) resultados.add(p);
                    return resultados;
                } catch (NumberFormatException e) {
                    return new ArrayList<>();
                }
            case 1:
                nomeColuna = "descricao"; 
                return produtoCopaDAO.Retrieve(nomeColuna, filtro);

            default:
                return produtoCopaDAO.Retrieve(null, null);
        }
    }
}