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

// Assumindo que você tenha uma classe de conexão
// import utilities.ConnectionFactory; 

public class VeiculoDAO implements InterfaceDAO<Veiculo> {

    @Override
    public void Create(Veiculo objeto) {
        // CORRIGIDO: Removido "marca_id" do SQL e dos parâmetros
        String sqlInstrucao = "INSERT INTO veiculo (placa, cor, modelo_id, funcionario_id, fornecedor_id, hospede_id, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getPlaca());
            pstm.setString(2, objeto.getCor());
            
            // Parâmetro 3: modelo_id
            if (objeto.getModelo() != null && objeto.getModelo().getId() != 0) {
                pstm.setInt(3, objeto.getModelo().getId());
            } else {
                pstm.setNull(3, java.sql.Types.INTEGER);
            }
            
            // Parâmetro 4: funcionario_id
            if (objeto.getFuncionario() != null && objeto.getFuncionario().getId() != 0) {
                pstm.setInt(4, objeto.getFuncionario().getId());
            } else {
                pstm.setNull(4, java.sql.Types.INTEGER);
            }
            
            // Parâmetro 5: fornecedor_id
            if (objeto.getFornecedor() != null && objeto.getFornecedor().getId() != 0) {
                pstm.setInt(5, objeto.getFornecedor().getId());
            } else {
                pstm.setNull(5, java.sql.Types.INTEGER);
            }
            
            // Parâmetro 6: hospede_id
            if (objeto.getHospede() != null && objeto.getHospede().getId() != 0) {
                pstm.setInt(6, objeto.getHospede().getId());
            } else {
                pstm.setNull(6, java.sql.Types.INTEGER);
            }
            
            // Parâmetro 7: status
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
        // CORRIGIDO: Adicionado JOIN com modelo e marca
        String sqlInstrucao = "SELECT v.id, v.placa, v.cor, v.status, " +
                              "mo.id AS modelo_id, mo.descricao AS modelo_descricao, " +
                              "ma.id AS marca_id, ma.descricao AS marca_descricao " +
                              // Adicione JOINs para funcionario, fornecedor, hospede se precisar dos nomes
                              "FROM veiculo AS v " +
                              "LEFT JOIN modelo AS mo ON v.modelo_id = mo.id " +
                              "LEFT JOIN marca AS ma ON mo.marca_id = ma.id " +
                              "WHERE v.id = ?";
        
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Veiculo veiculo = null; // Iniciar como nulo

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();
            
            if (rst.next()) {
                veiculo = new Veiculo(); // Cria o veículo só se encontrar
                
                // 1. Criar a Marca
                Marca marca = new Marca();
                marca.setId(rst.getInt("marca_id"));
                marca.setDescricao(rst.getString("marca_descricao"));

                // 2. Criar o Modelo e associar a Marca
                Modelo modelo = new Modelo();
                modelo.setId(rst.getInt("modelo_id"));
                modelo.setDescricao(rst.getString("modelo_descricao"));
                modelo.setMarca(marca);
                
                // 3. Criar o Veículo e associar o Modelo
                veiculo.setId(rst.getInt("id"));
                veiculo.setPlaca(rst.getString("placa"));
                veiculo.setCor(rst.getString("cor"));
                veiculo.setStatus(rst.getString("status").charAt(0));
                veiculo.setModelo(modelo);
                
                // Nota: Funcionario, Fornecedor, Hospede ainda não estão sendo buscados
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
        // CORRIGIDO: Adicionado JOIN com modelo e marca
        String sqlInstrucao = "SELECT v.id, v.placa, v.cor, v.status, " +
                              "mo.id AS modelo_id, mo.descricao AS modelo_descricao, " +
                              "ma.id AS marca_id, ma.descricao AS marca_descricao " +
                              "FROM veiculo AS v " +
                              "LEFT JOIN modelo AS mo ON v.modelo_id = mo.id " +
                              "LEFT JOIN marca AS ma ON mo.marca_id = ma.id ";
        
        // CUIDADO: Esta busca por atributo é frágil. Se o 'atributo' for 'marca.descricao',
        // esta lógica 'WHERE' falhará. Por enquanto, vamos manter como estava.
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
                // 1. Criar a Marca
                Marca marca = new Marca();
                marca.setId(rst.getInt("marca_id"));
                marca.setDescricao(rst.getString("marca_descricao"));

                // 2. Criar o Modelo e associar a Marca
                Modelo modelo = new Modelo();
                modelo.setId(rst.getInt("modelo_id"));
                modelo.setDescricao(rst.getString("modelo_descricao"));
                modelo.setMarca(marca);
                
                // 3. Criar o Veículo e associar o Modelo
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rst.getInt("id"));
                veiculo.setPlaca(rst.getString("placa"));
                veiculo.setCor(rst.getString("cor"));
                veiculo.setStatus(rst.getString("status").charAt(0));
                veiculo.setModelo(modelo);

                // Nota: Funcionario, Fornecedor, Hospede ainda não estão sendo buscados
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
        // CORRIGIDO: Removido "marca_id" do SQL e dos parâmetros
        String sqlInstrucao = "UPDATE veiculo SET placa = ?, cor = ?, modelo_id = ?, funcionario_id = ?, fornecedor_id = ?, hospede_id = ?, status = ? WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false);
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getPlaca());
            pstm.setString(2, objeto.getCor());
            
            // Parâmetro 3: modelo_id
            if (objeto.getModelo() != null) pstm.setInt(3, objeto.getModelo().getId()); else pstm.setNull(3, java.sql.Types.INTEGER);
            
            // Parâmetro 4: funcionario_id
            if (objeto.getFuncionario() != null) pstm.setInt(4, objeto.getFuncionario().getId()); else pstm.setNull(4, java.sql.Types.INTEGER);
            
            // Parâmetro 5: fornecedor_id
            if (objeto.getFornecedor() != null) pstm.setInt(5, objeto.getFornecedor().getId()); else pstm.setNull(5, java.sql.Types.INTEGER);
            
            // Parâmetro 6: hospede_id
            if (objeto.getHospede() != null) pstm.setInt(6, objeto.getHospede().getId()); else pstm.setNull(6, java.sql.Types.INTEGER);
            
            // Parâmetro 7: status
            pstm.setString(7, String.valueOf(objeto.getStatus()));
            
            // Parâmetro 8: id
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
        // Este método estava correto, sem necessidade de alteração
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