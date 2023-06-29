import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import javax.sql.rowset.serial.SerialDatalink;
import java.sql.SQLException;

class User{
    int id;
    String nome;
    String telefone;
    String password;
    String email;
    String cargo;
    String ultimoLogin;
    String registo;
    
    User(int id,String nome, String telefone, String password, String email,String cargo,String ultimoLogin,String registo){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.password = password;
        this.email = email;
        this.cargo = cargo;
        this.ultimoLogin = ultimoLogin;
        this.registo = registo;
    }
}

public class Utils {
    public static User loginUser(String email,String password) throws ClassNotFoundException, SQLException {
        User user = null;
        BDConnect instance = BDConnect.getInstance();
        Connection connection = instance.getConnection();

        String query = "SELECT user.id,funcao.cargo,user.nome,user.email,user.telemovel,user.password,user.ultimo_login,user.registo_data FROM user INNER JOIN funcao ON funcao.id=user.FUNCAO_id WHERE user.email LIKE '" + email + "' AND user.password LIKE '" + password + "';";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while(rs.next())
        {
           user = new User(rs.getInt("id"),rs.getString("user.nome"),rs.getString("user.telemovel"),rs.getString("user.password"),rs.getString("user.email"),rs.getString("funcao.cargo"),rs.getString("ultimo_login"),rs.getString("registo_data"));
        }

        return user;
    }

    public static void signupUser(String email,String username,String telefone,String password) throws ClassNotFoundException, SQLException {
        User user = null;
        BDConnect instance = BDConnect.getInstance();
        Connection connection = instance.getConnection();

        String query = "INSERT INTO user(nome,telemovel,email,registo_data,ultimo_login,password,FUNCAO_id) VALUES ('" + username + "','" + telefone + "','" + email + "',NOW(),NOW(),'" + password + "',2);";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);

    }

    public static void updateEntry(User user) throws ClassNotFoundException, SQLException {
        BDConnect instance = BDConnect.getInstance();
        Connection connection = instance.getConnection();

        String query = "UPDATE user SET ultimo_login = NOW() WHERE user.id = " + user.id + ";";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

}
