package service;

import java.util.ArrayList;
import java.util.List;
import model.VagaEstacionamento;

public class ServicoVagaEstacionamento {

    private static List<VagaEstacionamento> listaVagas = new ArrayList<>();
    private static int proximoId = 1;

    static {
        // Adicionando algumas vagas de exemplo
        listaVagas.add(new VagaEstacionamento(proximoId++, "Vaga 01 - Coberta", "Próxima ao elevador", 12.5f, 'A'));
        listaVagas.add(new VagaEstacionamento(proximoId++, "Vaga 02 - Coberta", "Padrão", 12.0f, 'A'));
        listaVagas.add(new VagaEstacionamento(proximoId++, "Vaga 15 - Descoberta", "Fundos do estacionamento", 15.0f, 'I'));
    }

    public void salvar(VagaEstacionamento vaga) {
        vaga.setId(proximoId++);
        listaVagas.add(vaga);
    }

    public List<VagaEstacionamento> buscarTodos() {
        return listaVagas;
    }

    public List<VagaEstacionamento> buscarPorFiltro(String filtro, int tipoBusca) {
        List<VagaEstacionamento> resultados = new ArrayList<>();
        if (filtro.trim().isEmpty()) {
            return listaVagas;
        }

        switch (tipoBusca) {
            case 0: // ID
                try {
                    int id = Integer.parseInt(filtro);
                    for (VagaEstacionamento v : listaVagas) if (v.getId() == id) resultados.add(v);
                } catch (NumberFormatException e) {}
                break;
            case 1: // Descrição
                for (VagaEstacionamento v : listaVagas) if (v.getDescricao().toLowerCase().contains(filtro.toLowerCase())) resultados.add(v);
                break;
        }
        return resultados;
    }
}