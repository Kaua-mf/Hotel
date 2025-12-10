package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;

public class FuncionarioDAO implements InterfaceDAO<Funcionario> {

    private final String COLUNAS_INSERT = "nome, fone, fone2, email, cep, logradouro, bairro, cidade, complemento, rg, obs, status, usuario, senha, sexo";

    @Override
    public void Create(Funcionario objeto) {
        String sql = "INSERT INTO funcionario (" + COLUNAS_INSERT + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            pstm.setString(10, objeto.getRg());
            pstm.setString(11, objeto.getObs());
            pstm.setString(12, String.valueOf(objeto.getStatus()));
            pstm.setString(13, objeto.getUsuario());
            pstm.setString(14, objeto.getSenha());
            pstm.setString(15, objeto.getSexo());

            pstm.executeUpdate();
            conexao.commit(); 

        } catch (SQLException ex) {
            System.err.println("Erro ao criar Funcionário: " + ex.getMessage());
            throw new RuntimeException("Falha na criação do Funcionário. A transação foi revertida.", ex);
        }
    }

    @Override
    public Funcionario Retrieve(int id) {
        String sql = "SELECT * FROM funcionario WHERE id = ?";
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
            System.err.println("Erro ao buscar Funcionário por ID: " + ex.getMessage());
            throw new RuntimeException("Falha na busca do Funcionário.", ex);
        }
        return funcionario;
    }
    
    @Override
    public List<Funcionario> Retrieve(String atributo, String valor) {
        String sql = "SELECT * FROM funcionario";
        
        if (atributo != null && valor != null && !valor.trim().isEmpty()) {
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
            System.err.println("Erro ao buscar lista de Funcionários: " + ex.getMessage());
            throw new RuntimeException("Falha na busca de Funcionários.", ex);
        }
        return funcionarios;
    }

    @Override
    public void Update(Funcionario objeto) {
        String sql = "UPDATE funcionario SET nome = ?, fone = ?, fone2 = ?, email = ?, cep = ?, " + 
                     "logradouro = ?, bairro = ?, cidade = ?, complemento = ?, rg = ?, obs = ?, " + 
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
            pstm.setString(10, objeto.getRg());
            pstm.setString(11, objeto.getObs());
            pstm.setString(12, String.valueOf(objeto.getStatus()));
            pstm.setString(13, objeto.getUsuario());
            pstm.setString(14, objeto.getSenha());
            pstm.setInt(15, objeto.getId()); 
            pstm.setString(15, objeto.getSexo());

            pstm.executeUpdate();
            conexao.commit();

        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar Funcionário: " + ex.getMessage());
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
        funcionario.setStatus(rst.getString("status").charAt(0));
        funcionario.setUsuario(rst.getString("usuario"));
        funcionario.setSenha(rst.getString("senha"));
        funcionario.setSenha(rst.getString("sexo"));
    }
}