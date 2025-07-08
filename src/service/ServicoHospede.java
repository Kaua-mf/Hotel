package service;

import java.util.ArrayList;
import java.util.List;
import model.Hospede;

public class ServicoHospede {

    private static List<Hospede> listaHospedes = new ArrayList<>();
    private static int proximoId = 1;

    static {
        Hospede hospede1 = new Hospede(proximoId++, "João da Silva", "1111-1111", "", "joao@email.com", "88000-000", "Rua A", "Centro", "Florianópolis", "", "", "111.111.111-11", "1111111", "Hotelaria SA", "11.111.111/0001-11", "", "", "", 'A');
        Hospede hospede2 = new Hospede(proximoId++, "Maria Oliveira", "2222-2222", "", "maria@email.com", "88000-100", "Rua B", "Norte", "Florianópolis", "", "", "222.222.222-22", "2222222", "Pousada Maria", "22.222.222/0001-22", "", "", "", 'A');
        Hospede hospede3 = new Hospede(proximoId++, "Carlos Pereira", "3333-3333", "", "carlos@email.com", "88000-200", "Rua C", "Sul", "São José", "", "", "333.333.333-33", "3333333", "Carlos Viagens", "33.333.333/0001-33", "", "", "", 'I');
        
        listaHospedes.add(hospede1);
        listaHospedes.add(hospede2);
        listaHospedes.add(hospede3);
    }
    
    public void salvar(Hospede hospede) {
        hospede.setId(proximoId++); 
        listaHospedes.add(hospede);
    }

    public List<Hospede> buscarTodos() {
        return listaHospedes;
    }

    public List<Hospede> buscarPorFiltro(String filtro, int tipoBusca) {
        List<Hospede> resultados = new ArrayList<>();

        if (filtro.trim().isEmpty()) {
            return listaHospedes; 
        }

        switch (tipoBusca) {
            case 0: 
                try {
                    int id = Integer.parseInt(filtro);
                    for (Hospede hospede : listaHospedes) {
                        if (hospede.getId() == id) {
                            resultados.add(hospede);
                        }
                    }
                } catch (NumberFormatException e) {
                }
                break;
            case 1:
                for (Hospede hospede : listaHospedes) {
                    if (hospede.getNome().toLowerCase().contains(filtro.toLowerCase())) {
                        resultados.add(hospede);
                    }
                }
                break;
            case 2: 
                for (Hospede hospede : listaHospedes) {
                    if (hospede.getCpf().contains(filtro)) {
                        resultados.add(hospede);
                    }
                }
                break;
        }
        return resultados;
    }
}