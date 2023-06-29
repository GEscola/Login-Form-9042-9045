import java.io.*;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import javax.sql.rowset.serial.SerialDatalink;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException,NoSuchAlgorithmException {
        menuPrint();
        /*
         * String password = readPassword("Enter password: ");
         * System.out.println("The password entered is: "+password);
         */
    }

    public static void menuPrint() throws ClassNotFoundException, SQLException,NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("+=====================================+");
        System.out.println("|          TABELA DE LOGIN            |");
        System.out.println("|                                     |");
        System.out.println("| 1 - Login                           |");
        System.out.println("| 2 - Sign Up                         |");
        System.out.println("| 3 - Sair                            |");
        System.out.println("+=====================================+");
        System.out.print("\n>> ");
        int opcao = scanner.nextInt();
        System.out.print("\033[H\033[2J");
        switch(opcao){
            case 1:
                execLogin();
            break;
            case 2:
                execSignUp();
            break;
            case 3:
                System.out.println("A sair...");
                BDConnect instance = BDConnect.getInstance();
                instance.closeConnection();
        }
    }

    public static void execLogin() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduza Login:");
        System.out.print("\tIntroduza Email => ");
        String email = scanner.nextLine();
        String password = readPassword("\n\tIntroduza Password =>  ");

        System.out.print("\033[H\033[2J");

        User user = Utils.loginUser(email, password);

        if(user != null){
            abrirJanela(user);
        }
        else{
            System.out.println("Login Invalido tente outra vez!");
            menuPrint();
        }
    }

    public static void execSignUp() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("SignUp:");
        System.out.print("\tIntroduza Email => ");
        String email = scanner.nextLine();
        System.out.print("\tIntroduza Username => ");
        String username = scanner.nextLine();
        System.out.print("\tIntroduza número de telefone => ");
        String telefone = scanner.nextLine();
        String password = readPassword("\tIntroduza Password =>  ");

        Utils.signupUser(email,username,telefone,password);
        System.out.print("\033[H\033[2J");
        menuPrint();
    }

    public static void abrirJanela(User user)throws ClassNotFoundException, SQLException,NoSuchAlgorithmException{
        if(user.cargo.equals("admin")){
            System.out.println("                        JANELA DO ADMINISTRADOR       \n");
            System.out.println("Bem-vindo " + user.nome + "!\n");
            System.out.print("Ultimo login: " + user.ultimoLogin);
            System.out.print("   Registrado em: " + user.registo);
            System.out.println("\nInsira uma tecla para fazer logoff!");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            Utils.updateEntry(user);
            System.out.print("\033[H\033[2J");
            menuPrint();
        }
        else{
            System.out.println("                          JANELA DO UTILIZADOR       \n");
            System.out.println("Bem-vindo " + user.nome + "!\n");
            System.out.print("Ultimo login: " + user.ultimoLogin);
            System.out.print("   Registrado em: " + user.registo);
            System.out.println("\nInsira uma tecla para fazer logoff!");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            Utils.updateEntry(user);
            System.out.print("\033[H\033[2J");
            menuPrint();
        }
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
                try {
                    System.out.print("\010*");
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
