package service;

import DAO.MarcaDAO;
import java.util.List;
import model.Marca;

public class ServicoMarca {

    private MarcaDAO marcaDAO = new MarcaDAO();

    public void salvar(Marca marca) {
        if (marca.getId() == 0) {
            marcaDAO.Create(marca);
        } else {
            marcaDAO.Update(marca);
        }
    }

    public List<Marca> buscarTodos() {
        return marcaDAO.Retrieve(null, null);
    }
    
    public Marca buscarPorId(int id) {
        return marcaDAO.Retrieve(id); 
    }

    public List<Marca> buscarPorFiltro(String filtro, int tipoBusca) {
        
        if (filtro.trim().isEmpty()) {
            return marcaDAO.Retrieve(null, null); 
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
                return marcaDAO.Retrieve(null, null);
        }
        
        return marcaDAO.Retrieve(nomeColuna, filtro);
    }
    
    public void deletar(Marca marca) {
        marcaDAO.Delete(marca);
    }
}