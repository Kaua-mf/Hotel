package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.VagaEstacionamento;

public class VagaEstacionamentoDAO implements InterfaceDAO<VagaEstacionamento> {
    
    private static final String COLUNAS = "id, descricao, obs, metragem_vaga, status";
    private static final String NOME_TABELA = "vaga_estacionamento"; 

    private VagaEstacionamento buildVagaFromResultSet(ResultSet rst) throws SQLException {
        VagaEstacionamento vaga = new VagaEstacionamento();
        vaga.setId(rst.getInt("id"));
        vaga.setDescricao(rst.getString("descricao"));
        vaga.setObs(rst.getString("obs"));
        vaga.setMetragemVaga(rst.getFloat("metragem_vaga"));
        vaga.setStatus(rst.getString("status").charAt(0));
        return vaga;
    }

    @Override
    public void Create(VagaEstacionamento objeto) {
        String sqlInstrucao = "INSERT INTO " + NOME_TABELA + " (descricao, obs, metragem_vaga, status) VALUES (?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, objeto.getObs());
            pstm.setFloat(3, objeto.getMetragemVaga());
            pstm.setString(4, String.valueOf(objeto.getStatus()));

            pstm.executeUpdate(); 
            conexao.commit();

        } catch (SQLException ex) {
            System.err.println("Erro ao criar Vaga: " + ex.getMessage());
            try {
                if (conexao != null) conexao.rollback();
            } catch (SQLException e) {}
            throw new RuntimeException("Falha na criação da Vaga.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public VagaEstacionamento Retrieve(int id) {
        String sqlInstrucao = "SELECT " + COLUNAS + " FROM " + NOME_TABELA + " WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        VagaEstacionamento vaga = null;

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            
            if (rst.next()) {
                vaga = buildVagaFromResultSet(rst);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Vaga por ID: " + ex.getMessage());
            throw new RuntimeException("Falha na busca por ID.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return vaga;
    }

    @Override
    public List<VagaEstacionamento> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT " + COLUNAS + " FROM " + NOME_TABELA;
        if (atributo != null && !atributo.trim().isEmpty()) {
            sqlInstrucao += " WHERE " + atributo + " LIKE ?";
        }
        
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<VagaEstacionamento> listaVagas = new ArrayList<>();

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            if (atributo != null && !atributo.trim().isEmpty()) {
                pstm.setString(1, "%" + valor + "%");
            }

            rst = pstm.executeQuery();
            
            while (rst.next()) {
                listaVagas.add(buildVagaFromResultSet(rst));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Vagas: " + ex.getMessage());
            throw new RuntimeException("Falha na busca de Vagas.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return listaVagas;
    }

    @Override
    public void Update(VagaEstacionamento objeto) {
        String sqlInstrucao = "UPDATE " + NOME_TABELA + " SET descricao = ?, obs = ?, metragem_vaga = ?, status = ? WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;
        
        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, objeto.getObs());
            pstm.setFloat(3, objeto.getMetragemVaga());
            pstm.setString(4, String.valueOf(objeto.getStatus()));
            
            pstm.setInt(5, objeto.getId()); 
            
            pstm.executeUpdate();
            conexao.commit();
            
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar Vaga: " + ex.getMessage());
             try {
                if (conexao != null) conexao.rollback();
            } catch (SQLException e) {}
            throw new RuntimeException("Falha na atualização da Vaga.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(VagaEstacionamento objeto) {
        // Implementar lógica de DELETE, se necessário
    }
}