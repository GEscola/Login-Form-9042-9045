import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnect {
    // atributo da instancia
    private Connection conn;
    // atributo da Classe
    private static BDConnect bdConnInstance;

    // metodo construtor privado
    private BDConnect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/db_login", "root", "root");
    }

    // metodo getInstance retorna a instancia
    // se instancia nao existe, cria e retorna instancia
    public static BDConnect getInstance() throws SQLException, ClassNotFoundException {
        if (bdConnInstance == null) {
            bdConnInstance = new BDConnect();
        }
        return bdConnInstance;
    }

    // metodo getConnection retorna instancia da Classe Connection
    public Connection getConnection() {
        return this.conn;
    }

    // metodo closeConnection fecha ligação à base de dados
    public void closeConnection() {
        if (this.conn != null) {
            try {
                this.conn.close();
                System.out.println("\n\nLigacao BD encerrada...");

            } catch (SQLException ex) {
                System.out.println("Erro ao terminar ligacao a BD!");
            }
        }
    }

}
