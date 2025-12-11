package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Quarto;

public class QuartoDAO implements InterfaceDAO<Quarto> {
    
    private static final String COLUNAS = "id, descricao, capacidade_hospedes, metragem, identificacao, andar, flag_animais, obs, status, valor_diaria"; 
    private static final String NOME_TABELA = "quarto";

    private Quarto buildQuartoFromResultSet(ResultSet rst) throws SQLException {
        Quarto quarto = new Quarto();
        quarto.setId(rst.getInt("id"));
        quarto.setDescricao(rst.getString("descricao"));
        quarto.setCapacidadeHospedes(rst.getInt("capacidade_hospedes"));
        quarto.setMetragem(rst.getFloat("metragem"));
        quarto.setIdentificacao(rst.getString("identificacao"));
        quarto.setAndar(rst.getInt("andar"));
        // Mapeando tinyint(4) para char '0' ou '1'
        quarto.setFlagAnimais(rst.getInt("flag_animais") == 1 ? '1' : '0'); 
        quarto.setObs(rst.getString("obs"));
        quarto.setStatus(rst.getString("status").charAt(0));
        quarto.setValorDiaria(rst.getFloat("valor_diaria"));
        
        return quarto;
    }

    @Override
    public void Create(Quarto objeto) {
        String sqlInstrucao = "INSERT INTO " + NOME_TABELA + " (descricao, capacidade_hospedes, metragem, identificacao, andar, flag_animais, obs, status, valor_diaria) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setString(1, objeto.getDescricao());
            pstm.setInt(2, objeto.getCapacidadeHospedes());
            pstm.setFloat(3, objeto.getMetragem());
            pstm.setString(4, objeto.getIdentificacao());
            pstm.setInt(5, objeto.getAndar());
            // Mapeando char '0' ou '1' para tinyint(4)
            pstm.setInt(6, objeto.getFlagAnimais() == '1' ? 1 : 0); 
            pstm.setString(7, objeto.getObs());
            pstm.setString(8, String.valueOf(objeto.getStatus()));
            pstm.setFloat(9, objeto.getValorDiaria());

            pstm.executeUpdate(); 
            conexao.commit();

        } catch (SQLException ex) {
            System.err.println("Erro ao criar Quarto: " + ex.getMessage());
            try { if (conexao != null) conexao.rollback(); } catch (SQLException e) {}
            throw new RuntimeException("Falha na criação do Quarto.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public Quarto Retrieve(int id) {
        String sqlInstrucao = "SELECT " + COLUNAS + " FROM " + NOME_TABELA + " WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Quarto quarto = null;

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            
            if (rst.next()) {
                quarto = buildQuartoFromResultSet(rst);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Quarto por ID: " + ex.getMessage());
            throw new RuntimeException("Falha na busca por ID.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return quarto;
    }

    @Override
    public List<Quarto> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT " + COLUNAS + " FROM " + NOME_TABELA;
        if (atributo != null && !atributo.trim().isEmpty()) {
            sqlInstrucao += " WHERE " + atributo + " LIKE ?";
        }
        
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Quarto> listaQuartos = new ArrayList<>();

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            if (atributo != null && !atributo.trim().isEmpty()) {
                pstm.setString(1, "%" + valor + "%");
            }

            rst = pstm.executeQuery();
            
            while (rst.next()) {
                listaQuartos.add(buildQuartoFromResultSet(rst));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Quartos: " + ex.getMessage());
            throw new RuntimeException("Falha na busca de Quartos.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return listaQuartos;
    }

    @Override
    public void Update(Quarto objeto) {
        String sqlInstrucao = "UPDATE " + NOME_TABELA + " SET descricao = ?, capacidade_hospedes = ?, metragem = ?, identificacao = ?, andar = ?, flag_animais = ?, obs = ?, status = ?, valor_diaria = ? WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;
        
        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setString(1, objeto.getDescricao());
            pstm.setInt(2, objeto.getCapacidadeHospedes());
            pstm.setFloat(3, objeto.getMetragem());
            pstm.setString(4, objeto.getIdentificacao());
            pstm.setInt(5, objeto.getAndar());
            pstm.setInt(6, objeto.getFlagAnimais() == '1' ? 1 : 0); 
            pstm.setString(7, objeto.getObs());
            pstm.setString(8, String.valueOf(objeto.getStatus()));
            pstm.setFloat(9, objeto.getValorDiaria());
            pstm.setInt(10, objeto.getId()); 
            
            pstm.executeUpdate();
            conexao.commit();
            
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar Quarto: " + ex.getMessage());
             try { if (conexao != null) conexao.rollback(); } catch (SQLException e) {}
            throw new RuntimeException("Falha na atualização do Quarto.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(Quarto objeto) {
         String sqlInstrucao = "DELETE FROM " + NOME_TABELA + " WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;
        
        if (objeto.getId() == 0) {
            throw new IllegalArgumentException("O objeto Quarto não possui ID válido para ser deletado.");
        }
        
        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setInt(1, objeto.getId());
            
            pstm.executeUpdate();
            conexao.commit();
            
        } catch (SQLException ex) {
            System.err.println("Erro ao deletar Quarto: " + ex.getMessage());
             try { if (conexao != null) conexao.rollback(); } catch (SQLException e) {}
            throw new RuntimeException("Falha na exclusão do Quarto.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }
}