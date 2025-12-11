package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;

public class FornecedorDAO implements InterfaceDAO<Fornecedor> {

    @Override
    public void Create(Fornecedor objeto) {
        String sqlInstrucao = "INSERT INTO fornecedor(nome, fone, fone2, email, cep, logradouro, bairro, cidade, complemento, data_cadastro, cpf, rg, obs, status, razao_social, cnpj, inscricao_estadual, contato) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 

            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setString(1, objeto.getNome());
            pstm.setString(2, objeto.getFone()); // Verifique se no Model é getFone ou getFone1
            pstm.setString(3, objeto.getFone2());
            pstm.setString(4, objeto.getEmail());
            pstm.setString(5, objeto.getCep());
            pstm.setString(6, objeto.getLogradouro());
            pstm.setString(7, objeto.getBairro());
            pstm.setString(8, objeto.getCidade());
            pstm.setString(9, objeto.getComplemento());
            pstm.setString(10, objeto.getDataCadastro()); 
            pstm.setString(11, objeto.getCpf());
            pstm.setString(12, objeto.getRg());
            pstm.setString(13, objeto.getObs());
            pstm.setString(14, String.valueOf(objeto.getStatus()));
            pstm.setString(15, objeto.getRazaoSocial());
            pstm.setString(16, objeto.getCnpj());
            pstm.setString(17, objeto.getInscricaoEstadual());
            pstm.setString(18, objeto.getContato());
            
            pstm.executeUpdate(); 
            conexao.commit(); 
            
        } catch (SQLException ex) {
            try {
                if (conexao != null) conexao.rollback(); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    /**
     * Este método é exigido pela InterfaceDAO
     */
    @Override
    public Fornecedor Retrieve(int id) {
        String sqlInstrucao = "SELECT * FROM fornecedor WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Fornecedor fornecedor = null; 

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            
            if (rst.next()) {
                fornecedor = new Fornecedor();
                fornecedor.setId(rst.getInt("id"));
                fornecedor.setNome(rst.getString("nome"));
                fornecedor.setFone(rst.getString("fone"));
                fornecedor.setFone2(rst.getString("fone2"));
                fornecedor.setEmail(rst.getString("email"));
                fornecedor.setCep(rst.getString("cep"));
                fornecedor.setLogradouro(rst.getString("logradouro"));
                fornecedor.setBairro(rst.getString("bairro"));
                fornecedor.setCidade(rst.getString("cidade"));
                fornecedor.setComplemento(rst.getString("complemento"));
                fornecedor.setDataCadastro(rst.getString("data_cadastro"));
                fornecedor.setCpf(rst.getString("cpf"));
                fornecedor.setRg(rst.getString("rg"));
                fornecedor.setObs(rst.getString("obs"));
                
                String statusString = rst.getString("status");
                if (statusString != null && !statusString.isEmpty()) {
                    fornecedor.setStatus(statusString.charAt(0));
                }
                
                fornecedor.setRazaoSocial(rst.getString("razao_social"));
                fornecedor.setCnpj(rst.getString("cnpj"));
                fornecedor.setInscricaoEstadual(rst.getString("inscricao_estadual"));
                fornecedor.setContato(rst.getString("contato"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return fornecedor; 
    }
    
    /**
     * --- NOVO MÉTODO ---
     * Criado para atender a chamada do ServicoFornecedor.buscarPorId()
     * Ele apenas reaproveita o Retrieve(id).
     */
    public Fornecedor buscar(int id) {
        return Retrieve(id);
    }
    
    @Override
    public List<Fornecedor> Retrieve(String atributo, String valor) {
        String sqlInstrucao;
        
        // Se atributo ou valor forem nulos/vazios, busca TUDO
        if (atributo == null || atributo.trim().isEmpty() || valor == null || valor.trim().isEmpty()) {
            sqlInstrucao = "SELECT * FROM fornecedor";
        } else {
            // CUIDADO: Concatenação direta pode gerar SQL Injection. 
            // Para estudo/trabalho escolar ok, para produção use validação dos campos.
            sqlInstrucao = "SELECT * FROM fornecedor WHERE " + atributo + " LIKE ?";
        }
        
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Fornecedor> listaFornecedor = new ArrayList<>();

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            // Só define o parâmetro se estivermos filtrando
            if (atributo != null && !atributo.trim().isEmpty() && valor != null) {
                pstm.setString(1, "%" + valor + "%");
            }
            
            rst = pstm.executeQuery();
            
            while (rst.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rst.getInt("id"));
                fornecedor.setNome(rst.getString("nome"));
                fornecedor.setFone(rst.getString("fone"));
                fornecedor.setFone2(rst.getString("fone2"));
                fornecedor.setEmail(rst.getString("email"));
                fornecedor.setCep(rst.getString("cep"));
                fornecedor.setLogradouro(rst.getString("logradouro"));
                fornecedor.setBairro(rst.getString("bairro"));
                fornecedor.setCidade(rst.getString("cidade"));
                fornecedor.setComplemento(rst.getString("complemento"));
                fornecedor.setDataCadastro(rst.getString("data_cadastro"));
                fornecedor.setCpf(rst.getString("cpf"));
                fornecedor.setRg(rst.getString("rg"));
                fornecedor.setObs(rst.getString("obs"));
                
                String statusString = rst.getString("status");
                if (statusString != null && !statusString.isEmpty()) {
                    fornecedor.setStatus(statusString.charAt(0));
                }
                
                fornecedor.setRazaoSocial(rst.getString("razao_social"));
                fornecedor.setCnpj(rst.getString("cnpj"));
                fornecedor.setInscricaoEstadual(rst.getString("inscricao_estadual")); 
                fornecedor.setContato(rst.getString("contato"));
                
                listaFornecedor.add(fornecedor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return listaFornecedor;
    }

    /**
     * Agora este método funciona corretamente chamando o Retrieve com null.
     * A lógica acima (Retrieve string, string) trata o null para buscar todos.
     */
    public List<Fornecedor> Retrieve() {
        return Retrieve(null, null); 
    }
    
    @Override
    public void Update(Fornecedor objeto) {
        String sqlInstrucao = "UPDATE fornecedor SET nome = ?, fone = ?, fone2 = ?, email = ?, cep = ?, logradouro = ?, bairro = ?, cidade = ?, complemento = ?, cpf = ?, rg = ?, obs = ?, status = ?, razao_social = ?, cnpj = ?, inscricao_estadual = ?, contato = ? WHERE id = ?";
        // OBS: Removi data_cadastro do UPDATE. Geralmente não se altera a data de criação.
        
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false);

            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setString(1, objeto.getNome());
            pstm.setString(2, objeto.getFone());
            pstm.setString(3, objeto.getFone2());
            pstm.setString(4, objeto.getEmail());
            pstm.setString(5, objeto.getCep());
            pstm.setString(6, objeto.getLogradouro());
            pstm.setString(7, objeto.getBairro());
            pstm.setString(8, objeto.getCidade());
            pstm.setString(9, objeto.getComplemento());
            // pstm.setString(10, objeto.getDataCadastro()); // Removido
            pstm.setString(10, objeto.getCpf());
            pstm.setString(11, objeto.getRg());
            pstm.setString(12, objeto.getObs());
            pstm.setString(13, String.valueOf(objeto.getStatus()));
            pstm.setString(14, objeto.getRazaoSocial());
            pstm.setString(15, objeto.getCnpj());
            pstm.setString(16, objeto.getInscricaoEstadual());
            pstm.setString(17, objeto.getContato());
            pstm.setInt(18, objeto.getId()); 

            pstm.executeUpdate();
            conexao.commit();

        } catch (SQLException ex) {
            try {
                if (conexao != null) conexao.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(Fornecedor objeto) {
        String sql = "DELETE FROM fornecedor WHERE id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement pstm = conexao.prepareStatement(sql)) {
            
            // O try-with-resources acima já fecha conexão e pstm
            // Mas se usar transação manual, precisa de cuidado
            conexao.setAutoCommit(false);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
            conexao.commit();

        } catch (SQLException ex) {
            ex.printStackTrace();
            // Rollback é difícil aqui pois a conexão fecha no final do try
            // Se precisar de rollback estrito, use a estrutura try/catch/finally clássica igual no Create
        }
    }
}