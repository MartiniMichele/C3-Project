package it.unicam.cs.ids.c3project.autenticazione;


import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    //TODO RIEMPIRE CON I DATI(ACCESSO, URL......). DA SOSTITUTIRE TUTTE LE STRINGHE VUOTE

    Connection connection=null;

    public static Connection ConnectionToDB() {

        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:AWADB");
            System.out.println("Connessione al DB riuscita");
            return con;

        }   catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
