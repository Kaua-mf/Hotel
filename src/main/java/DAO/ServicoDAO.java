package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Servico;

public class ServicoDAO implements InterfaceDAO<Servico> {
    
    private static final String COLUNAS = "id, descricao, obs, STATUS";

    private Servico buildServicoFromResultSet(ResultSet rst) throws SQLException {
        Servico servico = new Servico();
        servico.setId(rst.getInt("id"));
        servico.setDescricao(rst.getString("descricao"));
        servico.setObs(rst.getString("obs"));
        servico.setStatus(rst.getString("STATUS").charAt(0));
        return servico;
    }

    @Override
    public void Create(Servico objeto) {
        String sqlInstrucao = "INSERT INTO servico (descricao, obs, STATUS) VALUES (?, ?, ?)";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, objeto.getObs());
            pstm.setString(3, String.valueOf(objeto.getStatus()));

            pstm.executeUpdate(); 
            conexao.commit();

        } catch (SQLException ex) {
            System.err.println("Erro ao criar Serviço: " + ex.getMessage());
            try {
                if (conexao != null) conexao.rollback();
            } catch (SQLException e) {}
            throw new RuntimeException("Falha na criação do Serviço.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public Servico Retrieve(int id) {
        String sqlInstrucao = "SELECT " + COLUNAS + " FROM servico WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Servico servico = null;

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            
            if (rst.next()) {
                servico = buildServicoFromResultSet(rst);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Serviço por ID: " + ex.getMessage());
            throw new RuntimeException("Falha na busca por ID.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return servico;
    }

    @Override
    public List<Servico> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT " + COLUNAS + " FROM servico";
        if (atributo != null && !atributo.trim().isEmpty()) {
            sqlInstrucao += " WHERE " + atributo + " LIKE ?";
        }
        
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Servico> listaServicos = new ArrayList<>();

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            if (atributo != null && !atributo.trim().isEmpty()) {
                pstm.setString(1, "%" + valor + "%");
            }

            rst = pstm.executeQuery();
            
            while (rst.next()) {
                listaServicos.add(buildServicoFromResultSet(rst));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Serviços: " + ex.getMessage());
            throw new RuntimeException("Falha na busca de Serviços.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return listaServicos;
    }

    @Override
    public void Update(Servico objeto) {
        String sqlInstrucao = "UPDATE servico SET descricao = ?, obs = ?, STATUS = ? WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;
        
        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, objeto.getObs());
            pstm.setString(3, String.valueOf(objeto.getStatus()));
            
            pstm.setInt(4, objeto.getId()); 
            
            pstm.executeUpdate();
            conexao.commit();
            
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar Serviço: " + ex.getMessage());
             try {
                if (conexao != null) conexao.rollback();
            } catch (SQLException e) {}
            throw new RuntimeException("Falha na atualização do Serviço.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(Servico objeto) {
        // Implementar lógica de DELETE, se necessário
    }
}