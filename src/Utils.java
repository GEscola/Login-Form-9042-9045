import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import javax.sql.rowset.serial.SerialDatalink;
import java.sql.SQLException;

public class Utils {


    public static boolean loginUser(String nome,String password) throws ClassNotFoundException, SQLException {
        BDConnect instance = BDConnect.getInstance();
        Connection connection = instance.getConnection();

        String query = "SELECT FROM employees ";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return false;
    }


}
