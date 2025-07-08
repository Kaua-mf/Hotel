package service;

import java.util.ArrayList;
import java.util.List;
import model.Servico;

public class ServicoServico {

    private static List<Servico> listaServicos = new ArrayList<>();
    private static int proximoId = 1;

    static {
        listaServicos.add(new Servico(proximoId++, "Lavanderia - Peça de Roupa", "Lavagem e secagem por peça", 'A'));
        listaServicos.add(new Servico(proximoId++, "Serviço de Quarto - Frigobar", "Reposição de itens do frigobar", 'A'));
        listaServicos.add(new Servico(proximoId++, "Massagem Relaxante", "Sessão de 1 hora com massoterapeuta", 'I'));
    }

    public void salvar(Servico servico) {
        servico.setId(proximoId++);
        listaServicos.add(servico);
    }

    public List<Servico> buscarTodos() {
        return listaServicos;
    }

    public List<Servico> buscarPorFiltro(String filtro, int tipoBusca) {
        List<Servico> resultados = new ArrayList<>();
        if (filtro.trim().isEmpty()) {
            return listaServicos;
        }

        switch (tipoBusca) {
            case 0: 
                try {
                    int id = Integer.parseInt(filtro);
                    for (Servico s : listaServicos) {
                        if (s.getId() == id) {
                            resultados.add(s);
                        }
                    }
                } catch (NumberFormatException e) {
                }
                break;
            case 1: 
                for (Servico s : listaServicos) {
                    if (s.getDescricao().toLowerCase().contains(filtro.toLowerCase())) {
                        resultados.add(s);
                    }
                }
                break;
        }
        return resultados;
    }
}