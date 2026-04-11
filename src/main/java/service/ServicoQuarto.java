package service;

import DAO.QuartoDAO;
import java.util.List;
import model.Quarto;

public class ServicoQuarto {

    private QuartoDAO quartoDAO = new QuartoDAO();

    public void salvar(Quarto quarto) {
        if (quarto.getId() == 0) {
            quartoDAO.Create(quarto);
        } else {
            quartoDAO.Update(quarto);
        }
    }
    
    public Quarto buscarPorId(int id) {
        return quartoDAO.Retrieve(id);
    }

    public List<Quarto> buscarTodos() {
        return quartoDAO.Retrieve(null, null);
    }

    public List<Quarto> buscarPorFiltro(String atributo, String valor) {
        return quartoDAO.Retrieve(atributo, valor);
    }
    
    public void deletar(Quarto quarto) {
        quartoDAO.Delete(quarto);
    }
}