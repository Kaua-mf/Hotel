package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Marca;
import model.Modelo;
// Importação da sua classe de conexão
import DAO.ConnectionFactory; 

public class ModeloDAO implements InterfaceDAO<Modelo> {

    @Override
    public void Create(Modelo objeto) {
        String sql = "INSERT INTO modelo (descricao, status, marca_id) VALUES (?, ?, ?)";
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, objeto.getDescricao());
            ps.setString(2, String.valueOf(objeto.getStatus()));
            ps.setInt(3, objeto.getMarca().getId());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar modelo", e);
        }
    }

    @Override
    public Modelo Retrieve(int id) {
        // Removido marca_descricao do SELECT
        String sql = "SELECT mod.id AS modelo_id, " +
                     "       mod.descricao AS modelo_descricao, " +
                     "       mod.status AS modelo_status, " +
                     "       mar.id AS marca_id " +
                     "FROM modelo AS mod " +
                     "LEFT JOIN marca AS mar ON mod.marca_id = mar.id " +
                     "WHERE mod.id = ?";
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return construirModelo(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar modelo por ID", e);
        }
        return null;
    }

    @Override
    public List<Modelo> Retrieve(String atributo, String valor) {
        // ESTA CONSULTA ESTÁ CORRETA.
        // Removido marca_descricao do SELECT
        String sql = "SELECT mod.id AS modelo_id, " +
                     "       mod.descricao AS modelo_descricao, " +
                     "       mod.status AS modelo_status, " +
                     "       mar.id AS marca_id " +
                     
                     // A LINHA 'FROM' ESTÁ PRESENTE AQUI:
                     "FROM modelo AS mod " +
                     
                     "LEFT JOIN marca AS mar ON mod.marca_id = mar.id";

        if (atributo != null && valor != null) {
            sql += " WHERE " + atributo + " LIKE ?";
        }

        List<Modelo> modelos = new ArrayList<>();
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            if (atributo != null && valor != null) {
                ps.setString(1, "%" + valor + "%");
            }
            
            // Esta é a linha 90 (ou próxima) que está falhando
            try (ResultSet rs = ps.executeQuery()) { 
                while (rs.next()) {
                    modelos.add(construirModelo(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw new RuntimeException("Erro ao buscar modelos com marcas", e);
        }
        return modelos;
    }

    @Override
    public void Update(Modelo objeto) {
        String sql = "UPDATE modelo SET descricao = ?, status = ?, marca_id = ? WHERE id = ?";
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, objeto.getDescricao());
            ps.setString(2, String.valueOf(objeto.getStatus()));
            ps.setInt(3, objeto.getMarca().getId());
            ps.setInt(4, objeto.getId());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar modelo", e);
        }
    }

    @Override
    public void Delete(Modelo objeto) {
        String sql = "DELETE FROM modelo WHERE id = ?";
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, objeto.getId());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar modelo", e);
        }
    }

    // Método utilitário para não repetir código
    // Atualizado para não ler 'marca_descricao'
    private Modelo construirModelo(ResultSet rs) throws SQLException {
        Marca marca = new Marca();
        marca.setId(rs.getInt("marca_id"));
        // A linha de setDescricao da marca foi removida, como pedido.
        
        Modelo modelo = new Modelo();
        modelo.setId(rs.getInt("modelo_id"));
        modelo.setDescricao(rs.getString("modelo_descricao"));
        
        String statusStr = rs.getString("modelo_status");
        if (statusStr != null && !statusStr.isEmpty()) {
            modelo.setStatus(statusStr.charAt(0));
        }
        
        modelo.setMarca(marca);
        return modelo;
    }
}