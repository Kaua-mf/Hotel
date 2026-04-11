package service;

import DAO.VagaEstacionamentoDAO;
import java.util.List;
import model.VagaEstacionamento;

public class ServicoVagaEstacionamento {

    private VagaEstacionamentoDAO vagaDAO = new VagaEstacionamentoDAO();

    public void salvar(VagaEstacionamento vaga) {
        if (vaga.getId() == 0) {
            vagaDAO.Create(vaga);
        } else {
            vagaDAO.Update(vaga);
        }
    }
    
    public VagaEstacionamento buscarPorId(int id) {
        return vagaDAO.Retrieve(id);
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
            case 0: 
                nomeColuna = "id"; 
                break; 
            case 1: 
                nomeColuna = "descricao"; 
                break;
            default:
                return vagaDAO.Retrieve(null, null);
        }
        
        return vagaDAO.Retrieve(nomeColuna, filtro);
    }
    
    public void deletar(VagaEstacionamento vaga) {
        vagaDAO.Delete(vaga);
    }
}