package service;

import DAO.FornecedorDAO;
import model.Fornecedor;
import java.util.List;

public class ServicoFornecedor {
    
    private FornecedorDAO fornecedorDAO = new FornecedorDAO();

    public void salvar(Fornecedor fornecedor) {
        if (fornecedor.getId() == 0) {
            fornecedorDAO.Create(fornecedor);
        } else {
            fornecedorDAO.Update(fornecedor);
        }
    }
    
   
    public List<Fornecedor> buscarTodos() {
        return fornecedorDAO.Retrieve(); 
    }
    
    public Fornecedor buscarPorId(int id) {
        return fornecedorDAO.buscar(id);
    }

    public List<Fornecedor> buscarPorFiltro(String filtro, int tipoBusca) {
        String nomeColuna;

        switch (tipoBusca) {
            case 0:
                nomeColuna = "id";
                break;
            case 1:
                nomeColuna = "nome";
                break;
            case 2:
                nomeColuna = "cnpj";
                break;
            default:
                nomeColuna = "id"; // Default
        }
        
        return fornecedorDAO.Retrieve(nomeColuna, filtro);
    }

    public void deletar(Fornecedor fornecedor) {
        fornecedorDAO.Delete(fornecedor);
    }
}