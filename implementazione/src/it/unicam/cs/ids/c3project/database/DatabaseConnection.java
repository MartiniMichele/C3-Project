package it.unicam.cs.ids.c3project.database;


import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection ConnectionToDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:AwaDB.db");

        }   catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
