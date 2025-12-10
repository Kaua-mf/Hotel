package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
    
    private static final String driver = "com.mysql.cj.jdbc.Driver"; 
    private static final String url = "jdbc:mysql://localhost:3306/hotel?serverTimezone=UTC&useSSL=false"; 
    private static final String usuario = "root";
    private static final String senha = "";

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException ex) {
            System.err.println("ERRO GRAVE: Driver JDBC não encontrado. Verifique o arquivo JAR no Classpath.");
            ex.printStackTrace();
            return null; 
        } catch (SQLException ex) {
            System.err.println("Falha ao conectar ao banco de dados: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection conexao) {
        if (conexao != null) { 
            try {
                conexao.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar Connection: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection conexao, PreparedStatement pstm) {
        if (pstm != null) { 
            try {
                pstm.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar PreparedStatement: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        closeConnection(conexao);
    }

    public static void closeConnection(Connection conexao, PreparedStatement pstm, ResultSet rst) {
        if (rst != null) { 
            try {
                rst.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar ResultSet: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        closeConnection(conexao, pstm);
    }
}