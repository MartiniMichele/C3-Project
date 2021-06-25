package it.unicam.cs.ids.c3project.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeResponsabileController {


    public void gestioneVetrinaButtonPushed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/TracciaPacchi.fxml"));
            Parent gestioneVetrinaParent = loader.load();
            Scene gestioneVetrinaScene = new Scene(gestioneVetrinaParent);

            GestioneVetrinaController controller = loader.getController();

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


    public void populate() {

    }
}
