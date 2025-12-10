package service;

import DAO.QuartoDAO;
import java.util.ArrayList;
import java.util.List;
import model.Quarto;
import model.TipoQuarto; 

public class ServicoQuarto {

    private QuartoDAO quartoDAO = new QuartoDAO();
    
    private static List<TipoQuarto> listaTiposQuarto = new ArrayList<>(); 
    private static int proximoId = 1; 

    static {
        TipoQuarto tipo1 = new TipoQuarto(proximoId++, "Solteiro Simples", 150.0f);
        TipoQuarto tipo2 = new TipoQuarto(proximoId++, "Casal Simples", 250.0f);
        TipoQuarto tipo3 = new TipoQuarto(proximoId++, "Suíte Master", 450.0f);
        listaTiposQuarto.add(tipo1);
        listaTiposQuarto.add(tipo2);
        listaTiposQuarto.add(tipo3);
    }
    
    public void salvar(Quarto quarto) {
        if (quarto.getId() == 0) {
            quartoDAO.Create(quarto);
        } else {
        }
    }

    public List<Quarto> buscarTodos() {
        return quartoDAO.Retrieve(null, null);
    }
    
    public List<TipoQuarto> buscarTiposQuarto() {
        return listaTiposQuarto;
    }
    
    public List<Quarto> buscarPorFiltro(String filtro, int tipoBusca) {
        if (filtro.trim().isEmpty()) return quartoDAO.Retrieve(null, null);

        String nomeColuna;

        switch (tipoBusca) {
            case 0:
                nomeColuna = "id";
                break;
            case 1:
                nomeColuna = "descricao"; 
                break;
            default:
                nomeColuna = "id";
        }
        
        return quartoDAO.Retrieve(nomeColuna, filtro);
    }
}