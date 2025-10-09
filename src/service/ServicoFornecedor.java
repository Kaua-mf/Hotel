package service;

import DAO.FornecedorDAO;
import model.Fornecedor;
import java.util.List;

public class ServicoFornecedor {
    
    private FornecedorDAO fornecedorDAO = new FornecedorDAO();

    public void salvar(Fornecedor fornecedor) {
        fornecedorDAO.Create(fornecedor);
    }
    
    public List<Fornecedor> buscarTodos() {
        return fornecedorDAO.Retrieve(); 
    }
    
    public List<Fornecedor> buscarPorFiltro(String filtro, int tipoBusca) {
        String nomeColuna;

        switch (tipoBusca) {
            case 0:
                nomeColuna = "nome";
                break;
            case 1:
                nomeColuna = "razao_social";
                break;
            case 2:
                nomeColuna = "cnpj";
                break;
            default:
                throw new IllegalArgumentException("Tipo de busca (índice) inválido: " + tipoBusca);
        }
        
        return fornecedorDAO.Retrieve(nomeColuna, filtro);
    }
}