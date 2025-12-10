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
        String sql = "INSERT INTO marca (descricao, status) VALUES (?, ?)";
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, objeto.getDescricao());
            ps.setString(2, String.valueOf(objeto.getStatus()));
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar marca", e);
        }
    }

    @Override
    public Marca Retrieve(int id) {
        String sql = "SELECT id, descricao, status FROM marca WHERE id = ?";
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Marca marca = new Marca();
                    marca.setId(rs.getInt("id"));
                    marca.setDescricao(rs.getString("descricao"));
                    marca.setStatus(rs.getString("status").charAt(0));
                    return marca;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar marca por ID", e);
        }
        return null; 
    }

    @Override
    public List<Marca> Retrieve(String atributo, String valor) {
        String sql = "SELECT id, descricao, status FROM marca";
        
        if (atributo != null && valor != null) {
            sql += " WHERE " + atributo + " LIKE ?";
        }
        
        List<Marca> marcas = new ArrayList<>();
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            if (atributo != null && valor != null) {
                ps.setString(1, "%" + valor + "%");
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Marca marca = new Marca();
                    marca.setId(rs.getInt("id"));
                    marca.setDescricao(rs.getString("descricao"));
                    marca.setStatus(rs.getString("status").charAt(0));
                    marcas.add(marca);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar marcas", e);
        }
        return marcas;
    }

    @Override
    public void Update(Marca objeto) {
        String sql = "UPDATE marca SET descricao = ?, status = ? WHERE id = ?";
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, objeto.getDescricao());
            ps.setString(2, String.valueOf(objeto.getStatus()));
            ps.setInt(3, objeto.getId());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar marca", e);
        }
    }

    @Override
    public void Delete(Marca objeto) {
        String sql = "DELETE FROM marca WHERE id = ?";
        
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, objeto.getId());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar marca", e);
        }
    }
}