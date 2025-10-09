package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Quarto;

public class QuartoDAO implements InterfaceDAO<Quarto> {

    @Override
    public void Create(Quarto objeto) {
        String sqlInstrucao = "INSERT INTO quarto (descricao, capacidade_hospedes, valor_diaria, obs, status, metragem, identificacao, andar, flag_animais) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setString(1, objeto.getDescricao());
            pstm.setInt(2, objeto.getCapacidadeHospedes());
            pstm.setFloat(3, objeto.getValorDiaria()); // Mapeamento correto
            pstm.setString(4, objeto.getObs());
            pstm.setString(5, String.valueOf(objeto.getStatus()));
            pstm.setFloat(6, objeto.getMetragem());
            pstm.setString(7, objeto.getIdentificacao());
            pstm.setInt(8, objeto.getAndar());
            pstm.setBoolean(9, objeto.isFlagAnimais()); 

            pstm.executeUpdate(); 
            conexao.commit();

        } catch (SQLException ex) {
            System.err.println("Erro ao criar Quarto: " + ex.getMessage());
            try {
                if (conexao != null) conexao.rollback();
            } catch (SQLException e) {
            }
            throw new RuntimeException("Falha na criação do Quarto: " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }
    
    @Override
    public List<Quarto> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT id, descricao, capacidade_hospedes, valor_diaria, obs, status, metragem, identificacao, andar, flag_animais FROM quarto";
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
                Quarto quarto = new Quarto();
                quarto.setId(rst.getInt("id"));
                quarto.setDescricao(rst.getString("descricao"));
                quarto.setCapacidadeHospedes(rst.getInt("capacidade_hospedes"));
                quarto.setValorDiaria(rst.getFloat("valor_diaria")); // Mapeamento correto
                quarto.setObs(rst.getString("obs"));
                quarto.setStatus(rst.getString("status").charAt(0));
                quarto.setMetragem(rst.getFloat("metragem"));
                quarto.setIdentificacao(rst.getString("identificacao"));
                quarto.setAndar(rst.getInt("andar"));
                quarto.setFlagAnimais(rst.getBoolean("flag_animais"));

                listaQuartos.add(quarto);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Quartos: " + ex.getMessage());
            throw new RuntimeException("Falha na busca de Quartos: " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return listaQuartos;
    }
    
    @Override public Quarto Retrieve(int id) { return null; }
    @Override public void Update(Quarto objeto) {  }
    @Override public void Delete(Quarto objeto) {  }
}