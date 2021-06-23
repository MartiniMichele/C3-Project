package it.unicam.cs.ids.c3project.view;



import it.unicam.cs.ids.c3project.autenticazione.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        public void loginButtonPushed(ActionEvent event) {
            con= DatabaseConnection.ConnectionToDB();

            if (!usernameField.getText().isBlank() || !usernameField.getText().isBlank())
                verifyLogin(event);

            else
                launchError("nome o password sono vuoti");

        }

        private void verifyLogin(ActionEvent event){


           String sql="SELECT * FROM Account WHERE username LIKE ? AND password LIKE ?;";
            try {
                pst=con.prepareStatement(sql);
                pst.setString(1,usernameField.getText());
                pst.setString(2,passwordField.getText());
                rs=pst.executeQuery();
                if (rs.next()) {
                    launchMessage("login effettuato");

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/GestioneVetrina.fxml"));
                    Parent gestioneVetrinaParent = loader.load();
                    Scene gestioneVetrinaScene = new Scene(gestioneVetrinaParent);

                    GestioneVetrinaController controller = loader.getController();
                    controller.populate();

                    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    window.setScene(gestioneVetrinaScene);
                    window.show();
                }
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

    public void launchMessage(String str) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("LOGIN MESSAGE");
        alert.setHeaderText(str + "!");
        alert.showAndWait();
    }

    }