package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Veiculo;

public class VeiculoDAO implements InterfaceDAO<Veiculo> {
    
    private static final String COLUNAS = "id, placa, cor, status, modelo_id, hospede_id, funcionario_id, fornecedor_id";

    private Veiculo buildVeiculoFromResultSet(ResultSet rst) throws SQLException {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(rst.getInt("id"));
        veiculo.setPlaca(rst.getString("placa"));
        veiculo.setCor(rst.getString("cor"));
        veiculo.setStatus(rst.getString("status").charAt(0));
        
        int modeloId = rst.getInt("modelo_id");
        if (modeloId != 0) {
            model.Modelo modelo = new model.Modelo();
            modelo.setId(modeloId);
            veiculo.setModelo(modelo);
        }
        
        
        int hospedeId = rst.getInt("hospede_id");
        if (hospedeId != 0) {
            model.Hospede h = new model.Hospede();
            h.setId(hospedeId);
            veiculo.setHospede(h);
        }
        
        int funcionarioId = rst.getInt("funcionario_id");
        if (funcionarioId != 0) {
            model.Funcionario f = new model.Funcionario();
            f.setId(funcionarioId);
            veiculo.setFuncionario(f);
        }
        
        int fornecedorId = rst.getInt("fornecedor_id");
        if (fornecedorId != 0) {
            model.Fornecedor f = new model.Fornecedor();
            f.setId(fornecedorId);
            veiculo.setFornecedor(f);
        }
        
        return veiculo;
    }

    @Override
    public void Create(Veiculo objeto) {
        String sqlInstrucao = "INSERT INTO veiculo (placa, cor, status, modelo_id, hospede_id, funcionario_id, fornecedor_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setString(1, objeto.getPlaca());
            pstm.setString(2, objeto.getCor());
            pstm.setString(3, String.valueOf(objeto.getStatus()));
            pstm.setInt(4, objeto.getModelo().getId());
            
            pstm.setObject(5, objeto.getHospede() != null ? objeto.getHospede().getId() : null, Types.INTEGER);
            pstm.setObject(6, objeto.getFuncionario() != null ? objeto.getFuncionario().getId() : null, Types.INTEGER);
            pstm.setObject(7, objeto.getFornecedor() != null ? objeto.getFornecedor().getId() : null, Types.INTEGER);

            pstm.executeUpdate(); 
            conexao.commit();

        } catch (SQLException ex) {
            System.err.println("Erro ao criar Veículo: " + ex.getMessage());
            try { if (conexao != null) conexao.rollback(); } catch (SQLException e) {}
            throw new RuntimeException("Falha na criação do Veículo.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public Veiculo Retrieve(int id) {
        String sqlInstrucao = "SELECT " + COLUNAS + " FROM veiculo WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Veiculo veiculo = null;

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            
            if (rst.next()) {
                veiculo = buildVeiculoFromResultSet(rst);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Veículo por ID: " + ex.getMessage());
            throw new RuntimeException("Falha na busca por ID.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return veiculo;
    }

    @Override
    public List<Veiculo> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT " + COLUNAS + " FROM veiculo";
        if (atributo != null && !atributo.trim().isEmpty()) {
            sqlInstrucao += " WHERE " + atributo + " LIKE ?";
        }
        
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Veiculo> listaVeiculos = new ArrayList<>();

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            if (atributo != null && !atributo.trim().isEmpty()) {
                pstm.setString(1, "%" + valor + "%");
            }

            rst = pstm.executeQuery();
            
            while (rst.next()) {
                listaVeiculos.add(buildVeiculoFromResultSet(rst));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Veículos: " + ex.getMessage());
            throw new RuntimeException("Falha na busca de Veículos.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return listaVeiculos;
    }

    @Override
    public void Update(Veiculo objeto) {
        String sqlInstrucao = "UPDATE veiculo SET placa = ?, cor = ?, status = ?, modelo_id = ?, hospede_id = ?, funcionario_id = ?, fornecedor_id = ? WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;
        
        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setString(1, objeto.getPlaca());
            pstm.setString(2, objeto.getCor());
            pstm.setString(3, String.valueOf(objeto.getStatus()));
            pstm.setInt(4, objeto.getModelo().getId());
            
            pstm.setObject(5, objeto.getHospede() != null ? objeto.getHospede().getId() : null, Types.INTEGER);
            // LINHA CORRIGIDA: removido o ': null' duplicado
            pstm.setObject(6, objeto.getFuncionario() != null ? objeto.getFuncionario().getId() : null, Types.INTEGER);
            pstm.setObject(7, objeto.getFornecedor() != null ? objeto.getFornecedor().getId() : null, Types.INTEGER);
            
            pstm.setInt(8, objeto.getId()); 
            
            pstm.executeUpdate();
            conexao.commit();
            
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar Veículo: " + ex.getMessage());
             try { if (conexao != null) conexao.rollback(); } catch (SQLException e) {}
            throw new RuntimeException("Falha na atualização do Veículo.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(Veiculo objeto) {
         String sqlInstrucao = "DELETE FROM veiculo WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;
        
        if (objeto.getId() == 0) {
            throw new IllegalArgumentException("O objeto Veículo não possui ID válido para ser deletado.");
        }
        
        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            pstm.setInt(1, objeto.getId());
            
            pstm.executeUpdate();
            conexao.commit();
            
        } catch (SQLException ex) {
            System.err.println("Erro ao deletar Veículo: " + ex.getMessage());
             try { if (conexao != null) conexao.rollback(); } catch (SQLException e) {}
            throw new RuntimeException("Falha na exclusão do Veículo.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }
}