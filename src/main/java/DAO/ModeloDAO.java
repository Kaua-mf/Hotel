package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Marca;
import model.Modelo;
import DAO.ConnectionFactory; 

public class ModeloDAO implements InterfaceDAO<Modelo> {

    @Override
    public void Create(Modelo objeto) {
        String sql = "INSERT INTO modelo (nome, status, marca_id) VALUES (?, ?, ?)";
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, objeto.getNome()); 
            ps.setString(2, String.valueOf(objeto.getStatus()));
            ps.setInt(3, objeto.getMarca().getId());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar modelo na coluna 'nome'", e);
        }
    }

    @Override
    public Modelo Retrieve(int id) {
        String sql = "SELECT modelo.id, modelo.nome, modelo.status, modelo.marca_id, " +
                     "       marca.descricao AS marca_descricao " + 
                     "FROM modelo " +
                     "LEFT JOIN marca ON modelo.marca_id = marca.id " +
                     "WHERE modelo.id = ?";
        
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
        String sql = "SELECT modelo.id, modelo.nome, modelo.status, modelo.marca_id, " +
                     "       marca.descricao AS marca_descricao " + 
                     "FROM modelo " +
                     "LEFT JOIN marca ON modelo.marca_id = marca.id";

        if (atributo != null && valor != null) {
             String colunaBusca = atributo;
             if (atributo.equalsIgnoreCase("descricao") || atributo.equalsIgnoreCase("modelo.descricao")) {
                 colunaBusca = "modelo.nome"; 
             }
            sql += " WHERE " + colunaBusca + " LIKE ?";
        }

        List<Modelo> modelos = new ArrayList<>();
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            if (atributo != null && valor != null) {
                ps.setString(1, "%" + valor + "%");
            }
            
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
        String sql = "UPDATE modelo SET nome = ?, status = ?, marca_id = ? WHERE id = ?";
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, objeto.getNome());
            ps.setString(2, String.valueOf(objeto.getStatus()));
            ps.setInt(3, objeto.getMarca().getId());
            ps.setInt(4, objeto.getId());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar modelo na coluna 'nome'", e);
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

    private Modelo construirModelo(ResultSet rs) throws SQLException {
        Marca marca = new Marca();
        marca.setId(rs.getInt("marca_id")); 
        marca.setDescricao(rs.getString("marca_descricao")); 
        
        Modelo modelo = new Modelo();
        modelo.setId(rs.getInt("id"));
        modelo.setNome(rs.getString("nome")); 
        
        String statusStr = rs.getString("status");
        if (statusStr != null && !statusStr.isEmpty()) {
            modelo.setStatus(statusStr.charAt(0));
        }
        
        modelo.setMarca(marca);
        return modelo;
    }
}