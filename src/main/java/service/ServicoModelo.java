package service;

import DAO.ModeloDAO;
import java.util.List;
import model.Modelo;

public class ServicoModelo {

    private ModeloDAO modeloDAO = new ModeloDAO();

    public void salvar(Modelo modelo) {
        if (modelo.getId() == 0) {
            modeloDAO.Create(modelo);
        } else {
            modeloDAO.Update(modelo);
        }
    }

    public List<Modelo> buscarTodos() {
        return modeloDAO.Retrieve(null, null);
    }
    
    public Modelo buscarPorId(int id) {
        return modeloDAO.Retrieve(id);
    }

    public List<Modelo> buscarPorFiltro(String filtro, int tipoBusca) {
        
        if (filtro.trim().isEmpty()) {
            return modeloDAO.Retrieve(null, null); 
        }

        String nomeColuna; 

        switch (tipoBusca) {
            case 0: 
                nomeColuna = "id"; 
                break; 
            case 1: 
                nomeColuna = "nome"; 
                break;
            case 2:
                nomeColuna = "marca_id"; 
                break;
            default:
                return modeloDAO.Retrieve(null, null);
        }
        
        return modeloDAO.Retrieve(nomeColuna, filtro);
    }
    
    
    public void deletar(Modelo modelo) {
        modeloDAO.Delete(modelo);
    }
}