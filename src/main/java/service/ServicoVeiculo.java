package service;

import DAO.VeiculoDAO;
import java.util.List;
import model.Veiculo;

public class ServicoVeiculo {

    private VeiculoDAO veiculoDAO = new VeiculoDAO();
    private ServicoModelo servicoModelo = new ServicoModelo(); // Necessário para buscar o objeto Modelo

    public void salvar(Veiculo veiculo) {
        if (veiculo.getId() == 0) {
            veiculoDAO.Create(veiculo);
        } else {
            veiculoDAO.Update(veiculo);
        }
    }
    
    public Veiculo buscarPorId(int id) {
    Veiculo veiculo = veiculoDAO.Retrieve(id);
    
    if (veiculo != null && veiculo.getModelo() != null && veiculo.getModelo().getId() != 0) {
        veiculo.setModelo(servicoModelo.buscarPorId(veiculo.getModelo().getId()));
    }
    
    return veiculo;
}

    public List<Veiculo> buscarTodos() {
        return veiculoDAO.Retrieve(null, null);
    }

    public List<Veiculo> buscarPorFiltro(String filtro, int tipoBusca) {
        
        if (filtro.trim().isEmpty()) {
            return veiculoDAO.Retrieve(null, null); 
        }

        String nomeColuna; 

        // Mapeamento dos índices da busca (Assumindo 0=ID, 1=Placa, 2=Cor)
        switch (tipoBusca) {
            case 0: 
                nomeColuna = "id"; 
                break; 
            case 1: 
                nomeColuna = "placa"; 
                break;
            case 2: 
                nomeColuna = "cor"; 
                break;
            default:
                return veiculoDAO.Retrieve(null, null);
        }
        
        return veiculoDAO.Retrieve(nomeColuna, filtro);
    }
    
    public void deletar(Veiculo veiculo) {
        veiculoDAO.Delete(veiculo);
    }
}