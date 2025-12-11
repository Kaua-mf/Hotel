package service;

import DAO.ProdutoCopaDAO;
import java.util.List;
import model.ProdutoCopa;

public class ServicoProdutoCopa {

    private ProdutoCopaDAO produtoCopaDAO = new ProdutoCopaDAO();

    public void salvar(ProdutoCopa produto) {
        if (produto.getId() == 0) {
            produtoCopaDAO.Create(produto);
        } else {
            produtoCopaDAO.Update(produto);
        }
    }

    public List<ProdutoCopa> buscarTodos() {
        return produtoCopaDAO.Retrieve(null, null);
    }
    
    public ProdutoCopa buscarPorId(int id) {
        return produtoCopaDAO.Retrieve(id);
    }

    public List<ProdutoCopa> buscarPorFiltro(String filtro, int tipoBusca) {
        
        if (filtro.trim().isEmpty()) {
            return produtoCopaDAO.Retrieve(null, null); 
        }

        String nomeColuna; 

        switch (tipoBusca) {
            case 0: 
                nomeColuna = "id"; 
                break; 
            case 1: 
                nomeColuna = "descricao"; 
                break;
            default:
                return produtoCopaDAO.Retrieve(null, null);
        }
        
        return produtoCopaDAO.Retrieve(nomeColuna, filtro);
    }
    
    public void deletar(ProdutoCopa produto) {
        produtoCopaDAO.Delete(produto);
    }
}