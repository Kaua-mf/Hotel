package service;

import DAO.FuncionarioDAO;
import java.util.Collections;
import java.util.List;
import model.Funcionario;

public class ServicoFuncionario {

    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public void criar(Funcionario funcionario) {
        if (funcionario == null || funcionario.getNome() == null || funcionario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do funcionário é obrigatório.");
        }
        funcionarioDAO.Create(funcionario);
    }

    public void atualizar(Funcionario funcionario) {
        if (funcionario == null || funcionario.getId() <= 0) {
            throw new IllegalArgumentException("ID do funcionário inválido para atualização.");
        }
        funcionarioDAO.Update(funcionario);
    }

    public List<Funcionario> buscarTodos() {
        return funcionarioDAO.Retrieve(null, null);
    }

    /**
     * Busca funcionários com base em um critério e filtro específicos.
     * @param filtro O valor a ser buscado (ex: "Kauã").
     * @param tipoBusca O critério de busca (0 para Nome, 1 para CPF, etc.).
     * @return Uma lista de funcionários que correspondem ao filtro.
     */
    public List<Funcionario> buscarPorFiltro(String filtro, int tipoBusca) { // <<-- AQUI ESTÁ A MUDANÇA
        if (filtro == null || filtro.trim().isEmpty()) {
            return buscarTodos();
        }

        String nomeColuna;

        // Mapeamento usando o int que vem da tela, como era originalmente
        switch (tipoBusca) {
            case 0:
                nomeColuna = "nome";
                break;
            case 1:
                nomeColuna = "cpf";
                break;
            case 2:
                nomeColuna = "usuario";
                break;
            default:
                // Se um índice inesperado for recebido, busca por nome como padrão
                nomeColuna = "nome";
                break;
        }

        return funcionarioDAO.Retrieve(nomeColuna, filtro);
    }
}