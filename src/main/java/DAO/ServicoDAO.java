package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Servico;

public class ServicoDAO implements InterfaceDAO<Servico> {

    // --- CREATE (Insere um novo Serviço) ---
    @Override
    public void Create(Servico objeto) {
        // SQL CORRIGIDO: Usa 'descricao', 'obs' e 'STATUS' (em maiúsculas)
        String sqlInstrucao = "INSERT INTO servico (descricao, obs, STATUS) VALUES (?, ?, ?)";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);

            // Mapeamento dos parâmetros:
            // 1. O nome principal do serviço (objeto.getNome()) vai para 'descricao'
            pstm.setString(1, objeto.getDescricao()); 
            // 2. A descrição detalhada (objeto.getDescricao()) vai para 'obs'
            pstm.setString(2, objeto.getObs());
            // 3. O status (objeto.getStatus()) vai para 'STATUS'
            pstm.setString(3, String.valueOf(objeto.getStatus()));

            pstm.executeUpdate(); 
            conexao.commit();

        } catch (SQLException ex) {
            System.err.println("Erro ao criar Serviço: " + ex.getMessage());
            try {
                if (conexao != null) conexao.rollback();
            } catch (SQLException e) {
                System.err.println("Erro ao fazer rollback: " + e.getMessage());
            }
            throw new RuntimeException("Falha na criação do Serviço.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    // --- RETRIEVE POR ID ---
    @Override
    public Servico Retrieve(int id) {
        String sqlInstrucao = "SELECT id, descricao, obs, STATUS FROM servico WHERE id = ?";
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
                servico = new Servico();
                servico.setId(rst.getInt("id"));
                // Mapeamento: 'descricao' do DB vai para nome no Model
                servico.setDescricao(rst.getString("descricao")); 
                // Mapeamento: 'obs' do DB vai para descrição no Model
                servico.setObs(rst.getString("obs")); 
                // Mapeamento: 'STATUS' do DB vai para status no Model
                servico.setStatus(rst.getString("STATUS").charAt(0)); 
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
        String sqlInstrucao = "SELECT id, descricao, obs, STATUS FROM servico";
        boolean isGeral = (atributo == null || atributo.trim().isEmpty());
        
        if (!isGeral) {
            sqlInstrucao += " WHERE " + atributo + " LIKE ?";
        }
        
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Servico> listaServicos = new ArrayList<>();

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            if (!isGeral) {
                pstm.setString(1, "%" + valor + "%");
            }

            rst = pstm.executeQuery();
            
            while (rst.next()) {
                Servico servico = new Servico();
                servico.setId(rst.getInt("id"));
                // Mapeamento de retorno:
                servico.setDescricao(rst.getString("descricao"));
                servico.setObs(rst.getString("obs"));
                servico.setStatus(rst.getString("STATUS").charAt(0));
                listaServicos.add(servico);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Serviços: " + ex.getMessage());
            throw new RuntimeException("Falha na busca de Serviços.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return listaServicos;
    }
    
    // --- UPDATE ---
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
            } catch (SQLException e) {
                System.err.println("Erro ao fazer rollback: " + e.getMessage());
            }
            throw new RuntimeException("Falha na atualização do Serviço.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    // --- DELETE ---
    @Override
    public void Delete(Servico objeto) {
        String sqlInstrucao = "DELETE FROM servico WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, objeto.getId());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao deletar Serviço: " + ex.getMessage());
            throw new RuntimeException("Falha na exclusão do Serviço.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }
}