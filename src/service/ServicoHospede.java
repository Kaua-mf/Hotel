package service;

import java.util.ArrayList;
import java.util.List;
import model.Hospede;

public class ServicoHospede {

    // Nossa lista que vai funcionar como um banco de dados em memória
    private static List<Hospede> listaHospedes = new ArrayList<>();
    // Variável para gerar IDs automáticos para novos hóspedes
    private static int proximoId = 1;

    // Bloco estático para popular a lista com dados de exemplo quando a classe for carregada
    static {
        // Criando e adicionando alguns hóspedes de exemplo
        Hospede hospede1 = new Hospede(proximoId++, "João da Silva", "1111-1111", "", "joao@email.com", "88000-000", "Rua A", "Centro", "Florianópolis", "", "", "111.111.111-11", "1111111", "Hotelaria SA", "11.111.111/0001-11", "", "", "", 'A');
        Hospede hospede2 = new Hospede(proximoId++, "Maria Oliveira", "2222-2222", "", "maria@email.com", "88000-100", "Rua B", "Norte", "Florianópolis", "", "", "222.222.222-22", "2222222", "Pousada Maria", "22.222.222/0001-22", "", "", "", 'A');
        Hospede hospede3 = new Hospede(proximoId++, "Carlos Pereira", "3333-3333", "", "carlos@email.com", "88000-200", "Rua C", "Sul", "São José", "", "", "333.333.333-33", "3333333", "Carlos Viagens", "33.333.333/0001-33", "", "", "", 'I');
        
        listaHospedes.add(hospede1);
        listaHospedes.add(hospede2);
        listaHospedes.add(hospede3);
    }
    
    /**
     * Adiciona um novo hóspede à lista, atribuindo um ID automático.
     * @param hospede O objeto hóspede a ser salvo.
     */
    public void salvar(Hospede hospede) {
        hospede.setId(proximoId++); // Atribui o próximo ID disponível e incrementa
        listaHospedes.add(hospede);
    }

    /**
     * Retorna todos os hóspedes da lista.
     * @return Uma lista de todos os hóspedes.
     */
    public List<Hospede> buscarTodos() {
        return listaHospedes;
    }

    /**
     * Busca hóspedes com base em um filtro (ID, Nome ou CPF).
     * @param filtro O valor a ser buscado.
     * @param tipoBusca O campo onde a busca será feita (0 para Id, 1 para Nome, 2 para CPF).
     * @return Uma lista de hóspedes que correspondem ao critério.
     */
    public List<Hospede> buscarPorFiltro(String filtro, int tipoBusca) {
        List<Hospede> resultados = new ArrayList<>();

        if (filtro.trim().isEmpty()) {
            return listaHospedes; // Retorna todos se o filtro estiver vazio
        }

        switch (tipoBusca) {
            case 0: // Filtro por ID
                try {
                    int id = Integer.parseInt(filtro);
                    for (Hospede hospede : listaHospedes) {
                        if (hospede.getId() == id) {
                            resultados.add(hospede);
                        }
                    }
                } catch (NumberFormatException e) {
                    // Se não for um número válido, a busca não retorna nada
                }
                break;
            case 1: // Filtro por Nome
                for (Hospede hospede : listaHospedes) {
                    if (hospede.getNome().toLowerCase().contains(filtro.toLowerCase())) {
                        resultados.add(hospede);
                    }
                }
                break;
            case 2: // Filtro por CPF
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