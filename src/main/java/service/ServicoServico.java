package service;

import DAO.ServicoDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    
    public List<Servico> buscarTodos() {
        return servicoDAO.Retrieve(null, null); 
    }

    public List<Servico> buscarPorFiltro(String filtro, int tipoBusca) {
        
        if (Objects.isNull(filtro) || filtro.trim().isEmpty()) {
            return servicoDAO.Retrieve(null, null);
        }

        String nomeColuna;

        switch (tipoBusca) {
            case 0: 
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
            case 1: 
                nomeColuna = "descricao"; 
                return servicoDAO.Retrieve(nomeColuna, filtro);

            default:
                return servicoDAO.Retrieve(null, null);
        }
    }
}