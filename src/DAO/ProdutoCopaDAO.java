package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ProdutoCopa;

public class ProdutoCopaDAO implements InterfaceDAO<ProdutoCopa> {

    // --- CREATE (Insere um novo Produto) ---
    @Override
    public void Create(ProdutoCopa objeto) {
        // SQL: Adicionado obs e as colunas do DB
        String sqlInstrucao = "INSERT INTO produto_copa (descricao, valor, obs, status) VALUES (?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);

            // Mapeamento: Ordem correta para o INSERT
            pstm.setString(1, objeto.getDescricao());
            pstm.setFloat(2, objeto.getValor());
            pstm.setString(3, objeto.getObs()); 
            pstm.setString(4, String.valueOf(objeto.getStatus()));

            pstm.executeUpdate(); 
            conexao.commit();

        } catch (SQLException ex) {
            System.err.println("Erro ao criar Produto Copa: " + ex.getMessage());
            try {
                if (conexao != null) conexao.rollback();
            } catch (SQLException e) {
                // ...
            }
            throw new RuntimeException("Falha na criação do Produto Copa.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    // --- RETRIEVE POR ID ---
    @Override
    public ProdutoCopa Retrieve(int id) {
        String sqlInstrucao = "SELECT id, descricao, valor, obs, status FROM produto_copa WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        ProdutoCopa produto = null;

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            
            if (rst.next()) {
                produto = new ProdutoCopa();
                produto.setId(rst.getInt("id"));
                produto.setDescricao(rst.getString("descricao"));
                produto.setValor(rst.getFloat("valor"));
                produto.setObs(rst.getString("obs"));
                produto.setStatus(rst.getString("status").charAt(0));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Produto Copa por ID: " + ex.getMessage());
            throw new RuntimeException("Falha na busca por ID.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return produto;
    }

    // --- RETRIEVE POR FILTRO / ALL ---
    @Override
    public List<ProdutoCopa> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT id, descricao, valor, obs, status FROM produto_copa";
        if (atributo != null && !atributo.trim().isEmpty()) {
            sqlInstrucao += " WHERE " + atributo + " LIKE ?";
        }
        
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<ProdutoCopa> listaProdutos = new ArrayList<>();

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            if (atributo != null && !atributo.trim().isEmpty()) {
                pstm.setString(1, "%" + valor + "%");
            }

            rst = pstm.executeQuery();
            
            while (rst.next()) {
                ProdutoCopa produto = new ProdutoCopa();
                produto.setId(rst.getInt("id"));
                produto.setDescricao(rst.getString("descricao"));
                produto.setValor(rst.getFloat("valor"));
                produto.setObs(rst.getString("obs"));
                produto.setStatus(rst.getString("status").charAt(0));
                listaProdutos.add(produto);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Produtos Copa: " + ex.getMessage());
            throw new RuntimeException("Falha na busca de Produtos Copa.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return listaProdutos;
    }

    // --- UPDATE ---
    @Override
    public void Update(ProdutoCopa objeto) {
        String sqlInstrucao = "UPDATE produto_copa SET descricao = ?, valor = ?, obs = ?, status = ? WHERE id = ?";
        // ... Lógica JDBC similar ao Create/Delete
        throw new UnsupportedOperationException("Update ainda não implementado.");
    }

    // --- DELETE ---
    @Override
    public void Delete(ProdutoCopa objeto) {
        String sqlInstrucao = "DELETE FROM produto_copa WHERE id = ?";
        // ... Lógica JDBC similar ao Create/Update
        throw new UnsupportedOperationException("Delete ainda não implementado.");
    }
}