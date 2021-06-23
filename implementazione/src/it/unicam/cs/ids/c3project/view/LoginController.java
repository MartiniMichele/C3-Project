package it.unicam.cs.ids.c3project.view;



import it.unicam.cs.ids.c3project.autenticazione.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;

public class LoginController {
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;

     Connection con=null;
     PreparedStatement pst=null;
     ResultSet rs=null;


        @FXML
        public void loginButtonPushed() {
            con= DatabaseConnection.ConnectionToDB();

            if (!usernameField.getText().isBlank() || !usernameField.getText().isBlank())
                verifyLogin();

            else
                launchError("nome o password sono vuoti");

        }

        private void verifyLogin(){


           String sql="SELECT * FROM Account WHERE username LIKE ? AND password LIKE ?;";
            try {
                pst=con.prepareStatement(sql);
                pst.setString(1,usernameField.getText());
                pst.setString(2,passwordField.getText());
                rs=pst.executeQuery();
                if (rs.next())
                        launchError("login effettuato");
                else launchError("Username o Password non validi");

            }
            catch (Exception e){
                launchError("Catch");
                e.printStackTrace();
            }


        }

        public void launchError(String str) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE!");
            alert.setHeaderText("Qualcosa e' andato storto");
            alert.setContentText("ERRORE: " + str);
            alert.showAndWait();
        }

    }