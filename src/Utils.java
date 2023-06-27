import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import javax.sql.rowset.serial.SerialDatalink;
import java.sql.SQLException;

class User{
    String nome;
    String telefone;
    String password;
    String email;
    String cargo;
    
    User(String nome, String telefone, String password, String email,String cargo){
        this.nome = nome;
        this.telefone = telefone;
        this.password = password;
        this.email = email;
        this.cargo = cargo;
    }
}

public class Utils {
    public static User loginUser(String email,String password) throws ClassNotFoundException, SQLException {
        User user = null;
        BDConnect instance = BDConnect.getInstance();
        Connection connection = instance.getConnection();

        String query = "SELECT funcao.cargo,user.nome,user.email,user.telemovel,user.password FROM user INNER JOIN funcao ON funcao.id=user.FUNCAO_id WHERE user.email LIKE '" + email + "' AND user.password LIKE '" + password + "';";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next())
        {
           user = new User(rs.getString("user.nome"),rs.getString("user.telemovel"),rs.getString("user.password"),rs.getString("user.email"),rs.getString("funcao.cargo"));
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


}
