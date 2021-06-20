package it.unicam.cs.ids.c3project.autenticazione;


import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    //TODO RIEMPIRE CON I DATI(ACCESSO, URL......). DA SOSTITUTIRE TUTTE LE STRINGHE VUOTE
    public Connection databaseLink;

    public Connection getConnection() {

        String dbName = "";
        String dbUsername = "";
        String dbPassword = "";
        String url = "";

        try {
            Class.forName("");
            databaseLink = DriverManager.getConnection(url, dbUsername, dbPassword);

        }   catch (Exception e) {
            e.printStackTrace();
        }

        return databaseLink;

    }
}
