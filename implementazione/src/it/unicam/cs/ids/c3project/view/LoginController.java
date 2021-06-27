package it.unicam.cs.ids.c3project.view;



import it.unicam.cs.ids.c3project.autenticazione.DatabaseConnection;
import it.unicam.cs.ids.c3project.negozio.GestoreNegozio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.security.GeneralSecurityException;
import java.sql.*;

public class LoginController {
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;

    private Connection con=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;

        @FXML
        public void loginClienteButtonPushed(ActionEvent event) {
            con= DatabaseConnection.ConnectionToDB();

            if (!usernameField.getText().isBlank() || !usernameField.getText().isBlank())
                verifyLoginCliente(event);
            else
                launchError("nome o password sono vuoti");
        }

        private void verifyLoginCliente(ActionEvent event){
           String sql="SELECT * FROM Account INNER JOIN Cliente C on Account.username = C.Username WHERE Account.username LIKE ? AND password LIKE ? ;";
            try {
                pst=con.prepareStatement(sql);
                pst.setString(1,usernameField.getText());
                pst.setString(2,passwordField.getText());
                rs=pst.executeQuery();
                if (rs.next()) {
                    launchMessage("login effettuato");

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/HomeCliente.fxml"));
                    Parent homeClienteParent = loader.load();
                    Scene homeClienteScene = new Scene(homeClienteParent);

                    HomeClienteController controller = loader.getController();
                    controller.populatePacchi(usernameField.getText());
                    controller.populateNegozi();
                    controller.populateCategorie();
                    controller.populatePromozioni();

                    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homeClienteScene);
                    window.show();
                }
                else launchError("Username o Password non validi");

            }
            catch (Exception e){
                launchError("Qualcosa e' andato storto, riprova");
                e.printStackTrace();
            }
            finally {
                try {
                    rs.close();
                    pst.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

        }

    @FXML
    public void loginResponsabileButtonPushed(ActionEvent event) {
        con= DatabaseConnection.ConnectionToDB();

        if (!usernameField.getText().isBlank() || !usernameField.getText().isBlank())
            verifyLoginResponsabile(event);
        else
            launchError("nome o password sono vuoti");
    }
    private void verifyLoginResponsabile(ActionEvent event){
        String sql="SELECT * FROM Account INNER JOIN Responsabile R on Account.username = R.UsernameResponsabile WHERE username LIKE ? AND password LIKE ? ;";
        try {
            pst=con.prepareStatement(sql);
            pst.setString(1,usernameField.getText());
            pst.setString(2,passwordField.getText());
            rs=pst.executeQuery();
            if (rs.next()) {
                launchMessage("login effettuato");

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/HomeResponsabile.fxml"));
                Parent homeResponsabileParent = loader.load();
                Scene homeResponsabileScene = new Scene(homeResponsabileParent);

                HomeResponsabileController controller = loader.getController();
                controller.initialize(usernameField.getText());

                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                window.setScene(homeResponsabileScene);
                window.show();
            }
            else launchError("Username o Password non validi");

        }
        catch (Exception e){
            launchError("Qualcosa e' andato storto, riprova");
            e.printStackTrace();
        }
        finally {
            try {
                rs.close();
                pst.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
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