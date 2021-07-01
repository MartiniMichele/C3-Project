package it.unicam.cs.ids.c3project.view;

import it.unicam.cs.ids.c3project.database.DatabaseConnection;
import it.unicam.cs.ids.c3project.negozio.GestoreNegozio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HomeResponsabileController {

    private Connection con= DatabaseConnection.getConnection();
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    private GestoreNegozio gestoreNegozio = GestoreNegozio.getInstance();

    @FXML
    ChoiceBox<String> negozioChoiceBox;


    public void gestioneVetrinaButtonPushed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GestioneVetrina.fxml"));
            Parent gestioneVetrinaParent = loader.load();
            Scene gestioneVetrinaScene = new Scene(gestioneVetrinaParent);

            GestioneVetrinaController controller = loader.getController();
            controller.populate(negozioChoiceBox.getValue());

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(gestioneVetrinaScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
            launchError("errore nel caricamento del file");
        }
    }

    public void launchError(String str) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERRORE!");
        alert.setHeaderText("Qualcosa e' andato storto");
        alert.setContentText("ERRORE: " + str);
        alert.showAndWait();
    }

    public void initialize(String responsabile) {

        negozioChoiceBox.getItems().addAll(getNegozioAssociato(responsabile));

    }

    private List<String> getNegozioAssociato(String responsabile) {
        List<String> negoziAssociati = new ArrayList<>();
        String query = "SELECT * from Negozio where Responsabile like ?";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, responsabile);
            rs = pst.executeQuery();
            while (rs.next()) {
                negoziAssociati.add(rs.getString("Vetrina"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return negoziAssociati;
    }

}
