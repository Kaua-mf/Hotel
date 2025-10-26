package service;

import DAO.ModeloDAO;
import java.util.List;
import model.Modelo;

public class ServicoModelo {

    private ModeloDAO modeloDAO = new ModeloDAO();

    public void salvar(Modelo modelo) {
        modeloDAO.Create(modelo);
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
                nomeColuna = "modelo.descricao"; // Usamos o alias para desambiguação
                break;
            case 1:
                nomeColuna = "marca.descricao";
                break;
            default:
                return modeloDAO.Retrieve(null, null);
        }
        
        return modeloDAO.Retrieve(nomeColuna, filtro);
    }
    
    public void atualizar(Modelo modelo) {
        modeloDAO.Update(modelo);
    }
    
    public void deletar(Modelo modelo) {
        modeloDAO.Delete(modelo);
    }
}