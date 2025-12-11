package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;

public class FuncionarioDAO implements InterfaceDAO<Funcionario> {

    // 17 COLUNAS para INSERT (Adicionado data_cadastro e cpf)
    private final String COLUNAS_INSERT = "nome, fone, fone2, email, cep, logradouro, bairro, cidade, complemento, data_cadastro, cpf, rg, obs, status, usuario, senha, sexo";
    private final String COLUNAS_READ = "id, " + COLUNAS_INSERT; // 18 colunas para READ

    @Override
    public void Create(Funcionario objeto) {
        // 17 interrogações para as 17 colunas
        String sql = "INSERT INTO funcionario (" + COLUNAS_INSERT + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement pstm = conexao.prepareStatement(sql)) {

            conexao.setAutoCommit(false);

            pstm.setString(1, objeto.getNome());
            pstm.setString(2, objeto.getFone());
            pstm.setString(3, objeto.getFone2());
            pstm.setString(4, objeto.getEmail());
            pstm.setString(5, objeto.getCep());
            pstm.setString(6, objeto.getLogradouro());
            pstm.setString(7, objeto.getBairro());
            pstm.setString(8, objeto.getCidade());
            pstm.setString(9, objeto.getComplemento());
            
            pstm.setString(10, objeto.getDataCadastro()); // NOVO
            pstm.setString(11, objeto.getCpf());          // NOVO
            
            pstm.setString(12, objeto.getRg());
            pstm.setString(13, objeto.getObs());
            pstm.setString(14, String.valueOf(objeto.getStatus()));
            pstm.setString(15, objeto.getUsuario());
            pstm.setString(16, objeto.getSenha());
            pstm.setString(17, objeto.getSexo()); // Parâmetro 17

            pstm.executeUpdate();
            conexao.commit(); 

        } catch (SQLException ex) {
            System.err.println("Erro ao criar Funcionário: " + ex.getMessage());
             try {
                // Tenta reverter a transação em caso de erro
                Connection conexao = ConnectionFactory.getConnection(); 
                if (conexao != null) conexao.rollback(); 
            } catch (SQLException e) {
                // Apenas loga se a reversão falhar
                e.printStackTrace(); 
            }
            throw new RuntimeException("Falha na criação do Funcionário. A transação foi revertida.", ex);
        }
    }

    @Override
    public Funcionario Retrieve(int id) {
        // Usando COLUNAS_READ para evitar o SELECT *
        String sql = "SELECT " + COLUNAS_READ + " FROM funcionario WHERE id = ?";
        Funcionario funcionario = null;

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement pstm = conexao.prepareStatement(sql)) {

            pstm.setInt(1, id);
            try (ResultSet rst = pstm.executeQuery()) {
                if (rst.next()) {
                    funcionario = new Funcionario();
                    mapearResultado(funcionario, rst);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Falha na busca do Funcionário.", ex);
        }
        return funcionario;
    }
    
    // Alias para o Service (buscarPorId)
    public Funcionario buscar(int id) {
        return Retrieve(id);
    }

    @Override
    public List<Funcionario> Retrieve(String atributo, String valor) {
        // Usando COLUNAS_READ para evitar o SELECT *
        String sql = "SELECT " + COLUNAS_READ + " FROM funcionario";
        
        if (atributo != null && valor != null && !valor.trim().isEmpty()) {
            // Se você quer busca exata, remova os LIKE. Para busca parcial, mantenha:
            sql += " WHERE " + atributo + " LIKE ?";
        }

        List<Funcionario> funcionarios = new ArrayList<>();

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement pstm = conexao.prepareStatement(sql)) {

            if (atributo != null && valor != null && !valor.trim().isEmpty()) {
                pstm.setString(1, "%" + valor + "%"); 
            }

            try (ResultSet rst = pstm.executeQuery()) {
                while (rst.next()) {
                    Funcionario funcionario = new Funcionario();
                    mapearResultado(funcionario, rst);
                    funcionarios.add(funcionario);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Falha na busca de Funcionários.", ex);
        }
        return funcionarios;
    }
    
    // Alias para Listar Todos
    public List<Funcionario> Retrieve() {
        return Retrieve(null, null);
    }


    @Override
    public void Update(Funcionario objeto) {
        // 17 COLUNAS para UPDATE, 18º parâmetro é o ID (WHERE)
        String sql = "UPDATE funcionario SET nome = ?, fone = ?, fone2 = ?, email = ?, cep = ?, " + 
                     "logradouro = ?, bairro = ?, cidade = ?, complemento = ?, data_cadastro = ?, cpf = ?, rg = ?, obs = ?, " + 
                     "status = ?, usuario = ?, senha = ?, sexo = ? WHERE id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement pstm = conexao.prepareStatement(sql)) {

            conexao.setAutoCommit(false);

            pstm.setString(1, objeto.getNome());
            pstm.setString(2, objeto.getFone());
            pstm.setString(3, objeto.getFone2());
            pstm.setString(4, objeto.getEmail());
            pstm.setString(5, objeto.getCep());
            pstm.setString(6, objeto.getLogradouro());
            pstm.setString(7, objeto.getBairro());
            pstm.setString(8, objeto.getCidade());
            pstm.setString(9, objeto.getComplemento());
            
            pstm.setString(10, objeto.getDataCadastro()); // Parâmetro 10
            pstm.setString(11, objeto.getCpf());          // Parâmetro 11
            
            pstm.setString(12, objeto.getRg());
            pstm.setString(13, objeto.getObs());
            pstm.setString(14, String.valueOf(objeto.getStatus()));
            pstm.setString(15, objeto.getUsuario());
            pstm.setString(16, objeto.getSenha());
            pstm.setString(17, objeto.getSexo()); // Parâmetro 17
            pstm.setInt(18, objeto.getId());      // Parâmetro 18 (WHERE)

            pstm.executeUpdate();
            conexao.commit();

        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar Funcionário: " + ex.getMessage());
            try {
                Connection conexao = ConnectionFactory.getConnection(); 
                if (conexao != null) conexao.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("Falha na atualização do Funcionário. A transação foi revertida.", ex);
        }
    }

    @Override
    public void Delete(Funcionario objeto) {
        String sql = "DELETE FROM funcionario WHERE id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement pstm = conexao.prepareStatement(sql)) {
            
            conexao.setAutoCommit(false);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
            conexao.commit();

        } catch (SQLException ex) {
            System.err.println("Erro ao deletar Funcionário: " + ex.getMessage());
            try {
                Connection conexao = ConnectionFactory.getConnection(); 
                if (conexao != null) conexao.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("Falha ao deletar o Funcionário. A transação foi revertida.", ex);
        }
    }
    
    private void mapearResultado(Funcionario funcionario, ResultSet rst) throws SQLException {
        funcionario.setId(rst.getInt("id"));
        funcionario.setNome(rst.getString("nome"));
        funcionario.setFone(rst.getString("fone"));
        funcionario.setFone2(rst.getString("fone2"));
        funcionario.setEmail(rst.getString("email"));
        funcionario.setCep(rst.getString("cep"));
        funcionario.setLogradouro(rst.getString("logradouro"));
        funcionario.setBairro(rst.getString("bairro"));
        funcionario.setCidade(rst.getString("cidade"));
        funcionario.setComplemento(rst.getString("complemento"));
        
        funcionario.setDataCadastro(rst.getString("data_cadastro")); 
        funcionario.setCpf(rst.getString("cpf"));                     
        
        funcionario.setRg(rst.getString("rg"));
        funcionario.setObs(rst.getString("obs"));
        
        String statusStr = rst.getString("status");
        if(statusStr != null && !statusStr.isEmpty()){
            funcionario.setStatus(statusStr.charAt(0));
        }

        funcionario.setUsuario(rst.getString("usuario"));
        funcionario.setSenha(rst.getString("senha"));
        
        funcionario.setSexo(rst.getString("sexo")); 
    }
}