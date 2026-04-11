package service;

import DAO.HospedeDAO; 
import model.Hospede;
import java.util.List;

public class ServicoHospede {
    
    private HospedeDAO hospedeDAO = new HospedeDAO(); 

    public void salvar(Hospede hospede) {
        if (hospede.getId() == 0) {
            hospedeDAO.Create(hospede);
        } else {
            hospedeDAO.Update(hospede);
        }
    }
    
    public List<Hospede> buscarTodos() {
        return hospedeDAO.Retrieve(); 
    }
    
    public Hospede buscarPorId(int id) {
        return hospedeDAO.Retrieve(id); 
    }
    
    public List<Hospede> buscarPorFiltro(String filtro, int tipoBusca) {
        String nomeColuna;

        switch (tipoBusca) {
            case 0:
                nomeColuna = "id";
                break;
            case 1:
                nomeColuna = "nome"; 
                break;
            case 2:
                nomeColuna = "cpf"; 
                break;
            default:
                nomeColuna = "id";
        }
        
        return hospedeDAO.Retrieve(nomeColuna, filtro);
    }
    
    public void deletar(Hospede hospede) {
        hospedeDAO.Delete(hospede);
    }
}