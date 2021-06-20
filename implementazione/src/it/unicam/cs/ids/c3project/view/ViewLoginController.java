package it.unicam.cs.ids.c3project.view;


import it.unicam.cs.ids.c3project.autenticazione.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewLoginController {

    @FXML
     TextField usernameField;
    @FXML
     PasswordField passwordField;
    @FXML
     Button loginButton;
    @FXML
     TextField loginMessageLabel;


    @FXML public void loginButtonPushed() {

        if (!usernameField.getText().isBlank() || !usernameField.getText().isBlank())
            verifyLogin();

        else
            loginMessageLabel.setText("non lasciare le caselle vuote");

    }

    private void verifyLogin(){
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();

        //TODO CAMBIARE LA QUERY IN BASE AL DATABASE
        String verifyLogin = "SELECT count(1) FROM UserAccounts WHERE username = '" + usernameField.getText() + "' AND password = '" + passwordField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1)
                    loginMessageLabel.setText("login effettuato");

                else loginMessageLabel.setText("Username o Password non validi");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
