package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Hospede;

public class HospedeDAO implements InterfaceDAO<Hospede> {

    private final String COLUNAS_INSERT = "nome, fone, fone2, email, cep, logradouro, bairro, cidade, complemento, data_cadastro, cpf, rg, obs, status, usuario, senha, razao_social, cnpj, inscricao_estadual, contato, sexo";
    private final String COLUNAS_READ = "id, " + COLUNAS_INSERT;

    @Override
    public void Create(Hospede objeto) {
        String sqlInstrucao = "INSERT INTO hospede(" + COLUNAS_INSERT + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false); 
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getNome());
            pstm.setString(2, objeto.getFone()); 
            pstm.setString(3, objeto.getFone2());
            pstm.setString(4, objeto.getEmail());
            pstm.setString(5, objeto.getCep());
            pstm.setString(6, objeto.getLogradouro());
            pstm.setString(7, objeto.getBairro());
            pstm.setString(8, objeto.getCidade());
            pstm.setString(9, objeto.getComplemento());
            pstm.setString(10, objeto.getDataCadastro());
            pstm.setString(11, objeto.getCpf());
            pstm.setString(12, objeto.getRg());
            pstm.setString(13, objeto.getObs());
            pstm.setString(14, String.valueOf(objeto.getStatus()));
            pstm.setString(15, objeto.getUsuario()); 
            pstm.setString(16, objeto.getSenha());   
            pstm.setString(17, objeto.getRazaoSocial());
            pstm.setString(18, objeto.getCnpj());
            pstm.setString(19, objeto.getInscricaoEstadual());
            pstm.setString(20, objeto.getContato());
            pstm.setString(21, objeto.getSexo());

            pstm.executeUpdate(); 
            conexao.commit();

        } catch (SQLException ex) {
            System.err.println("Erro ao criar Hóspede: " + ex.getMessage());
            try {
                if (conexao != null) conexao.rollback();
            } catch (SQLException e) {
            }
            throw new RuntimeException("Falha na criação do Hóspede.", ex);

        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public Hospede Retrieve(int id) {
        String sqlInstrucao = "SELECT " + COLUNAS_READ + " FROM hospede WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        Hospede hospede = null;

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, id);
            rst = pstm.executeQuery();

            if (rst.next()) {
                hospede = new Hospede();
                mapearResultado(hospede, rst);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Hóspede por ID: " + ex.getMessage());
            throw new RuntimeException("Falha na busca de Hóspede por ID.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return hospede;
    }

    @Override
    public List<Hospede> Retrieve(String atributo, String valor) {
        String sqlInstrucao = "SELECT " + COLUNAS_READ + " FROM hospede";

        if (atributo != null && !atributo.trim().isEmpty()) {
            sqlInstrucao += " WHERE " + atributo + " LIKE ?";
        }

        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Hospede> listaHospedes = new ArrayList<>();

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);

            if (atributo != null && !atributo.trim().isEmpty()) {
                pstm.setString(1, "%" + valor + "%");
            }

            rst = pstm.executeQuery();

            while (rst.next()) {
                Hospede hospede = new Hospede();
                mapearResultado(hospede, rst);
                listaHospedes.add(hospede);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Hóspedes: " + ex.getMessage());
            throw new RuntimeException("Falha na busca de Hóspedes.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
        }
        return listaHospedes;
    }

    @Override
    public void Update(Hospede objeto) {
        String sqlInstrucao = "UPDATE hospede SET nome = ?, fone = ?, fone2 = ?, email = ?, cep = ?, logradouro = ?, bairro = ?, cidade = ?, complemento = ?, data_cadastro = ?, cpf = ?, rg = ?, obs = ?, status = ?, usuario = ?, senha = ?, razao_social = ?, cnpj = ?, inscricao_estadual = ?, contato = ?, sexo = ? WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            conexao.setAutoCommit(false);
            pstm = conexao.prepareStatement(sqlInstrucao);

            pstm.setString(1, objeto.getNome());
            pstm.setString(2, objeto.getFone()); 
            pstm.setString(3, objeto.getFone2());
            pstm.setString(4, objeto.getEmail());
            pstm.setString(5, objeto.getCep());
            pstm.setString(6, objeto.getLogradouro());
            pstm.setString(7, objeto.getBairro());
            pstm.setString(8, objeto.getCidade());
            pstm.setString(9, objeto.getComplemento());
            pstm.setString(10, objeto.getDataCadastro());
            pstm.setString(11, objeto.getCpf());
            pstm.setString(12, objeto.getRg());
            pstm.setString(13, objeto.getObs());
            pstm.setString(14, String.valueOf(objeto.getStatus()));
            pstm.setString(15, objeto.getUsuario());
            pstm.setString(16, objeto.getSenha());
            pstm.setString(17, objeto.getRazaoSocial());
            pstm.setString(18, objeto.getCnpj());
            pstm.setString(19, objeto.getInscricaoEstadual());
            pstm.setString(20, objeto.getContato());
            pstm.setInt(21, objeto.getId()); 
            pstm.setString(22, objeto.getSexo());

            pstm.executeUpdate();
            conexao.commit();

        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar Hóspede: " + ex.getMessage());
            throw new RuntimeException("Falha na atualização do Hóspede.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    @Override
    public void Delete(Hospede objeto) {
        String sqlInstrucao = "DELETE FROM hospede WHERE id = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = ConnectionFactory.getConnection();
            pstm = conexao.prepareStatement(sqlInstrucao);
            pstm.setInt(1, objeto.getId());
            pstm.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao deletar Hóspede: " + ex.getMessage());
            throw new RuntimeException("Falha na exclusão do Hóspede.", ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }
    }

    private void mapearResultado(Hospede hospede, ResultSet rst) throws SQLException {
        hospede.setId(rst.getInt("id"));
        hospede.setNome(rst.getString("nome"));
        hospede.setFone(rst.getString("fone"));
        hospede.setFone2(rst.getString("fone2"));
        hospede.setEmail(rst.getString("email"));
        hospede.setCep(rst.getString("cep"));
        hospede.setLogradouro(rst.getString("logradouro"));
        hospede.setBairro(rst.getString("bairro"));
        hospede.setCidade(rst.getString("cidade"));
        hospede.setComplemento(rst.getString("complemento"));
        hospede.setDataCadastro(rst.getString("data_cadastro"));
        hospede.setCpf(rst.getString("cpf"));
        hospede.setRg(rst.getString("rg"));
        hospede.setObs(rst.getString("obs"));
        hospede.setStatus(rst.getString("status").charAt(0));
        hospede.setUsuario(rst.getString("usuario")); 
        hospede.setSenha(rst.getString("senha"));    
        hospede.setRazaoSocial(rst.getString("razao_social"));
        hospede.setCnpj(rst.getString("cnpj"));
        hospede.setInscricaoEstadual(rst.getString("inscricao_estadual"));
        hospede.setContato(rst.getString("contato"));
        hospede.setSexo(rst.getString("sexo"));
    }
}