package service;

import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;

public class ServicoFornecedor {

    private static List<Fornecedor> listaFornecedores = new ArrayList<>();
    private static int proximoId = 1;

    static {
        Fornecedor f1 = new Fornecedor(proximoId++, "Distribuidora de Bebidas A&B", "2222-3333", "", "contato@ab.com", "88123-000", "Rua das Bebidas", "Industrial", "São José", "", "", "11.111.111/0001-11", "", "A&B Bebidas", "11.111.111/0001-11", "ISENTO", "Carlos", "Fornecedor de bebidas", 'A');
        Fornecedor f2 = new Fornecedor(proximoId++, "Limpeza & Cia", "4444-5555", "", "vendas@limpeza.com", "88456-000", "Av. Central", "Comerciários", "Palhoça", "", "", "22.222.222/0001-22", "", "Limpeza Total Ltda", "22.222.222/0001-22", "123.456.789", "Mariana", "Produtos de limpeza", 'A');
        listaFornecedores.add(f1);
        listaFornecedores.add(f2);
    }
    
    public void salvar(Fornecedor fornecedor) {
        fornecedor.setId(proximoId++);
        listaFornecedores.add(fornecedor);
    }

    public List<Fornecedor> buscarTodos() {
        return listaFornecedores;
    }
    
    public List<Fornecedor> buscarPorFiltro(String filtro, int tipoBusca) {
        List<Fornecedor> resultados = new ArrayList<>();
        if (filtro.trim().isEmpty()) return listaFornecedores;

        switch (tipoBusca) {
            case 0: 
                try {
                    int id = Integer.parseInt(filtro);
                    for (Fornecedor f : listaFornecedores) if (f.getId() == id) resultados.add(f);
                } catch (NumberFormatException e) {}
                break;
            case 1: 
                for (Fornecedor f : listaFornecedores) if (f.getNome().toLowerCase().contains(filtro.toLowerCase())) resultados.add(f);
                break;
            case 2:
                for (Fornecedor f : listaFornecedores) if (f.getCnpj().contains(filtro) || f.getCpf().contains(filtro)) resultados.add(f);
                break;
        }
        return resultados;
    }
}