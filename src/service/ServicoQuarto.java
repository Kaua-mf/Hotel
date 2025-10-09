package service;

import DAO.QuartoDAO;
import java.util.ArrayList;
import java.util.List;
import model.Quarto;
import model.TipoQuarto; // Assumimos que esta classe é a que carrega tipos de quarto

public class ServicoQuarto {

    private QuartoDAO quartoDAO = new QuartoDAO();
    
    // Mantemos a lista de Tipos de Quarto estática se estes dados não forem salvos no DB
    private static List<TipoQuarto> listaTiposQuarto = new ArrayList<>(); 
    private static int proximoId = 1; // ID de TiposQuarto

    // O bloco static é mantido APENAS para os Tipos de Quarto (se eles não tiverem um DAO próprio)
    static {
        TipoQuarto tipo1 = new TipoQuarto(proximoId++, "Solteiro Simples", 150.0f);
        TipoQuarto tipo2 = new TipoQuarto(proximoId++, "Casal Simples", 250.0f);
        TipoQuarto tipo3 = new TipoQuarto(proximoId++, "Suíte Master", 450.0f);
        listaTiposQuarto.add(tipo1);
        listaTiposQuarto.add(tipo2);
        listaTiposQuarto.add(tipo3);
        // A lista de Quartos estática foi REMOVIDA
    }
    
    public void salvar(Quarto quarto) {
        // Se o Quarto não tiver ID, é um CREATE. Caso contrário, seria um UPDATE.
        if (quarto.getId() == 0) {
            quartoDAO.Create(quarto);
        } else {
            // quartoDAO.Update(quarto); // Implementar depois
        }
    }

    public List<Quarto> buscarTodos() {
        // Delega a busca geral para o DAO
        return quartoDAO.Retrieve(null, null);
    }
    
    public List<TipoQuarto> buscarTiposQuarto() {
        // Retorna os tipos estáticos (ou buscaria de um TipoQuartoDAO)
        return listaTiposQuarto;
    }
    
    public List<Quarto> buscarPorFiltro(String filtro, int tipoBusca) {
        if (filtro.trim().isEmpty()) return quartoDAO.Retrieve(null, null);

        String nomeColuna;

        switch (tipoBusca) {
            case 0:
                nomeColuna = "id"; // Assume busca por ID
                break;
            case 1:
                nomeColuna = "descricao"; // Assume busca por Descrição
                break;
            default:
                return quartoDAO.Retrieve(null, null);
        }
        
        return quartoDAO.Retrieve(nomeColuna, filtro);
    }
}