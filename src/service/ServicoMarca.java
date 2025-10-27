package service;

import DAO.MarcaDAO;
import java.util.List;
import model.Marca;

public class ServicoMarca {

    private MarcaDAO marcaDAO = new MarcaDAO();

    public void salvar(Marca marca) {
        // You can add business logic here later (e.g., don't save if name < 3 chars)
        marcaDAO.Create(marca);
    }

    public List<Marca> buscarTodos() {
        // Retrieve(null, null) should fetch all records in your DAO
        return marcaDAO.Retrieve(null, null);
    }
    
    public Marca buscarPorId(int id) {
        // Retrieve(id) should fetch by primary key in your DAO
        return marcaDAO.Retrieve(id);
    }

    public List<Marca> buscarPorFiltro(String filtro, int tipoBusca) {
        
        // If the filter text is empty, return all brands
        if (filtro.trim().isEmpty()) {
            return marcaDAO.Retrieve(null, null); 
        }

        String nomeColuna; // This variable will hold the *column name* as a String

        // Determine the column name based on the ComboBox selection index
        // Assumption: Index 0 in your JComboBox means "ID", Index 1 means "Descrição"
        switch (tipoBusca) {
            case 0: // Filter by ID
                // ***CORRECTION: Assign the COLUMN NAME as a String***
                nomeColuna = "id"; 
                break; 
            case 1: // Filter by Description
                // ***CORRECTION: Assign the COLUMN NAME as a String***
                nomeColuna = "descricao"; 
                break;
            default:
                // If the index is unexpected, return all brands
                return marcaDAO.Retrieve(null, null);
        }
        
        // Call the DAO's Retrieve method that takes the column name and filter value
        // Both nomeColuna and filtro are Strings here.
        return marcaDAO.Retrieve(nomeColuna, filtro);
    }
    
    public void atualizar(Marca marca) {
        marcaDAO.Update(marca);
    }
    
    public void deletar(Marca marca) {
        marcaDAO.Delete(marca);
    }
}