package service;

// A classe ServicoDAO (DAI) deve estar na pasta DAO
import DAO.ServicoDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Servico; // Classe de Modelo (POJO)

// Esta classe agora atua como a Camada de Serviço para a entidade Servico
public class ServicoServico { 

    // O Service precisa de uma instância do DAO
    private ServicoDAO servicoDAO = new ServicoDAO();

    // Removidas a lista estática, o proximoId e o bloco static { ... }
    // O banco de dados fará o controle de ID e armazenamento.

    /**
     * Salva ou atualiza um serviço no banco de dados.
     * @param servico O objeto Servico a ser persistido.
     */
    public void salvar(Servico servico) {
        // Lógica do Service: Decide entre CREATE (salvar novo) e UPDATE (atualizar existente)
        if (servico.getId() == 0) {
            servicoDAO.Create(servico);
        } else {
            servicoDAO.Update(servico);
        }
    }

    /**
     * Busca todos os serviços registrados no banco.
     * @return Lista de todos os objetos Servico.
     */
    public List<Servico> buscarTodos() {
        // Delega a busca geral para o DAO
        return servicoDAO.Retrieve(null, null); // Assume que Retrieve(null, null) busca todos
    }

    /**
     * Busca serviços com base no filtro (ID ou Descrição/Nome)
     * @param filtro O valor a ser buscado.
     * @param tipoBusca Índice da ComboBox (0: ID, 1: Descrição/Nome).
     * @return Lista de Servicos que correspondem ao filtro.
     */
    public List<Servico> buscarPorFiltro(String filtro, int tipoBusca) {
        
        if (Objects.isNull(filtro) || filtro.trim().isEmpty()) {
            return servicoDAO.Retrieve(null, null);
        }

        String nomeColuna;

        switch (tipoBusca) {
            case 0: // Buscar por ID
                try {
                    int id = Integer.parseInt(filtro);
                    Servico s = servicoDAO.Retrieve(id); 
                    List<Servico> resultados = new ArrayList<>();
                    if (s != null) {
                        resultados.add(s);
                    }
                    return resultados;
                } catch (NumberFormatException e) {
                    return new ArrayList<>(); 
                }
            case 1: // Buscar por Descrição/Nome
                // O DAO precisa saber o nome da coluna de texto. Usamos 'descricao'.
                nomeColuna = "descricao"; 
                return servicoDAO.Retrieve(nomeColuna, filtro);

            default:
                return servicoDAO.Retrieve(null, null);
        }
    }
}