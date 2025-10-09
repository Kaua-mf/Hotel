package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Marca;

public class MarcaDAO implements InterfaceDAO<Marca> {

    @Override
    public void Create(Marca objeto) {
        String sqlInstrucao = "INSERT INTO marca(descricao, STATUS) VALUES(?, ?)";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, String.valueOf(objeto.getStatus()));
            
            pstm.executeUpdate(); 
            conexao.commit(); 

        } catch (SQLException ex) {
            System.err.println("Erro ao criar Marca: " + ex.getMessage());
            try {
                if (conexao != null) conexao.rollback();
            } catch (SQLException e) {
                System.err.println("Erro ao fazer rollback: " + e.getMessage());
            }
            throw new RuntimeException("Falha na criação da Marca.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public Marca Retrieve(int id) {
        String sqlInstrucao = "SELECT id, descricao, STATUS FROM marca WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Marca marca = null; 

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            
            if (rst.next()) {
                marca = new Marca();
                marca.setId(rst.getInt("id"));
                marca.setDescricao(rst.getString("descricao"));
                marca.setStatus(rst.getString("STATUS").charAt(0));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Falha na busca de Marca por ID.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return marca;
    }

    @Override
    public List<Marca> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT id, descricao, STATUS FROM marca";
        boolean isGeral = (atributo == null || atributo.trim().isEmpty());
        
        if (!isGeral) {
            sqlInstrucao += " WHERE " + atributo + " LIKE ?";
        }
        
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Marca> listaMarcas = new ArrayList<>();

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            if (!isGeral) {
                pstm.setString(1, "%" + valor + "%");
            }

            rst = pstm.executeQuery();
            
            while (rst.next()) { 
                Marca marca = new Marca();
                marca.setId(rst.getInt("id"));
                marca.setDescricao(rst.getString("descricao"));
                marca.setStatus(rst.getString("STATUS").charAt(0));
                listaMarcas.add(marca); 
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Falha na busca de Marcas.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return listaMarcas;
    }

    @Override
    public void Update(Marca objeto) {
        String sqlInstrucao = "UPDATE marca SET descricao = ?, STATUS = ? WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false);
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setString(1, objeto.getDescricao());
            pstm.setString(2, String.valueOf(objeto.getStatus()));
            pstm.setInt(3, objeto.getId());

            pstm.executeUpdate();
            conexao.commit();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Falha na atualização da Marca.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(Marca objeto) {
        String sqlInstrucao = "DELETE FROM marca WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, objeto.getId());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Falha na exclusão da Marca.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }
}