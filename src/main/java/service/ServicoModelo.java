package service;

import DAO.ModeloDAO;
import java.util.ArrayList;
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
        
        if (filtro == null || filtro.trim().isEmpty()) {
            return modeloDAO.Retrieve(null, null); 
        }

        List<Modelo> listaModelos = new ArrayList<>(); 

        if (tipoBusca == 0) { 
            try {
                int id = Integer.parseInt(filtro); 
                Modelo modelo = modeloDAO.Retrieve(id); 
                if (modelo != null) {
                    listaModelos.add(modelo); 
                }
            } catch (NumberFormatException e) {
                System.err.println("Erro ao filtrar Modelo por ID: O valor '" + filtro + "' não é um número válido.");
                return listaModelos; 
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao buscar modelo por ID no serviço: " + e.getMessage(), e);
            }
        } else {
            String nomeColuna;
            
            if (tipoBusca == 1) { 
                nomeColuna = "mod.nome"; 
            } else if (tipoBusca == 2) {
                nomeColuna = "mar.descricao"; 
            } else {
                return modeloDAO.Retrieve(null, null);
            }
            
            try {
                listaModelos = modeloDAO.Retrieve(nomeColuna, filtro);
            } catch (Exception e) {
                 e.printStackTrace();
                throw new RuntimeException("Erro ao buscar modelos por " + nomeColuna + " no serviço: " + e.getMessage(), e);
            }
        }
        
        return listaModelos; 
    }
    
    public void atualizar(Modelo modelo) {
        modeloDAO.Update(modelo);
    }
    
    public void deletar(Modelo modelo) {
        modeloDAO.Delete(modelo);
    }
}