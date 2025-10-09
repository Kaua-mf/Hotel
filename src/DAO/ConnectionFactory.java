package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
    
    // Configuração para MySQL (Baseado no seu log)
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Corrigido para o nome moderno do driver
    private static final String url = "jdbc:mysql://localhost:3306/hotel?serverTimezone=UTC&useSSL=false"; // URL base limpa
    private static final String usuario = "root";
    private static final String senha = ""; // Deixado vazio conforme sua configuração local

    public static Connection getConnection() {
        try {
            // 1. (Opcional) Tenta carregar o driver explicitamente (útil para debug ou JDKs antigos)
            // Para versões modernas (Java 6+), não é estritamente necessário.
            Class.forName(driver);
            
            // 2. Conecta usando a URL, usuário e senha em parâmetros separados
            return DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException ex) {
            // Se o driver JAR não for encontrado no Classpath
            System.err.println("ERRO GRAVE: Driver JDBC não encontrado. Verifique o arquivo JAR no Classpath.");
            ex.printStackTrace();
            return null; // Retorna null em caso de falha, para ser tratado nas camadas superiores
        } catch (SQLException ex) {
            // Se a conexão falhar (MySQL desligado, usuário/senha errados, etc.)
            System.err.println("Falha ao conectar ao banco de dados: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    // --- MÉTODOS DE FECHAMENTO SEGURO (CORREÇÃO DE NULLPOINTEREXCEPTION) ---

    // 1. Fechar Connection
    public static void closeConnection(Connection conexao) {
        if (conexao != null) { // CRUCIAL: Verifica se é null
            try {
                conexao.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar Connection: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    // 2. Fechar Connection e PreparedStatement
    public static void closeConnection(Connection conexao, PreparedStatement pstm) {
        // Fecha o PSTM primeiro, pois ele falhou no seu log quando era null
        if (pstm != null) { // CRUCIAL: Verifica se é null
            try {
                pstm.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar PreparedStatement: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        closeConnection(conexao);
    }

    // 3. Fechar Connection, PreparedStatement e ResultSet
    public static void closeConnection(Connection conexao, PreparedStatement pstm, ResultSet rst) {
        if (rst != null) { // CRUCIAL: Verifica se é null
            try {
                rst.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar ResultSet: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        // Chama o método que fecha PSTM e Connection de forma segura
        closeConnection(conexao, pstm);
    }
}