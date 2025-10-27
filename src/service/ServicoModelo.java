package service;

import DAO.ModeloDAO;
import java.util.ArrayList; // Import ArrayList
import java.util.List;
import model.Modelo;

public class ServicoModelo {

    private ModeloDAO modeloDAO = new ModeloDAO();

    public void salvar(Modelo modelo) {
        // Validações podem ser adicionadas aqui antes de chamar o DAO
        modeloDAO.Create(modelo);
    }

    public List<Modelo> buscarTodos() {
        // Chama o Retrieve do DAO que busca tudo (sem filtro)
        return modeloDAO.Retrieve(null, null);
    }
    
    public Modelo buscarPorId(int id) {
        // Chama o Retrieve do DAO que busca por ID
        return modeloDAO.Retrieve(id);
    }

    /**
     * Busca Modelos aplicando um filtro baseado no texto e no tipo de busca selecionado.
     * @param filtro O texto a ser buscado.
     * @param tipoBusca O índice do ComboBox indicando o campo a ser filtrado 
     * (Ex: 0=ID, 1=Nome Modelo, 2=Nome Marca).
     * @return Uma lista de Modelos encontrados.
     */
    public List<Modelo> buscarPorFiltro(String filtro, int tipoBusca) {
        
        // Se o filtro estiver vazio, retorna todos os modelos
        if (filtro == null || filtro.trim().isEmpty()) {
            return modeloDAO.Retrieve(null, null); 
        }

        List<Modelo> listaModelos = new ArrayList<>(); // Lista para os resultados

        // Assume: tipoBusca 0 = ID, tipoBusca 1 = Nome Modelo, tipoBusca 2 = Nome Marca
        if (tipoBusca == 0) { // Filtrar por ID do Modelo
            try {
                int id = Integer.parseInt(filtro); // Tenta converter o filtro para número
                Modelo modelo = modeloDAO.Retrieve(id); // Chama o Retrieve(int id) do DAO
                if (modelo != null) {
                    listaModelos.add(modelo); // Adiciona o resultado único (se encontrado)
                }
                // Se não encontrar (modelo == null), a lista fica vazia.
            } catch (NumberFormatException e) {
                // Se o texto digitado não for um número válido para ID
                System.err.println("Erro ao filtrar Modelo por ID: O valor '" + filtro + "' não é um número válido.");
                // Retorna lista vazia se o ID for inválido
                return listaModelos; 
            } catch (Exception e) {
                 // Captura outros erros do DAO
                e.printStackTrace();
                throw new RuntimeException("Erro ao buscar modelo por ID no serviço: " + e.getMessage(), e);
            }
        } else { // Filtrar por Nome do Modelo ou Nome da Marca (texto)
            String nomeColuna;
            
            if (tipoBusca == 1) { // Filtrar por Nome do Modelo
                // Usa o nome da coluna 'nome' com alias da tabela 'modelo' (mod)
                // como definido no SQL do ModeloDAO.Retrieve(atributo, valor)
                nomeColuna = "mod.nome"; 
            } else if (tipoBusca == 2) { // Filtrar por Nome da Marca
                // Usa o nome da coluna 'descricao' com alias da tabela 'marca' (mar)
                // como definido no SQL do ModeloDAO.Retrieve(atributo, valor)
                nomeColuna = "mar.descricao"; 
            } else {
                // Tipo de busca desconhecido, retorna todos
                return modeloDAO.Retrieve(null, null);
            }
            
            try {
                 // Chama o Retrieve(atributo, valor) do DAO que usa LIKE
                listaModelos = modeloDAO.Retrieve(nomeColuna, filtro);
            } catch (Exception e) {
                 e.printStackTrace();
                throw new RuntimeException("Erro ao buscar modelos por " + nomeColuna + " no serviço: " + e.getMessage(), e);
            }
        }
        
        return listaModelos; // Retorna a lista (pode estar vazia)
    }
    
    public void atualizar(Modelo modelo) {
        // Validações podem ser adicionadas aqui
        modeloDAO.Update(modelo);
    }
    
    public void deletar(Modelo modelo) {
        // Validações/Regras de negócio (ex: verificar se modelo está em uso) podem ser adicionadas
        modeloDAO.Delete(modelo);
    }
}