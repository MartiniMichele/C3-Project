package it.unicam.cs.ids.c3project.view;

import it.unicam.cs.ids.c3project.contenuto.Pacco;
import it.unicam.cs.ids.c3project.corriere.GestoreSpedizioni;
import it.unicam.cs.ids.c3project.contenuto.StatoPacco;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Predicate;

public class TracciaPacchiController {

    GestoreSpedizioni gestore = GestoreSpedizioni.getInstance();

    @FXML
    Button idButton;

    @FXML
    Button corriereButton;

    @FXML
    Button statoButton;

    @FXML
    TextField idTextField;

    @FXML
    TextField corriereTextField;

    @FXML
    TextField statoTextField;

    @FXML TableView<Pacco> tracciaPaccoTableView;
    @FXML TableColumn<Pacco, String> idTableColumn;
    @FXML TableColumn<Pacco, LocalTime> tempoArrivoTableColumn;
    @FXML TableColumn<Pacco, StatoPacco> statoTableColumn;

    public void idButtonPushed() {

        if (!idTextField.getText().isBlank()) {

            int id = Integer.parseInt(idTextField.getText());

            Predicate<Pacco> predicate = p -> p.getID() == id;
            updateTableView(gestore.filtraPacchi(predicate));
        }

        else launchError("il campo è vuoto");
    }

    public void cercaCorriereButtonPushed() {


        if (!corriereTextField.getText().isBlank()) {

            String usernameCorriere = corriereTextField.getText();

            Predicate<Pacco> predicate = p -> p.getusernameCorriere() == usernameCorriere;
            updateTableView(gestore.filtraPacchi(predicate));
        }

        else launchError("il campo è vuoto");
    }


    public void cercaStatoButtonPushed() {

        if (!statoTextField.getText().isBlank()) {

            String statoPacco = statoTextField.getText();

            Predicate<Pacco> predicate = p -> statoPacco.equalsIgnoreCase(p.getStatoPacco().toString());
            updateTableView(gestore.filtraPacchi(predicate));
        }

        else launchError("il campo è vuoto");
    }

    public void homeButtonPushed(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/HomeCliente.fxml"));
            Parent homeClienteParent = loader.load();
            Scene homeClienteScene = new Scene(homeClienteParent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(homeClienteScene);
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


    public void initTableView() {

        idTableColumn.setCellValueFactory(new PropertyValueFactory<Pacco, String>("ID"));
        tempoArrivoTableColumn.setCellValueFactory(new PropertyValueFactory<Pacco, LocalTime>("tempoDiArrivoStimato"));
        statoTableColumn.setCellValueFactory(new PropertyValueFactory<Pacco, StatoPacco>("statoPacco"));

        tracciaPaccoTableView.setItems(getObservablePacchi(gestore.getPacchiDaConsegnare()));
        tracciaPaccoTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void updateTableView(List<Pacco> lista) {
        tracciaPaccoTableView.setItems(getObservablePacchi(lista));
    }

    private ObservableList<Pacco> getObservablePacchi(List<Pacco> lista) {
        return FXCollections.observableArrayList(lista);
    }
}
