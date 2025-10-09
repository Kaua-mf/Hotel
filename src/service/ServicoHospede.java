package service;

import DAO.HospedeDAO;
import java.util.List;
import model.Hospede;
import java.util.Objects;

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
        return hospedeDAO.Retrieve(null, null);
    }

    public List<Hospede> buscarPorFiltro(String filtro, int tipoBusca) {
        if (Objects.isNull(filtro) || filtro.trim().isEmpty()) {
            return hospedeDAO.Retrieve(null, null);
        }

        String nomeColuna;

        switch (tipoBusca) {
            case 0: 
                nomeColuna = "nome";
                break;
            case 1: 
                nomeColuna = "cpf";
                break;
            case 2: 
                nomeColuna = "rg";
                break;
            default:
                nomeColuna = "nome";
                break;
        }
        
        return hospedeDAO.Retrieve(nomeColuna, filtro);
    }
}