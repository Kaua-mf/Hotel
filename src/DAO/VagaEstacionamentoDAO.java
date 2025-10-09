package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.VagaEstacionamento;

public class VagaEstacionamentoDAO implements InterfaceDAO<VagaEstacionamento> {

    // --- CREATE (Insere uma nova Vaga) ---
    @Override
    public void Create(VagaEstacionamento objeto) {
        String sqlInstrucao = "INSERT INTO vaga_estacionamento(descricao, obs, metragem_vaga, status) VALUES (?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            // Habilita transação
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getDescricao());
            
            // Mapeamento de Obs (trata como NULL se for vazio/nulo)
            if (objeto.getObs() != null && !objeto.getObs().trim().isEmpty()) {
                pstm.setString(2, objeto.getObs());
            } else {
                pstm.setNull(2, Types.VARCHAR);
            }
            
            pstm.setFloat(3, objeto.getMetragemVaga()); 
            pstm.setString(4, String.valueOf(objeto.getStatus()));
            
            pstm.executeUpdate();
            conexao.commit(); 
            
        } catch (SQLException ex) {
            System.err.println("Erro ao criar Vaga: " + ex.getMessage());
            try {
                if (conexao != null) conexao.rollback();
            } catch (SQLException e) {
                // Log de erro de rollback
            }
            // Lança RuntimeException para a camada Service
            throw new RuntimeException("Falha na criação da Vaga.", ex);

        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    // --- RETRIEVE POR ID ---
    @Override
    public VagaEstacionamento Retrieve(int id) {
        String sqlInstrucao = "SELECT id, descricao, obs, metragem_vaga, status FROM vaga_estacionamento WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        VagaEstacionamento vaga = null;

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();

            if(rst.next()) {
                vaga = new VagaEstacionamento();
                vaga.setId(rst.getInt("id"));
                vaga.setDescricao(rst.getString("descricao"));
                vaga.setObs(rst.getString("obs"));
                vaga.setMetragemVaga(rst.getFloat("metragem_vaga"));
                vaga.setStatus(rst.getString("status").charAt(0));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Vaga por ID: " + ex.getMessage());
            throw new RuntimeException("Falha na busca da Vaga por ID.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return vaga;
        }
    }

    // --- RETRIEVE POR FILTRO / ALL ---
    @Override
    public List<VagaEstacionamento> Retrieve(String atributo, String valor) {
        // Query base para buscar todos
        String sqlInstrucao = "SELECT id, descricao, obs, metragem_vaga, status FROM vaga_estacionamento";
        boolean isGeral = (atributo == null || atributo.trim().isEmpty());

        if (!isGeral) {
             // Usa LIKE para filtro de texto
             sqlInstrucao += " WHERE " + atributo + " LIKE ?";
        }
        
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<VagaEstacionamento> listaVagas = new ArrayList<>();

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            if (!isGeral) {
                pstm.setString(1, "%" + valor + "%");
            }
            
            rst = pstm.executeQuery();
            
            while(rst.next()) {
                VagaEstacionamento vaga = new VagaEstacionamento();
                vaga.setId(rst.getInt("id"));
                vaga.setDescricao(rst.getString("descricao"));
                vaga.setObs(rst.getString("obs"));
                vaga.setMetragemVaga(rst.getFloat("metragem_vaga"));
                vaga.setStatus(rst.getString("status").charAt(0));
                listaVagas.add(vaga);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Vagas: " + ex.getMessage());
            throw new RuntimeException("Falha na busca de Vagas.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaVagas;
        }
    }

    // --- UPDATE ---
    @Override
    public void Update(VagaEstacionamento objeto) {
        String sqlInstrucao = "UPDATE vaga_estacionamento SET descricao = ?, obs = ?, metragem_vaga = ?, status = ? WHERE id = ?";
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
            throw new RuntimeException("Falha na atualização da Vaga.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    // --- DELETE ---
    @Override
    public void Delete(VagaEstacionamento objeto) {
        String sqlInstrucao = "DELETE FROM vaga_estacionamento WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao deletar Vaga: " + ex.getMessage());
            throw new RuntimeException("Falha na exclusão da Vaga.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }
}