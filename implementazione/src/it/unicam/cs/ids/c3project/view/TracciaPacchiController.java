package it.unicam.cs.ids.c3project.view;

import it.unicam.cs.ids.c3project.contenuto.Pacco;
import it.unicam.cs.ids.c3project.corriere.GestoreSpedizioni;
import it.unicam.cs.ids.c3project.corriere.StatoPacco;
import it.unicam.cs.ids.c3project.corriere.TipologiaContenuto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    @FXML TableColumn<Pacco, String> corriereTableColumn;
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

            int idCorriere = Integer.parseInt(corriereTextField.getText());

            Predicate<Pacco> predicate = p -> p.getCorriereID() == idCorriere;
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


    public void launchError(String str) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERRORE!");
        alert.setHeaderText("Qualcosa e' andato storto");
        alert.setContentText("ERRORE: " + str);
        alert.showAndWait();
    }


    private void initTableView() {

        idTableColumn.setCellValueFactory(new PropertyValueFactory<Pacco, String>("ID"));
        corriereTableColumn.setCellValueFactory(new PropertyValueFactory<Pacco, String>("corriereID"));
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
