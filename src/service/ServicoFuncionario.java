package service;

import java.util.ArrayList;
import java.util.List;
import model.Funcionario;

public class ServicoFuncionario {

    private static List<Funcionario> listaFuncionarios = new ArrayList<>();
    private static int proximoId = 1; // Para gerar IDs automáticos

    public ServicoFuncionario() {
        // Construtor vazio
    }

    public void salvar(Funcionario funcionario) {
        // Atribui um novo ID e adiciona na lista
        funcionario.setId(proximoId++);
        listaFuncionarios.add(funcionario);
    }

    public List<Funcionario> buscarTodos() {
        return listaFuncionarios;
    }

    public List<Funcionario> buscarPorFiltro(String filtro, int tipoBusca) {
        List<Funcionario> resultados = new ArrayList<>();

        // Se o filtro estiver vazio, retorna todos os funcionários
        if (filtro.trim().isEmpty()) {
            return listaFuncionarios;
        }

        switch (tipoBusca) {
            case 0: // ID
                try {
                    int id = Integer.parseInt(filtro);
                    for (Funcionario func : listaFuncionarios) {
                        if (func.getId() == id) {
                            resultados.add(func);
                        }
                    }
                } catch (NumberFormatException e) {
                    // Ignora se o filtro não for um número válido para ID
                }
                break;
            case 1: // Nome
                for (Funcionario func : listaFuncionarios) {
                    if (func.getNome().toLowerCase().contains(filtro.toLowerCase())) {
                        resultados.add(func);
                    }
                }
                break;
            case 2: // Usuário (conforme ajuste do Passo 1)
                for (Funcionario func : listaFuncionarios) {
                    // Evita erro se o usuário for nulo
                    if (func.getUsuario() != null && func.getUsuario().toLowerCase().contains(filtro.toLowerCase())) {
                        resultados.add(func);
                    }
                }
                break;
        }
        return resultados;
    }
}