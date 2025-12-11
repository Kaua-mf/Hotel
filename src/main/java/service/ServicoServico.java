package service;

import DAO.ServicoDAO;
import java.util.List;
import model.Servico;

public class ServicoServico {

    private ServicoDAO servicoDAO = new ServicoDAO();

    public void salvar(Servico servico) {
        if (servico.getId() == 0) {
            servicoDAO.Create(servico);
        } else {
            servicoDAO.Update(servico);
        }
    }
    
    public Servico buscarPorId(int id) {
        return servicoDAO.Retrieve(id);
    }

    public List<Servico> buscarTodos() {
        return servicoDAO.Retrieve(null, null);
    }

    public List<Servico> buscarPorFiltro(String filtro, int tipoBusca) {
        
        if (filtro.trim().isEmpty()) {
            return servicoDAO.Retrieve(null, null); 
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
                return servicoDAO.Retrieve(null, null);
        }
        
        return servicoDAO.Retrieve(nomeColuna, filtro);
    }
    
    public void deletar(Servico servico) {
        servicoDAO.Delete(servico);
    }
}