package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Veiculo;
import model.Fornecedor;
import model.Funcionario;
import model.Hospede;
import model.Modelo;
import model.Marca; 

public class VeiculoDAO implements InterfaceDAO<Veiculo> {

    @Override
    public void Create(Veiculo objeto) {
        String sqlInstrucao = "INSERT INTO veiculo (placa, cor, modelo_id, marca_id, funcionario_id, fornecedor_id, hospede_id, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
            
            if (objeto.getMarca() != null && objeto.getMarca().getId() != 0) {
                pstm.setInt(4, objeto.getMarca().getId());
            } else {
                pstm.setNull(4, java.sql.Types.INTEGER); 
            }
            
            if (objeto.getFuncionario() != null && objeto.getFuncionario().getId() != 0) {
                pstm.setInt(5, objeto.getFuncionario().getId());
            } else {
                pstm.setNull(5, java.sql.Types.INTEGER);
            }
            
            if (objeto.getFornecedor() != null && objeto.getFornecedor().getId() != 0) {
                pstm.setInt(6, objeto.getFornecedor().getId());
            } else {
                pstm.setNull(6, java.sql.Types.INTEGER);
            }
            
            if (objeto.getHospede() != null && objeto.getHospede().getId() != 0) {
                pstm.setInt(7, objeto.getHospede().getId());
            } else {
                pstm.setNull(7, java.sql.Types.INTEGER);
            }
            
            pstm.setString(8, String.valueOf(objeto.getStatus()));

            pstm.executeUpdate();
            conexao.commit();

        } catch (SQLException ex) {
            System.err.println("Erro ao criar Veículo: " + ex.getMessage());
            try {
                if (conexao != null) conexao.rollback();
            } catch (SQLException e) {
            }
            System.out.println("DEBUG: Placa a ser inserida: " + objeto.getPlaca());

            throw new RuntimeException("Erro ao criar Veículo: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
        if (objeto.getMarca() != null) {
    System.out.println("DEBUG: ID da Marca sendo enviado: " + objeto.getMarca().getId());
} else {
    System.out.println("DEBUG: Marca sendo enviada como NULL.");
}
    }

    @Override
    public Veiculo Retrieve(int id) {
        String sqlInstrucao = "SELECT id, placa, cor, modelo_id, marca_id, funcionario_id, fornecedor_id, hospede_id, status FROM veiculo WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Veiculo veiculo = new Veiculo();

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            
            if (rst.next()) {
                veiculo.setId(rst.getInt("id"));
                veiculo.setPlaca(rst.getString("placa"));
                veiculo.setCor(rst.getString("cor"));
                veiculo.setModelo((Modelo)null); 
                veiculo.setMarca((Marca)null);
                veiculo.setFuncionario((Funcionario)null); 
                veiculo.setFornecedor((Fornecedor)null);
                veiculo.setHospede((Hospede)null); 
                
                veiculo.setStatus(rst.getString("status").charAt(0));
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
        String sqlInstrucao = "SELECT id, placa, cor, modelo_id, marca_id, funcionario_id, fornecedor_id, hospede_id, status FROM veiculo";
        if (atributo != null && !atributo.trim().isEmpty()) {
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
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rst.getInt("id"));
                veiculo.setPlaca(rst.getString("placa"));
                veiculo.setCor(rst.getString("cor"));
                veiculo.setModelo((Modelo)null);
                veiculo.setMarca((Marca)null);
                veiculo.setFuncionario((Funcionario)null);
                veiculo.setFornecedor((Fornecedor)null);
                veiculo.setHospede((Hospede)null);
                
                veiculo.setStatus(rst.getString("status").charAt(0));
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
        String sqlInstrucao = "UPDATE veiculo SET placa = ?, cor = ?, modelo_id = ?, marca_id = ?, funcionario_id = ?, fornecedor_id = ?, hospede_id = ?, status = ? WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false);
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getPlaca());
            pstm.setString(2, objeto.getCor());
            
            if (objeto.getModelo() != null) pstm.setInt(3, objeto.getModelo().getId()); else pstm.setNull(3, java.sql.Types.INTEGER);
            if (objeto.getMarca() != null) pstm.setInt(4, objeto.getMarca().getId()); else pstm.setNull(4, java.sql.Types.INTEGER);
            if (objeto.getFuncionario() != null) pstm.setInt(5, objeto.getFuncionario().getId()); else pstm.setNull(5, java.sql.Types.INTEGER);
            if (objeto.getFornecedor() != null) pstm.setInt(6, objeto.getFornecedor().getId()); else pstm.setNull(6, java.sql.Types.INTEGER);
            if (objeto.getHospede() != null) pstm.setInt(7, objeto.getHospede().getId()); else pstm.setNull(7, java.sql.Types.INTEGER);
            
            pstm.setString(8, String.valueOf(objeto.getStatus()));
            pstm.setInt(9, objeto.getId()); 

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