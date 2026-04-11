package service;

import DAO.FuncionarioDAO; 
import model.Funcionario;
import java.util.List;

public class ServicoFuncionario {
    
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public void salvar(Funcionario funcionario) {
        if (funcionario.getId() == 0) {
            funcionarioDAO.Create(funcionario);
        } else {
            funcionarioDAO.Update(funcionario);
        }
    }
  
    public List<Funcionario> buscarTodos() {
        return funcionarioDAO.Retrieve(); 
    }
    
    public Funcionario buscarPorId(int id) {
        return funcionarioDAO.Retrieve(id); 
    }
    
    public List<Funcionario> buscarPorFiltro(String filtro, int tipoBusca) {
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
        
        return funcionarioDAO.Retrieve(nomeColuna, filtro);
    }
    
    public void deletar(Funcionario funcionario) {
        funcionarioDAO.Delete(funcionario);
    }
}