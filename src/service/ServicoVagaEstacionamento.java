package service;

import DAO.VagaEstacionamentoDAO;
import java.util.ArrayList;
import java.util.List;
import model.VagaEstacionamento;

public class ServicoVagaEstacionamento {

    private VagaEstacionamentoDAO vagaDAO = new VagaEstacionamentoDAO();

    // REMOVIDA A LÓGICA ESTÁTICA (listaVagas, proximoId, static { ... })

    public void salvar(VagaEstacionamento vaga) {
        // Lógica: se ID for 0, cria. Senão, atualiza.
        if (vaga.getId() == 0) {
            vagaDAO.Create(vaga);
        } else {
            vagaDAO.Update(vaga);
        }
    }

    public List<VagaEstacionamento> buscarTodos() {
        return vagaDAO.Retrieve(null, null);
    }

    public List<VagaEstacionamento> buscarPorFiltro(String filtro, int tipoBusca) {
        if (filtro.trim().isEmpty()) {
            return vagaDAO.Retrieve(null, null);
        }

        String nomeColuna;

        switch (tipoBusca) {
            case 0: // ID
                try {
                    int id = Integer.parseInt(filtro);
                    VagaEstacionamento vaga = vagaDAO.Retrieve(id);
                    List<VagaEstacionamento> resultados = new ArrayList<>();
                    if (vaga != null) resultados.add(vaga);
                    return resultados;
                } catch (NumberFormatException e) {
                    return new ArrayList<>();
                }
            case 1: // Descrição
                nomeColuna = "descricao";
                break;
            case 2: // Metragem
                nomeColuna = "metragem_vaga";
                break;
            default:
                return vagaDAO.Retrieve(null, null);
        }
        
        return vagaDAO.Retrieve(nomeColuna, filtro);
    }
}