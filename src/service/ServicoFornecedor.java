package service;

import DAO.FornecedorDAO;
import model.Fornecedor;
import java.util.List;

public class ServicoFornecedor {
    
    private FornecedorDAO fornecedorDAO = new FornecedorDAO();

    // MÉTODOS EXISTENTES
    public void salvar(Fornecedor fornecedor) {
        // O método Create no DAO lança RuntimeException, então deve ser propagada
        fornecedorDAO.Create(fornecedor);
    }
    
    // Supondo que este Retrieve chame um método no DAO que busca TUDO.
    // Se o seu DAO só tem Retrieve(String, String), ele deve ser adaptado.
    // Para simplificar, vamos assumir que o DAO tem um Retrieve() sem parâmetros.
    public List<Fornecedor> buscarTodos() {
        return fornecedorDAO.Retrieve(); 
    }
    
    /**
     * Busca fornecedores no banco de dados com base no filtro e no tipo de busca.
     * @param filtro O valor a ser buscado (ex: "Volkswagen").
     * @param tipoBusca O índice da ComboBox (ex: 0 para Nome, 1 para CNPJ).
     * @return Lista de Fornecedores que correspondem ao filtro.
     * @throws RuntimeException se a operação no DAO falhar.
     */
    public List<Fornecedor> buscarPorFiltro(String filtro, int tipoBusca) {
        String nomeColuna;

        // Traduz o índice da ComboBox (int) para o nome da coluna no banco (String)
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
            // Adicione outros casos conforme a necessidade da sua interface (ComboBox)
            default:
                throw new IllegalArgumentException("Tipo de busca (índice) inválido: " + tipoBusca);
        }
        
        // Chamada CORRETA: O Serviço agora chama o DAO com a assinatura Retrieve(String, String)
        return fornecedorDAO.Retrieve(nomeColuna, filtro);
    }
}