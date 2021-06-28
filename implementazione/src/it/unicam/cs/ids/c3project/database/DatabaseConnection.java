package it.unicam.cs.ids.c3project.database;


import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection ConnectionToDB() {

        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:AwaDB.db");
            return con;

        }   catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
