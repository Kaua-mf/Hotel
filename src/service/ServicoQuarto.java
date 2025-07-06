package service;

import java.util.ArrayList;
import java.util.List;
import model.Quarto;
import model.TipoQuarto;

public class ServicoQuarto {

    private static List<Quarto> listaQuartos = new ArrayList<>();
    private static List<TipoQuarto> listaTiposQuarto = new ArrayList<>();
    private static int proximoId = 1;

    static {
        // Criando Tipos de Quarto de exemplo
        TipoQuarto tipo1 = new TipoQuarto(1, "Solteiro Simples", 150.0f);
        TipoQuarto tipo2 = new TipoQuarto(2, "Casal Simples", 250.0f);
        TipoQuarto tipo3 = new TipoQuarto(3, "Suíte Master", 450.0f);
        listaTiposQuarto.add(tipo1);
        listaTiposQuarto.add(tipo2);
        listaTiposQuarto.add(tipo3);

        // Criando Quartos de exemplo
        Quarto q1 = new Quarto(proximoId++, "101", 150.0f, "Primeiro andar, vista para a rua", 'A', tipo1);
        Quarto q2 = new Quarto(proximoId++, "202", 250.0f, "Segundo andar, vista para a piscina", 'A', tipo2);
        Quarto q3 = new Quarto(proximoId++, "305", 450.0f, "Cobertura, com banheira", 'I', tipo3);
        listaQuartos.add(q1);
        listaQuartos.add(q2);
        listaQuartos.add(q3);
    }
    
    public void salvar(Quarto quarto) {
        quarto.setId(proximoId++);
        listaQuartos.add(quarto);
    }

    public List<Quarto> buscarTodos() {
        return listaQuartos;
    }
    
    public List<TipoQuarto> buscarTiposQuarto() {
        return listaTiposQuarto;
    }
    
    public List<Quarto> buscarPorFiltro(String filtro, int tipoBusca) {
        List<Quarto> resultados = new ArrayList<>();
        if (filtro.trim().isEmpty()) return listaQuartos;

        switch (tipoBusca) {
            case 0: // ID
                try {
                    int id = Integer.parseInt(filtro);
                    for (Quarto q : listaQuartos) if (q.getId() == id) resultados.add(q);
                } catch (NumberFormatException e) {}
                break;
            case 1: // Descrição
                for (Quarto q : listaQuartos) if (q.getDescricao().toLowerCase().contains(filtro.toLowerCase())) resultados.add(q);
                break;
        }
        return resultados;
    }
}