package service;

import DAO.VeiculoDAO;
import java.util.List;
import model.Veiculo;

public class ServicoVeiculo {

    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    // MÉTODOS DE DADOS REMOVIDOS (Static List)

    public void salvar(Veiculo veiculo) {
        // A lógica de validação do Service viria aqui.
        
        // Delega a persistência para o DAO (assume-se que o Service decide entre Create/Update)
        veiculoDAO.Create(veiculo); 
    }

    // --- CORRIGIDO: Deve chamar o DAO de forma que traga todos os registros ---
    public List<Veiculo> buscarTodos() {
        // Chamada padronizada: se o filtro e o valor são null, o DAO deve buscar todos.
        return veiculoDAO.Retrieve(null, null); 
    }
    
    public List<Veiculo> buscarPorFiltro(String filtro, int tipoBusca) {
        
        // 1. Caso de Filtro Vazio: Retorna todos
        if (filtro.trim().isEmpty()) {
            return veiculoDAO.Retrieve(null, null); 
        }

        String nomeColuna;

        // 2. Mapeamento da Coluna (Continua o mesmo)
        switch (tipoBusca) {
            case 0:
                nomeColuna = "placa";
                break;
            case 1:
                nomeColuna = "cor";
                break;
            default:
                // Se o índice for inválido, retornamos todos os registros ou lançamos erro
                return veiculoDAO.Retrieve(null, null);
        }
        
        // 3. Executa a Busca Filtrada no DAO
        return veiculoDAO.Retrieve(nomeColuna, filtro);
    }
}