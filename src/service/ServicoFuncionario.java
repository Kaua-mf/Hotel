package service;

import java.util.ArrayList;
import java.util.List;
import model.Funcionario;

public class ServicoFuncionario {

    private static List<Funcionario> listaFuncionarios = new ArrayList<>();
    private static int proximoId = 1; 

    public ServicoFuncionario() {
       
    }

    public void salvar(Funcionario funcionario) {
       
        funcionario.setId(proximoId++);
        listaFuncionarios.add(funcionario);
    }

    public List<Funcionario> buscarTodos() {
        return listaFuncionarios;
    }

    public List<Funcionario> buscarPorFiltro(String filtro, int tipoBusca) {
        List<Funcionario> resultados = new ArrayList<>();

        if (filtro.trim().isEmpty()) {
            return listaFuncionarios;
        }

        switch (tipoBusca) {
            case 0: 
                try {
                    int id = Integer.parseInt(filtro);
                    for (Funcionario func : listaFuncionarios) {
                        if (func.getId() == id) {
                            resultados.add(func);
                        }
                    }
                } catch (NumberFormatException e) {
                }
                break;
            case 1: 
                for (Funcionario func : listaFuncionarios) {
                    if (func.getNome().toLowerCase().contains(filtro.toLowerCase())) {
                        resultados.add(func);
                    }
                }
                break;
            case 2: 
                for (Funcionario func : listaFuncionarios) {
                    if (func.getUsuario() != null && func.getUsuario().toLowerCase().contains(filtro.toLowerCase())) {
                        resultados.add(func);
                    }
                }
                break;
        }
        return resultados;
    }
}