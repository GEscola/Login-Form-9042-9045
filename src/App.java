import java.io.*;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import javax.sql.rowset.serial.SerialDatalink;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        menuPrint();
        /*String password = readPassword("Enter password: ");
        System.out.println("The password entered is: "+password);*/
    }

    public static void menuPrint() throws ClassNotFoundException, SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("+======================================");
        System.out.println("|          TABELA DE LOGIN            |");
        System.out.println("|                                     |");
        System.out.println("| 1 - Login                           |");
        System.out.println("| 2 - Sign Up                         |"); 
        System.out.println("| 3 - Sair                            |"); 
        System.out.println("+=====================================+");
        System.out.print("\n>> ");
        int opcao = scanner.nextInt();
        System.out.print("\033[H\033[2J");  
        
        }








    public static void ads() throws ClassNotFoundException, SQLException {
        BDConnect asdas = BDConnect.getInstance();   
    }

    public static class EraserThread implements Runnable {
        private boolean stop;

        /**
         * @param The prompt displayed to the user
         */
        public EraserThread(String prompt) {
            System.out.print(prompt);
        }

        /**
         * Begin masking...display asterisks (*)
         */
        public void run() {
            stop = true;
            while (stop) {
                System.out.print("\010*");
                try {
                    Thread.currentThread().sleep(1);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }

        /**
         * Instruct the thread to stop masking
         */
        public void stopMasking() {
            this.stop = false;
        }
    }

    public static String readPassword(String prompt) {
        EraserThread et = new EraserThread(prompt);
        Thread mask = new Thread(et);
        mask.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String password = "";

        try {
            password = in.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        // stop masking
        et.stopMasking();
        // return the password entered by the user
        return password;
    }

    
}

