package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Veiculo;
import model.Modelo;
import model.Marca; 
import DAO.ConnectionFactory; 

public class VeiculoDAO implements InterfaceDAO<Veiculo> {

    @Override
    public void Create(Veiculo objeto) {
        String sqlInstrucao = "INSERT INTO veiculo (placa, cor, modelo_id, funcionario_id, fornecedor_id, hospede_id, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getPlaca());
            pstm.setString(2, objeto.getCor());
            
            if (objeto.getModelo() != null && objeto.getModelo().getId() != 0) {
                pstm.setInt(3, objeto.getModelo().getId());
            } else {
                pstm.setNull(3, java.sql.Types.INTEGER);
            }
            
            if (objeto.getFuncionario() != null && objeto.getFuncionario().getId() != 0) {
                pstm.setInt(4, objeto.getFuncionario().getId());
            } else {
                pstm.setNull(4, java.sql.Types.INTEGER);
            }
            
            if (objeto.getFornecedor() != null && objeto.getFornecedor().getId() != 0) {
                pstm.setInt(5, objeto.getFornecedor().getId());
            } else {
                pstm.setNull(5, java.sql.Types.INTEGER);
            }
            
            if (objeto.getHospede() != null && objeto.getHospede().getId() != 0) {
                pstm.setInt(6, objeto.getHospede().getId());
            } else {
                pstm.setNull(6, java.sql.Types.INTEGER);
            }
            
            pstm.setString(7, String.valueOf(objeto.getStatus()));

            pstm.executeUpdate();
            conexao.commit();

        } catch (SQLException ex) {
            System.err.println("Erro ao criar Veículo: " + ex.getMessage());
            try {
                if (conexao != null) conexao.rollback();
            } catch (SQLException e) {
            }
            throw new RuntimeException("Erro ao criar Veículo: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public Veiculo Retrieve(int id) {
        String sqlInstrucao = "SELECT v.id, v.placa, v.cor, v.status, " +
                              "mo.id AS modelo_id, mo.nome AS modelo_descricao, " + // <-- MUDANÇA AQUI
                              "ma.id AS marca_id, ma.descricao AS marca_descricao " +
                              "FROM veiculo AS v " +
                              "LEFT JOIN modelo AS mo ON v.modelo_id = mo.id " +
                              "LEFT JOIN marca AS ma ON mo.marca_id = ma.id " +
                              "WHERE v.id = ?";
        
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
                veiculo = new Veiculo(); 
                
                Marca marca = new Marca();
                marca.setId(rst.getInt("marca_id"));
                marca.setDescricao(rst.getString("marca_descricao"));

                Modelo modelo = new Modelo();
                modelo.setId(rst.getInt("modelo_id"));
                modelo.setNome(rst.getString("modelo_descricao")); // <-- MUDANÇA AQUI
                modelo.setMarca(marca);
                
                veiculo.setId(rst.getInt("id"));
                veiculo.setPlaca(rst.getString("placa"));
                veiculo.setCor(rst.getString("cor"));
                veiculo.setStatus(rst.getString("status").charAt(0));
                veiculo.setModelo(modelo);
                
                veiculo.setFuncionario(null); 
                veiculo.setFornecedor(null);
                veiculo.setHospede(null); 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao buscar Veículo por ID: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return veiculo;
        }
    }

    @Override
    public List<Veiculo> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT v.id, v.placa, v.cor, v.status, " +
                              "mo.id AS modelo_id, mo.nome AS modelo_descricao, " + // <-- MUDANÇA AQUI
                              "ma.id AS marca_id, ma.descricao AS marca_descricao " +
                              "FROM veiculo AS v " +
                              "LEFT JOIN modelo AS mo ON v.modelo_id = mo.id " +
                              "LEFT JOIN marca AS ma ON mo.marca_id = ma.id ";
        
        if (atributo != null && !atributo.trim().isEmpty()) {
            if (atributo.equals("modelo.descricao")) { 
                 atributo = "mo.nome"; 
            }
            sqlInstrucao += " WHERE " + atributo + " LIKE ?";
        }
        
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Veiculo> veiculos = new ArrayList();

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            
            if (atributo != null && !atributo.trim().isEmpty()) {
                pstm.setString(1, "%" + valor + "%");
            }

            rst = pstm.executeQuery();

            while(rst.next()) {
                Marca marca = new Marca();
                marca.setId(rst.getInt("marca_id"));
                marca.setDescricao(rst.getString("marca_descricao"));

                Modelo modelo = new Modelo();
                modelo.setId(rst.getInt("modelo_id"));
                // CORRIGIDO: Usa setNome()
                modelo.setNome(rst.getString("modelo_descricao")); // <-- MUDANÇA AQUI
                modelo.setMarca(marca);
                
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rst.getInt("id"));
                veiculo.setPlaca(rst.getString("placa"));
                veiculo.setCor(rst.getString("cor"));
                veiculo.setStatus(rst.getString("status").charAt(0));
                veiculo.setModelo(modelo);

                veiculo.setFuncionario(null);
                veiculo.setFornecedor(null);
                veiculo.setHospede(null);
                
                veiculos.add(veiculo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao buscar Veículos com filtro: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return veiculos;
        }
    }

    @Override
    public void Update(Veiculo objeto) {
        String sqlInstrucao = "UPDATE veiculo SET placa = ?, cor = ?, modelo_id = ?, funcionario_id = ?, fornecedor_id = ?, hospede_id = ?, status = ? WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false);
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getPlaca());
            pstm.setString(2, objeto.getCor());
            
            if (objeto.getModelo() != null) pstm.setInt(3, objeto.getModelo().getId()); else pstm.setNull(3, java.sql.Types.INTEGER);
            if (objeto.getFuncionario() != null) pstm.setInt(4, objeto.getFuncionario().getId()); else pstm.setNull(4, java.sql.Types.INTEGER);
            if (objeto.getFornecedor() != null) pstm.setInt(5, objeto.getFornecedor().getId()); else pstm.setNull(5, java.sql.Types.INTEGER);
            if (objeto.getHospede() != null) pstm.setInt(6, objeto.getHospede().getId()); else pstm.setNull(6, java.sql.Types.INTEGER);
            pstm.setString(7, String.valueOf(objeto.getStatus()));
            pstm.setInt(8, objeto.getId()); 

            pstm.executeUpdate();
            conexao.commit();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao atualizar Veículo: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(Veiculo objeto) {
        String sqlInstrucao = "DELETE FROM veiculo WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao deletar Veículo: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }
}