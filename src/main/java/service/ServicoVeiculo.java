package service;

import DAO.VeiculoDAO;
import java.util.List;
import model.Veiculo;

public class ServicoVeiculo {

    private VeiculoDAO veiculoDAO = new VeiculoDAO();


    public void salvar(Veiculo veiculo) {
        veiculoDAO.Create(veiculo); 
    }

    public List<Veiculo> buscarTodos() {
        return veiculoDAO.Retrieve(null, null); 
    }
    
    public List<Veiculo> buscarPorFiltro(String filtro, int tipoBusca) {
        
        if (filtro.trim().isEmpty()) {
            return veiculoDAO.Retrieve(null, null); 
        }

        String nomeColuna;

        switch (tipoBusca) {
            case 0:
                nomeColuna = "placa";
                break;
            case 1:
                nomeColuna = "cor";
                break;
            default:
                return veiculoDAO.Retrieve(null, null);
        }
        
        return veiculoDAO.Retrieve(nomeColuna, filtro);
    }
}